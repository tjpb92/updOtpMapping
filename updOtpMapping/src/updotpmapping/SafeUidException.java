package updotpmapping;

/**
 * Classe qui définit une exception lancée en cas d'erreur lors de la création d'un identifiant unique.
 *
 * @author Thierry Baribaud
 * @version 0.02
 */
public class SafeUidException extends Exception {

    /**
     * Creates a new instance of <code>SafeUidException</code> without detail
     * message.
     */
    public SafeUidException() {
    }

    /**
     * Constructs an instance of <code>SafeUidException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SafeUidException(String msg) {
        super(msg);
    }
}
