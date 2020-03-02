package updotpmapping;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Classe décrivant un motif d'ouverture de ticket
 * Version pour la base de données intermédiaire.
 * @author Thierry Baribaud
 * @version 0.02
 */
public class OtpMapping {

    /**
     * Identifiant unique du motif d'ouverture de ticket
     */
    private String uid;

    /**
     * Libellé du motif d'ouverture de ticket
     */
    private String label;

    /**
     * Identifiant externe venant du logiciel métier (optionnel)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String extId;

    /**
     * Identifiant unique pour l'activité (optionnel)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String activityUid;
    
    /**
     * Identifiant unique du client
     */
    private String clientCompanyUid;
    
    /**
     * Contructeur principal de la classe OtpMapping
     * @param clientCompanyUid identifiant unique du client
     * @param openingTicketPurpose motif d'ouverture du ticket
     */
    public OtpMapping(String clientCompanyUid, OpeningTicketPurpose openingTicketPurpose) {
        Activities activities;
        
        this.clientCompanyUid = clientCompanyUid;
        this.uid = openingTicketPurpose.getUid();
        this.label = openingTicketPurpose.getLabel();
        this.extId = openingTicketPurpose.getExtId();
        activities = openingTicketPurpose.getActivities();
        if (activities.size() > 0) {
            this.activityUid = activities.get(0).getUid();
        }
    }
    
    /**
     * @return l'identifiant unique du motif d'ouverture de ticket
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid définit l'dentifiant unique du motif d'ouverture de ticket
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return le libellé du motif d'ouverture de ticket
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label définit le libellé du motif d'ouverture de ticket
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return l'identifiant externe du motif d'ouverture de ticket
     */
    public String getExtId() {
        return extId;
    }

    /**
     * @param extId définit l'identifiant externe du motif d'ouverture de ticket
     */
    public void setExtId(String extId) {
        this.extId = extId;
    }

    /**
     * @return l'identifiant unique de l'activité
     */
    public String getActivityUid() {
        return activityUid;
    }

    /**
     * @param activityUid définit l'identifiant unique de l'activité
     */
    public void setActivityUid(String activityUid) {
        this.activityUid = activityUid;
    }

    /**
     * @return l'identifiant unique du client
     */
    public String getClientCompanyUid() {
        return clientCompanyUid;
    }

    /**
     * @param clientCompanyUid définit l'identifiant unique du client
     */
    public void setClientCompanyUid(String clientCompanyUid) {
        this.clientCompanyUid = clientCompanyUid;
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "OptMapping:{"
                + " uid:" + getUid()
                + ", label:" + getLabel()
                + ", extId:" + getExtId()
                + ", activityUid:" + getActivityUid()
                + ", clientCompanyUid:" + getClientCompanyUid()
                + "}";
    }
}
