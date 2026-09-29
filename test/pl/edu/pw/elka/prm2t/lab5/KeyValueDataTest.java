package pl.edu.pw.elka.prm2t.lab5;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */

class KeyValueDataTest {

    private final Path inputPath = Paths.get("src/test/resources/input_keyvalues.txt");
    private final Path outputPath = Paths.get("src/test/resources/output_keyvalues.json");

    @BeforeEach
    void setUp() throws IOException {
        // Utwórz plik wejściowy do testów
        List<String> lines = List.of(
                "name=John",
                "age=30",
                "city=Warsaw"
        );
        Files.createDirectories(inputPath.getParent());
        Files.write(inputPath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(inputPath);
        Files.deleteIfExists(outputPath);
    }

    @Test
    void testReadFile_populatesDataCorrectly() {
        KeyValueData kvData = new KeyValueData();
        kvData.readFile(inputPath.toString());

        Map<String, String> expected = new TreeMap<>();
        expected.put("name", "John");
        expected.put("age", "30");
        expected.put("city", "Warsaw");

        assertEquals(expected, kvData.data);
    }

    @Test
    void testWriteFile_createsCorrectNumberOfLines() throws IOException {
        KeyValueData kvData = new KeyValueData();
        kvData.readFile(inputPath.toString());
        kvData.writeFile(outputPath.toString());

        List<String> outputLines = Files.readAllLines(outputPath);

        // Dla 3 wpisów powinno być 5 linijek: {, 3 linie z danymi, }
        assertEquals(5, outputLines.size());
    }
}