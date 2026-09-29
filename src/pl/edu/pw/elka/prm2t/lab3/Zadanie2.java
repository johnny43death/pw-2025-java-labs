package pl.edu.pw.elka.prm2t.lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jakub Gruszka, Kajetan Rosik
 */

public class Zadanie2 {
    public static class Osoba{
        private String name;
        private int age;


        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Imię i wiek osoby: " + name + " " + age;
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        List<Osoba> osoby = new ArrayList<>();

        while(true) {
            System.out.println("Proszę podać Imię");
            String name = scan.nextLine();
            if(name.equals("q")) {
                break;
            }
            System.out.println("Proszę podać wiek");
            String age = scan.nextLine();

            Osoba person = new Osoba();
            person.setName(name);
            person.setAge(Integer.parseInt(age));

            osoby.add(person);
        }
        for(Osoba person : osoby) {
            System.out.println(person.toString());
        }

        scan.close();
    }
}
