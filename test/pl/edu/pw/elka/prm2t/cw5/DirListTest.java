package pl.edu.pw.elka.prm2t.cw5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DirListTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testEmptyDir(@TempDir Path dir) {
        DirList.main(new String[]{dir.toString()});
        assertTrue(out.toString().isBlank());
    }

    @Test
    void testFileListed(@TempDir Path dir) throws IOException {
        new File(dir.toFile(), "plik.txt").createNewFile();
        DirList.main(new String[]{dir.toString()});
        assertTrue(out.toString().contains("plik.txt"));
    }

    @Test
    void testNoArgs() {
        DirList.main(new String[]{});
        assertTrue(err.toString().contains("komenda"));
    }

    @Test
    void testInvalidPath() {
        DirList.main(new String[]{"nie_ma_takiego"});
        assertTrue(err.toString().contains("nie jest katalogiem"));
    }
}
