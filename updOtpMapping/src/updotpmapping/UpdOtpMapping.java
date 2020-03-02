package updotpmapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import utils.ApplicationProperties;
import utils.DBServer;
import utils.DBServerException;
import utils.GetArgsException;
import utils.Md5;
import utils.ValidServers;

/**
 * Programme Java permettant de mettre � jour dans une base de donn�es de
 * destination les raisons d'appel (Open Ticket Purpose) � partir de celles
 * d�clar�es dans une base de donn�es source.
 *
 * @author Thierry Baribaud
 * @version 0.02
 */
public class UpdOtpMapping {

    /**
     * sourceServerType : prod pour le serveur de production, pre-prod pour le
     * serveur de pr�-production, dev pour le serveur de d�veloppement. Valeur
     * par d�faut : pre-prod.
     */
    private String sourceServerType = "pre-prod";

    /**
     * destinationServerType : prod pour le serveur de production, pre-prod pour
     * le serveur de pr�-production, dev pour le serveur de d�veloppement.
     * Valeur par d�faut : pre-prod.
     */
    private String destinationServerType = "pre-prod";

    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par d�faut : false.
     */
    private static boolean debugMode = false;

    /**
     * testMode : fonctionnement du programme en mode test (true/false). Valeur
     * par d�faut : false.
     */
    private static boolean testMode = false;

    /**
     * unum : r�f�rence au service d'urgence (identifiant interne)
     */
    private int unum;

    /**
     * clientCompanyUuid : identifiant universel unique du service d'urgence
     */
    private String clientCompanyUuid = null;

    private final static String HOST = "1.2.3.4";
    private final static int PORT = 27017;

    /**
     * Constructeur principal de la classe updOtpMapping
     *
     * @param args arguments en ligne de commande
     * @throws GetArgsException en cas d'erreur avec les param�tres en ligne de
     * commande
     * @throws java.io.IOException en cas d'erreur d'entr�e/sortie.
     * @throws utils.DBServerException en cas d'erreur avec le serveur de base
     * de donn�es.
     */
    public UpdOtpMapping(String[] args) throws GetArgsException, IOException, DBServerException {
        ApplicationProperties applicationProperties;
        DBServer mgoSource;
        DBServer mgoDestination;
        MongoClient mongoSourceClient;
        MongoDatabase mongoSourceDatabase;
        MongoClient mongoDestinationClient;
        MongoDatabase mongoDestinationDatabase;
        Enumeration names;
        String name;
        String connectionString;
        OpeningTicketPurposeRef openingTicketPurposeRef;
        OtpMappings otpMappings;

        System.out.println("Cr�ation d'une instance de updOtpMapping ...");

        System.out.println("Analyse des arguments de la ligne de commande ...");
        this.getArgs(args);
        System.out.println("Argument(s) en ligne de commande lus().");

        System.out.println("Lecture des param�tres d'ex�cution ...");
        applicationProperties = new ApplicationProperties("updOtpMapping.prop");
        if (debugMode) {
            names = applicationProperties.propertyNames();

            while (names.hasMoreElements()) {
                name = names.nextElement().toString();
                System.out.println("  " + name + ":" + applicationProperties.getProperty(name));
            }
        }
        System.out.println("Param�tres d'ex�cution lus.");

        System.out.println("Lecture des param�tres du serveur Mongo source ...");
//        System.out.println("sourceServerType:" + sourceServerType);
        mgoSource = new DBServer(sourceServerType, "source", applicationProperties);
        System.out.println("Param�tres du serveur Mongo source lus.");
        if (debugMode) {
            System.out.println(mgoSource);
        }

        System.out.println("Lecture des param�tres du serveur Mongo de destination ...");
//        System.out.println("destinationServerType:" + destinationServerType);
        mgoDestination = new DBServer(destinationServerType, "destination", applicationProperties);
        System.out.println("Param�tres du serveur Mongo de destination lus.");
        if (debugMode) {
            System.out.println(mgoDestination);
        }

        if (debugMode) {
            System.out.println(this.toString());
        }

        System.out.println("Ouverture de la connexion au serveur MongoDb source : " + mgoSource.getName());
        connectionString = "mongodb://" + mgoSource.getUserLogin() + ":" + mgoSource.getUserPassword()
                + "@" + mgoSource.getIpAddress() + ":" + mgoSource.getPortNumber() + "/" + mgoSource.getDbName();
        if (debugMode) {
            System.out.println("connectionString:" + connectionString);
        }
        mongoSourceClient = new MongoClient(new MongoClientURI(connectionString));

        System.out.println("Connexion � la base de donn�es source : " + mgoSource.getDbName());
        mongoSourceDatabase = mongoSourceClient.getDatabase(mgoSource.getDbName());

        System.out.println("Ouverture de la connexion au serveur MongoDb de destination : " + mgoDestination.getName());
        mongoDestinationClient = new MongoClient(mgoDestination.getIpAddress(), (int) mgoDestination.getPortNumber());

        System.out.println("Connexion � la base de donn�es de destination : " + mgoDestination.getDbName());
        mongoDestinationDatabase = mongoDestinationClient.getDatabase(mgoDestination.getDbName());

        System.out.println("Lecture des donn�es ...");
        openingTicketPurposeRef = readSourceData(mongoSourceDatabase);

        System.out.println("Conversion du �f�rentiel en liste de motifs d'ouverture de ticket ...");
        otpMappings = new OtpMappings(clientCompanyUuid, openingTicketPurposeRef);

        System.out.println("Ecriture des donn�es ...");
        writeDestinationData(mongoDestinationDatabase, otpMappings);

    }

    /**
     * R�cup�re les param�tres en ligne de commande
     *
     * @param args arguments en ligne de commande
     */
    private void getArgs(String[] args) throws GetArgsException {
        int i;
        int n;
        int ip1;
        String currentParam;
        String nextParam;

        n = args.length;
        System.out.println("nargs=" + n);
        for (i = 0; i < n; i++) {
            System.out.println("args[" + i + "]=" + args[i]);
        }
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            currentParam = args[i];
            ip1 = i + 1;
            nextParam = (ip1 < n) ? args[ip1] : null;
            switch (currentParam) {
                case "-source":
                    if (ip1 < n) {
                        if (ValidServers.isAValidServer(nextParam)) {
                            this.sourceServerType = nextParam;
                        } else {
                            throw new GetArgsException("ERREUR : Mauvais serveur Mongo source : " + nextParam);
                        }
                        i = ip1;
                    } else {
                        throw new GetArgsException("ERREUR : Serveur Mongo source non d�fini");
                    }
                    break;
                case "-destination":
                    if (ip1 < n) {
                        if (ValidServers.isAValidServer(nextParam)) {
                            this.destinationServerType = nextParam;
                        } else {
                            throw new GetArgsException("ERREUR : Mauvais serveur Mongo de destination : " + nextParam);
                        }
                        i = ip1;
                    } else {
                        throw new GetArgsException("ERREUR : Serveur Mongo de destination non d�fini");
                    }
                    break;
                case "-u":
                    if (ip1 < n) {
                        try {
                            this.unum = Integer.parseInt(nextParam);
                            i = ip1;
                        } catch (Exception exception) {
                            throw new GetArgsException("L'identifiant du service d'urgence doit �tre num�rique : " + nextParam);
                        }

                    } else {
                        throw new GetArgsException("ERREUR : Identifiant du service d'urgence non d�fini");
                    }
                    break;
                case "-clientCompanyUuid":
                    if (ip1 < n) {
                        this.clientCompanyUuid = nextParam;
                        i = ip1;
                    } else {
                        throw new GetArgsException("ERREUR : Identifiant UUID du service d'urgence non d�fini");
                    }
                    break;
                case "-d":
                    setDebugMode(true);
                    break;
                case "-t":
                    setTestMode(true);
                    break;
                default:
                    usage();
                    throw new GetArgsException("ERREUR : Mauvais param�tre : " + currentParam);
            }
            i++;
        }

        if (unum > 0) {
            if (clientCompanyUuid != null) {
                System.out.println("unum:" + unum + ", clientCompanyUuid:" + clientCompanyUuid);
                throw new GetArgsException("ERREUR : Veuillez choisir unum ou uuid");
            }
        } else {
            throw new GetArgsException("ERREUR : Veuillez fournir unum ou uuid");
        }

        if (unum > 0 && clientCompanyUuid != null) {
            System.out.println("unum:" + unum + ", clientCompanyUuid:" + clientCompanyUuid);
            throw new GetArgsException("ERREUR : Veuillez choisir unum ou uuid");
        } else {
            clientCompanyUuid = Md5.encode("u:" + unum);
        }
    }

    /**
     * Affiche le mode d'utilisation du programme.
     */
    public static void usage() {
        System.out.println("Usage : java UpdOtpMapping"
                + " [-source prod|pre-prod|dev]"
                + " [-destination prod|pre-prod|dev]"
                + " -u unum|-clientCompanyUuid uuid"
                + " [-d] [-t]");
    }

    /**
     * @return debugMode : retourne le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        UpdOtpMapping.debugMode = debugMode;
    }

    /**
     * @return testMode : retourne le mode de fonctionnement test.
     */
    public boolean getTestMode() {
        return (testMode);
    }

    /**
     * @param testMode : fonctionnement du programme en mode test (true/false).
     */
    public void setTestMode(boolean testMode) {
        UpdOtpMapping.testMode = testMode;
    }

    /**
     * @param args arguments en ligne de commande
     */
    public static void main(String[] args) {
        UpdOtpMapping updOtpMapping;
        SafeUid uid;

        System.out.println("Lancement de UpdOtpMapping ...");
        try {
            updOtpMapping = new UpdOtpMapping(args);
        } catch (GetArgsException | IOException | DBServerException exception) {
            Logger.getLogger(UpdOtpMapping.class.getName()).log(Level.SEVERE, null, exception);
            //            Logger.getLogger(ExpPatrimonies.class.getName()).log(Level.INFO, null, exception);
        }

        System.out.println("Fin de UpdOtpMapping.");

    }

    /**
     * Lit les donn�es source
     *
     * @param mongoDatabase base de donn�es � lire
     * @return openingTicketPurposeRef le r�f�rentiel du client
     */
    public OpeningTicketPurposeRef readSourceData(MongoDatabase mongoDatabase) {

        ObjectMapper objectMapper;
        BasicDBObject filter;
        MongoCursor<Document> openingTicketPurposeRefCursor;
        int n;
        OpeningTicketPurposeRef openingTicketPurposeRef;

        objectMapper = new ObjectMapper();
        MongoCollection<Document> openingTicketPurposeRefCollection = mongoDatabase.getCollection("openingTicketPurposeRef");
        System.out.println(openingTicketPurposeRefCollection.count() + " openingTicketPurposeRefs");

        filter = new BasicDBObject("companyUid", clientCompanyUuid);
        if (debugMode) {
            System.out.println("filter:" + filter);
        }
        openingTicketPurposeRefCursor = openingTicketPurposeRefCollection.find(filter).iterator();

        n = 0;
        openingTicketPurposeRef = null;
        try {
            while (openingTicketPurposeRefCursor.hasNext()) {
//            System.out.println("Json:" + openingTicketPurposeRefCursor.next().toJson());
                openingTicketPurposeRef = objectMapper.readValue(openingTicketPurposeRefCursor.next().toJson(), OpeningTicketPurposeRef.class);
                System.out.println("openingTicketPurposeRef:" + openingTicketPurposeRef);
            }
        } catch (IOException ex) {
            Logger.getLogger(UpdOtpMapping.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            openingTicketPurposeRefCursor.close();
        }
        return openingTicketPurposeRef;
    }

    /**
     * Ecrit les donn�es dans la base de donn�es de destination
     *
     * @param mongoDatabase base de donn�es o� �crire
     * @param optMappings liste de motifis d'ouverture de ticket
     */
    public void writeDestinationData(MongoDatabase mongoDatabase, OtpMappings optMappings) {
        String json;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(optMappings);
            System.out.println("    json(optMappings):" + json);

        } catch (IOException ex) {
            Logger.getLogger(UpdOtpMapping.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Retourne le contenu de UpdOtpMapping
     *
     * @return retourne le contenu de UpdOtpMapping
     */
    @Override
    public String toString() {
        return "UpdOtpMapping:{"
                + "sourceServerType:" + sourceServerType
                + ", destinationServerType:" + destinationServerType
                + ", unum:" + unum
                + ", clientCompanyUuid:" + clientCompanyUuid
                + ", debugMode:" + debugMode
                + ", testMode:" + testMode
                + "}";
    }

}
