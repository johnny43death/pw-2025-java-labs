package pl.edu.pw.elka.prm2t.lab3;

import java.util.Scanner;
import java.util.Hashtable;

/**
 * @author Jakub Gruszka, Kajetan Rosik
 */

public class Zadanie1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Hashtable<String, String> Osoby = new Hashtable<>();

        while(true) {
            System.out.println("Proszę podać nazwę użytkownika");
            String username = scan.nextLine();
            if(username.equals("q")) {
                break;
            }
            System.out.println("Proszę podać hasło");
            String password = scan.nextLine();

            Osoby.put(username, password);
        }
        System.out.println("Proszę podać nazwę użytkownika, którego hasło chcesz sprawdzić");
        String check = scan.nextLine();

        System.out.println(Osoby.getOrDefault(check,
                "Nie ma takiego użytkownika"));

        scan.close();
    }
}
