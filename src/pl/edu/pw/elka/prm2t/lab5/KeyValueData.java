package pl.edu.pw.elka.prm2t.lab5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */

public class KeyValueData {

    public final TreeMap<String, String> data;

    public KeyValueData() {
        this.data = new TreeMap<>();
    }

    public void readFile(String inFile){
        Path path = Paths.get(inFile);
        try {
            List<String> lines = Files.readAllLines(path);
            for(int i=0; i<lines.size(); i++) {
                String[] parts = lines.get(i).split("=");
                String key = parts[0];
                String value = parts[1];
                data.put(key, value);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void writeFile(String outFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write("{\n");
            Iterator<Map.Entry<String, String>> iterator = data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                writer.write(" \"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                if (iterator.hasNext()) {
                    writer.write(",\n");
                }
            }
            writer.write("\n}");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyValueData that)) return false;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "KeyValueData{" +
                "data=" + data +
                '}';
    }

    public static void main(String[] args) {
        KeyValueData keyValueData = new KeyValueData();

        keyValueData.readFile("resource/input_keyvalues.txt");

        System.out.println(keyValueData.data);

        keyValueData.writeFile("resource/output_keyvalues.json");

    }

}