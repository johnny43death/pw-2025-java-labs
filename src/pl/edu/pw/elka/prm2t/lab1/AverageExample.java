package pl.edu.pw.elka.prm2t.lab1;

import java.util.ArrayList;
import java.util.List;

/**
 * To demonstrate issues with math calculations.
 * @author Kajetan Rosik
 */
public class AverageExample {

    private final List<Integer> values = new ArrayList<>();

    /**
     * Constructs the object to demonstrate the issues with math calculations.
     * @param args numbers as {@link String} table.
     */
    public AverageExample(String[] args) {
        parseArgs(args);
    }

    /**
     * Parses {@link String}s in the {@link String} table.
     * @param args the table of {@link String}s.
     */
    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            values.add(Integer.parseInt(args[i]));
        }
        /* try alternatively:
        for (String arg : args) {
            values.add(Integer.parseInt(arg));
        }*/
    }

    /**
     * To calculate the average of numbers.
     * @return the average calculated.
     */
    public double calculateAverage() {
        double sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        /* try alternatively:
        for (int index = 0; index < values.size(); index++) {
            sum += values.get(index);
        }*/
        return sum / values.size();
    }

    /**
     * Test program.
     * @param args arguments of the program given at the command prompt. Numbers expected, but not explicitly verified.
     */
    public static void main(String[] args) {
        AverageExample averageExample = new AverageExample(args);
        System.out.println(averageExample.calculateAverage());
    }
}
