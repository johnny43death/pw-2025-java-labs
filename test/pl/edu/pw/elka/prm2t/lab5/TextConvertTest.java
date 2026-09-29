package pl.edu.pw.elka.prm2t.lab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */

class TextConvertTest {

    TextConvert textConvert;

    @BeforeEach
    void setUp() {
        textConvert = new TextConvert();
        textConvert.readFile("resource/input.txt");
    }

    @Test
    void testEqualsAndHashCode() {
        String s1 = textConvert.getParagraph(0);
        String s2 = textConvert.getParagraph(0);
        assertEquals(s1, s2);
        assertTrue( s1.hashCode()==s2.hashCode() );
    }

    @Test
    void getParagraph() {
        String s1 = textConvert.getParagraph(0);
        String s1p = textConvert.paragraphs.get(0);
        assertEquals(s1, s1p);
    }
}