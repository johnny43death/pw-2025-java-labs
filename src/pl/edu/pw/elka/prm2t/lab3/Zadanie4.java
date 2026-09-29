package pl.edu.pw.elka.prm2t.lab3;

import java.util.*;
import java.util.stream.*;

/**
 * @author Jakub Gruszka, Kajetan Rosik
 */

public class Zadanie4 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj dane w formacie: [dane zbioru pierwszego] * | + | – [dane zbioru drugiego]");
        String input = scanner.nextLine();

        String operator = "";
        if (input.contains(" * ")) {
            operator = "*";
        } else if (input.contains(" + ")) {
            operator = "+";
        } else if (input.contains(" - ")) {
            operator = "-";
        }

        String[] parts = input.split(" \\* | \\+ | - ");

        HashSet<Integer> set1 = (HashSet<Integer>) Arrays.stream(parts[0].replace("[", "")
                .replace("]", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        HashSet<Integer> set2 = (HashSet<Integer>) Arrays.stream(parts[1].replace("[", "")
                .replace("]", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());


        if (operator.equals("+")) {
            set1.addAll(set2);
        } else if (operator.equals("-")) {
            set1.removeAll(set2);
        } else if (operator.equals("*")) {
            set1.retainAll(set2);
        }

        System.out.println(set1);

        scanner.close();
    }
}
