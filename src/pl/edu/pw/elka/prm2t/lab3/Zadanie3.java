package pl.edu.pw.elka.prm2t.lab3;

import java.util.*;

/**
 * @author Jakub Gruszka, Kajetan Rosik
 */

public class Zadanie3 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Proszę podać MAX:");
        int MAX = Integer.parseInt(scan.nextLine());

        System.out.println("Proszę podać N:");
        int N = Integer.parseInt(scan.nextLine());

        System.out.println("Proszę podać S:");
        int S = Integer.parseInt(scan.nextLine());

        Random rand = new Random();
        List<Integer> list = new ArrayList<>();

        do {
            list.add(rand.nextInt(0, MAX));

        } while (!list.contains(S));

        int size = list.size();
        int firstN = Math.min(N, size);
        int lastN = Math.min(N, size);

        System.out.println("Pierwsze " + firstN + " liczby: " + list.subList(0, firstN));
        System.out.println("Ostatnie " + lastN + " liczby: " + list.subList(size - lastN, size));

        TreeSet<Integer> uniqueNumbers = new TreeSet<>(list);
        System.out.println("Posortowana lista: " + uniqueNumbers);

        scan.close();
    }
}
