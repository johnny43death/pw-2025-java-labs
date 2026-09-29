package pl.edu.pw.elka.prm2t.lab5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */

public class WordsCounter {

    public final List<String> wordList;
    public final TreeMap<String, Integer> wordCount;
    public WordsCounter() {
        this.wordList = new ArrayList<>();
        this.wordCount = new TreeMap<>();
    }

    public void readFile(String inFile) {
        Path path = Paths.get(inFile);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    wordList.add(part);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void counter() {
        for (String word : wordList) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
    }

    public void writeFile(String outFile) {
        counter();
        Path path = Paths.get(outFile);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordsCounter that)) return false;
        return Objects.equals(wordList, that.wordList) && Objects.equals(wordCount, that.wordCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordList, wordCount);
    }

    public static void main(String[] args) {

        WordsCounter wordsCounter = new WordsCounter();

        wordsCounter.readFile("resource/input_words.txt");

        System.out.println(wordsCounter.wordList);

        wordsCounter.writeFile("resource/output_words.txt");
    }
}
