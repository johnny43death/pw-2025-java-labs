package pl.edu.pw.elka.prm2t.lab5;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WordsCounterTest {

    @Test
    void testReadFile() {
        WordsCounter wordsCounter = new WordsCounter();
        wordsCounter.readFile("resource/input_words.txt");

        List<String> expectedWordList = Arrays.asList("rower", "koło", "szprycha", "motorower", "rower", "samochód", "samochód", "koło", "kierownica", "kierowca", "kierowca", "kierownica", "kierownik");
        assertEquals(expectedWordList, wordsCounter.wordList);
    }

    @Test
    void testCounter() {
        WordsCounter wordsCounter = new WordsCounter();
        wordsCounter.wordList.addAll(Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple"));

        wordsCounter.counter();

        TreeMap<String, Integer> expectedWordCount = new TreeMap<>();
        expectedWordCount.put("apple", 3);
        expectedWordCount.put("banana", 2);
        expectedWordCount.put("orange", 1);

        assertEquals(expectedWordCount, wordsCounter.wordCount);
    }

    @Test
    void testWriteFile() {
        WordsCounter wordsCounter = new WordsCounter();
        wordsCounter.wordCount.put("apple", 3);
        wordsCounter.wordCount.put("banana", 2);
        wordsCounter.wordCount.put("orange", 1);

        wordsCounter.writeFile("resource/test_output_words.txt");
    }

    @Test
    void testEquals() {
        WordsCounter wordsCounter1 = new WordsCounter();
        wordsCounter1.wordList.addAll(Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple"));
        wordsCounter1.counter();

        WordsCounter wordsCounter2 = new WordsCounter();
        wordsCounter2.wordList.addAll(Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple"));
        wordsCounter2.counter();

        assertTrue(wordsCounter1.equals(wordsCounter2));
    }
}