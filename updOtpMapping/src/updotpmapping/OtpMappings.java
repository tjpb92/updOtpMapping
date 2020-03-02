package updotpmapping;

import java.util.ArrayList;

/**
 * Classe décrivant une liste de motifs d'ouverture de ticket
 *
 * @author Thierry Baribaud
 * @version 0.02
 */
public class OtpMappings extends ArrayList<OtpMapping> {

    /**
     * Constructeur de la classe OptMappings
     *
     * @param clientCompanyUid identifiant unique du client
     * @param openingTicketPurposeRef référentiel du client
     */
    public OtpMappings(String clientCompanyUid, OpeningTicketPurposeRef openingTicketPurposeRef) {

        OpeningTicketPurposes openingTicketPurposes;
        OtpMapping otpMapping;

        openingTicketPurposes = openingTicketPurposeRef.getOpeningTicketPurposes();
        for (OpeningTicketPurpose openingTicketPurpose : openingTicketPurposes) {
            if (openingTicketPurpose.getExtId() != null) {
                add(new OtpMapping(clientCompanyUid, openingTicketPurpose));
            }
        }

    }
}
