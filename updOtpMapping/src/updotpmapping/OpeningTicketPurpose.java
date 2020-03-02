package updotpmapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe d�crivant un motif d'ouverture de ticket
 *
 * @author Thierry Baribaud
 * @version 0.02
 * @see http://performanceimmo.github.io/API/#openingticketpurpose
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningTicketPurpose {

    /**
     * Identifiant unique du motif d'ouverture de ticket
     */
    private String uid;

    /**
     * Libell� du motif d'ouverture de ticket
     */
    private String label;

    /**
     * Identifiant externe venant du logiciel m�tier
     */
    private String extId;
    
    /**
     * Liste d'identitiants d'activit�s
     */
    private Activities activities;
    
    /**
     * @return l'identifiant unique du motif d'ouverture de ticket
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid d�finit l'dentifiant unique du motif d'ouverture de ticket
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return le libell� du motif d'ouverture de ticket
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label d�finit le libell� du motif d'ouverture de ticket
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
     * @param extId d�finit l'identifiant externe du motif d'ouverture de ticket
     */
    public void setExtId(String extId) {
        this.extId = extId;
    }

    /**
     * @return une liste d'identifiants d'activit�s
     */
    public Activities getActivities() {
        return activities;
    }

    /**
     * @param activities d�finit une liste d'identifiants d'activit�s
     */
    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "OpeningTicketPurpose:{"
                + " uid:" + getUid()
                + ", label:" + getLabel()
                + ", extId:" + getExtId()
                + ", activities:" + getActivities()
                + "}";
    }

}
