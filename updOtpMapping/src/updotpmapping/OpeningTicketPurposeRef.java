package updotpmapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe décrivant le référentiel des motifs d'ouverture de ticket
 *
 * @author Thierry Baribaud
 * @version 0.02
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningTicketPurposeRef {

    /**
     * Liste des tags 
     * A détailler plus tard ...
     */
    
    /**
     * Liste des catégories 
     * A détailler plus tard ...
     */
    
    /**
     * Liste des motifs d'ouverture de ticket
     */
    private OpeningTicketPurposes openingTicketPurposes;

    /**
     * Motif d'ouverture de ticket par défaut A détailler plus tard ...
     */

    /**
     * @return la liste de motifs d'ouverture de ticket
     */
    public OpeningTicketPurposes getOpeningTicketPurposes() {
        return openingTicketPurposes;
    }

    /**
     * @param openingTicketPurposes définit liste de motifs d'ouverture de
     * ticket
     */
    public void setOpeningTicketPurposes(OpeningTicketPurposes openingTicketPurposes) {
        this.openingTicketPurposes = openingTicketPurposes;
    }

    /**
     * @return Retourne l'objet sous forme textuelle
     */
    @Override
    public String toString() {
        return "OpeningTicketPurposeRef:{"
                //                + "tags:" + getTags()
                //                + ",categories:" + getCategories()
                + "openingTicketPurposes:" + getOpeningTicketPurposes()
                //                + ", default:" + getDefault()
                + "}";
    }
}
