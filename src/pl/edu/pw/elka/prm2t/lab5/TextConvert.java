package pl.edu.pw.elka.prm2t.lab5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author Igor Kutermankiewicz
 * @author Kajetan Rosik
 */


public class TextConvert {

    public final List<String> paragraphs;
    public TextConvert() {
        this.paragraphs = new ArrayList<>();
    }

    public void readFile(String inFile){
        Path path = Paths.get(inFile);
        String elem = new String();
        try {
            List<String> lines = Files.readAllLines(path);
            for(int i=0; i<lines.size(); i++) {
                if (lines.get(i).endsWith(".") ){
                    elem = elem + lines.get(i);
                    paragraphs.add(elem);
                    elem = "";
                }else{
                    elem = elem + lines.get(i);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void writeFile(String outFile){
        Path path = Paths.get(outFile);
        try {
            Files.write(path, paragraphs, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String toString() {
        return "TextConvert{" +
                "paragraphs=" + paragraphs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextConvert that)) return false;
        return Objects.equals(paragraphs, that.paragraphs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paragraphs);
    }

    public String getParagraph(int num) {
        if (num >= 0 && num < paragraphs.size()) {
            return paragraphs.get(num);
        }
        return null;
    }

    public static void main(String[] args) {
        TextConvert textConvert = new TextConvert();

        textConvert.readFile("resource/input.txt");
        textConvert.writeFile("resource/output.txt");

        System.out.println(textConvert);
        System.out.println(textConvert.getParagraph(1));
    }

}