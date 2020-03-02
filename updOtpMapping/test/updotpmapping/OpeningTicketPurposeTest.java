package updotpmapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Programme de test de la classe OpeningTicketPurpose
 *
 * @author Thierry Baribaud
 * @version 0.20
 */
public class OpeningTicketPurposeTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public OpeningTicketPurposeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * OpeningTicketPurpose.
     */
    @Test
    public void testOpeningTicketPurposeJsonSerialization() {
        System.out.println("OpeningTicketPurposeJsonSerialization");

        String filename = "OpeningTicketPurpose.json";
        String testFilename = "testOpeningTicketPurpose.json";
        OpeningTicketPurpose openingTicketPurpose = null;
        OpeningTicketPurpose expOpeningTicketPurpose = null;
        String json;

        try {
            openingTicketPurpose = objectMapper.readValue(new File(filename), OpeningTicketPurpose.class);
            System.out.println("openingTicketPurpose:" + openingTicketPurpose);
            objectMapper.writeValue(new File(testFilename), openingTicketPurpose);

            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(openingTicketPurpose);
            System.out.println("    json(openingTicketPurpose):" + json);

            expOpeningTicketPurpose = objectMapper.readValue(new File(filename), OpeningTicketPurpose.class);
            System.out.println("expOpeningTicketPurpose:" + expOpeningTicketPurpose);
        } catch (IOException ex) {
            Logger.getLogger(OpeningTicketPurposeTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(openingTicketPurpose);
        assertNotNull(expOpeningTicketPurpose);
        assertEquals(openingTicketPurpose.toString(), expOpeningTicketPurpose.toString());
    }

    /**
     * Test of getUid method, of class OpeningTicketPurpose.
     */
    @Test
    public void testGetUid() {
        System.out.println("getUid");
        OpeningTicketPurpose instance = new OpeningTicketPurpose();
        String expResult = "";
        String result = instance.getUid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUid method, of class OpeningTicketPurpose.
     */
    @Test
    public void testSetUid() {
        System.out.println("setUid");
        String uid = "";
        OpeningTicketPurpose instance = new OpeningTicketPurpose();
        instance.setUid(uid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabel method, of class OpeningTicketPurpose.
     */
    @Test
    public void testGetLabel() {
        System.out.println("getLabel");
        OpeningTicketPurpose instance = new OpeningTicketPurpose();
        String expResult = "";
        String result = instance.getLabel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLabel method, of class OpeningTicketPurpose.
     */
    @Test
    public void testSetLabel() {
        System.out.println("setLabel");
        String label = "";
        OpeningTicketPurpose instance = new OpeningTicketPurpose();
        instance.setLabel(label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class OpeningTicketPurpose.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        OpeningTicketPurpose instance = new OpeningTicketPurpose();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
