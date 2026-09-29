package pl.edu.pw.elka.prm2t.lab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */

class AddressBookTest {
    AddressBook addressBook;

    @BeforeEach
    void setUp() {
        addressBook = new AddressBook();
        addressBook.readFile("resource/input_addressbook.txt");
    }

    @Test
    void testEqualsAndHashCode() {
        String s1 = Arrays.toString(addressBook.getAddresses("Anna Acka"));
        String s2 = Arrays.toString(addressBook.getAddresses("Anna Acka"));
        assertEquals(s1, s2);
        assertTrue( s1.hashCode()==s2.hashCode() );
    }

    @Test
    public void testGetAddresses() {
        AddressBook addressBook = new AddressBook();
        addressBook.addAddress("John Doe", "john@example.com");
        addressBook.addAddress("John Doe", "john.doe@example.com");
        addressBook.addAddress("Jane Smith", "jane@example.com");

        String[] johnAddresses = addressBook.getAddresses("John Doe");
        assertArrayEquals(new String[]{"john@example.com", "john.doe@example.com"}, johnAddresses);

        String[] janeAddresses = addressBook.getAddresses("Jane Smith");
        assertArrayEquals(new String[]{"jane@example.com"}, janeAddresses);

        String[] nonExistingAddresses = addressBook.getAddresses("Non Existing User");
        assertEquals(0, nonExistingAddresses.length);
    }

    @Test
    public void testAddAddress() {
        AddressBook addressBook = new AddressBook();
        addressBook.addAddress("John Doe", "john@example.com");
        addressBook.addAddress("John Doe", "john@example.com"); // Adding duplicate address
        addressBook.addAddress("John Doe", "john.doe@example.com");

        String[] johnAddresses = addressBook.getAddresses("John Doe");
        assertArrayEquals(new String[]{"john@example.com", "john.doe@example.com"}, johnAddresses);
    }
}