package rolodex.src.rolodex;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class RolodexTest {


    public void testContains() {
        Rolodex rolodex = new Rolodex();
        rolodex.addCard("Alice", "123-456-7890");
        assertTrue(rolodex.contains("Alice"));
        assertFalse(rolodex.contains("Bob"));
    }

    public void testSize() {
        Rolodex rolodex = new Rolodex();
        assertEquals(0, rolodex.size());
        rolodex.addCard("Alice", "123-456-7890");
        assertEquals(1, rolodex.size());
        rolodex.addCard("Bob", "234-567-8901");
        assertEquals(2, rolodex.size());
    }

    public void testLookup() {
        Rolodex rolodex = new Rolodex();
        rolodex.addCard("Alice", "123-456-7890");
        rolodex.addCard("Bob", "234-567-8901");
        ArrayList<String> aliceNumbers = rolodex.lookup("Alice");
        assertEquals(1, aliceNumbers.size());
        assertTrue(aliceNumbers.contains("123-456-7890"));
        ArrayList<String> bobNumbers = rolodex.lookup("Bob");
        assertEquals(1, bobNumbers.size());
        assertTrue(bobNumbers.contains("234-567-8901"));
        try {
            rolodex.lookup("Carol");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // pass
        }
    }

    public void testAddCard() {
        Rolodex rolodex = new Rolodex();
        rolodex.addCard("Alice", "123-456-7890");
        assertEquals("Alice\n123-456-7890\n", rolodex.toString());
        try {
            rolodex.addCard("Alice", "234-567-8901");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // pass
        }
    }}
