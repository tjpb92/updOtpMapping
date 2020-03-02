package updotpmapping;

import java.util.regex.*;

/**
 * Classe décrivant un identifiant unique de type UUID.
 *
 * @author Thierry Baribaud
 * @version 0.03
 */
public class SafeUid {

    /**
     * Identifiant unique
     */
    private String uid;

    /**
     * Contructeur principal de la classe
     *
     * @param uid identifiant unique
     * @throws updotpmapping.SafeUidException en cas de mauvais uid.
     */
    public SafeUid(String uid) throws SafeUidException {
        String pattern = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";
//        System.out.println("pattern:" + pattern + ", uid:" + uid);
        if (uid == null) {
            throw new SafeUidException("ERREUR : uid non défini");
        } else if (uid.length() != 36) {
            throw new SafeUidException("ERREUR : mauvaise longueur de l'uid");
        } else if (!Pattern.matches(pattern, uid)) {
            throw new SafeUidException("ERREUR : mauvais format de l'uid");
        } else {
            this.uid = uid;
        }
    }

    /**
     * @return uid l'identifiant unique
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid définit l'identifiant unique
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Retourne l'objet sous forme textuelle
     * 
     * @return l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "SafeUid:{"
                + "uid:" + getUid()
                + "}";
    }
}
