package updotpmapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe décrivant un motif d'ouverture de ticket
 *
 * @author Thierry Baribaud
 * @version 0.03
 * @see <a href="http://performanceimmo.github.io/API/#openingticketpurpose">OpeningTicketPurpose</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningTicketPurpose {

    /**
     * Identifiant unique du motif d'ouverture de ticket
     */
    private String uid;

    /**
     * Libellé du motif d'ouverture de ticket
     */
    private String label;

    /**
     * Identifiant externe venant du logiciel métier
     */
    private String extId;
    
    /**
     * Liste d'identitiants d'activités
     */
    private Activities activities;
    
    /**
     * @return uid l'identifiant unique du motif d'ouverture de ticket
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid définit l'identifiant unique du motif d'ouverture de ticket
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return label le libellé du motif d'ouverture de ticket
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
     * @return extId l'identifiant externe du motif d'ouverture de ticket
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
     * @return activities une liste d'identifiants d'activités
     */
    public Activities getActivities() {
        return activities;
    }

    /**
     * @param activities définit une liste d'identifiants d'activités
     */
    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    /**
     * Retourne l'objet sous forme textuelle
     * 
     * @return l'objet sous forme textuelle
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
