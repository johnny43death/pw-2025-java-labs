package pl.edu.pw.elka.prm2t.zadind;

import java.util.ArrayList;
import java.util.Scanner;

class Gosc {
    private String name;
    private boolean inviteSent;
    private boolean attendanceConfirmed;

    public Gosc(String name, boolean inviteSent, boolean attendanceConfirmed){
        this.name = name;
        this.inviteSent = inviteSent;
        this.attendanceConfirmed = attendanceConfirmed;
    }

    @Override
    public String toString(){
        return "| Imię i nazwisko: "+ name +" | Czy wysłano zaproszenie: "+(inviteSent?"Wysłano":"Nie wysłano")+" | Czy gość potwierdził obecność: "+(attendanceConfirmed?"Potwierdził":"Nie potwierdził");
    }

    public boolean isInviteSent() {
        return inviteSent;
    }
}

class Lista {
    private static ArrayList<Gosc> lista = new ArrayList<>();
    void addGuest(String name, boolean inviteSent, boolean attendanceConfirmed) {
        lista.add(new Gosc(name, inviteSent, attendanceConfirmed));
    }
    void removeGuest(int i){
        lista.remove(i);
    }
    void display(){
        if(lista.isEmpty()){
            System.out.println("Brak osób zaproszonych");
        }else{
            System.out.println("\n Lista zaproszonych uczestników: ");
            int i = 0;
            for (Gosc Gosc : lista){
                System.out.println(i++ + ". " + Gosc);
            }
        }
    }
    void select(){
        boolean select = false;
        for (Gosc Gosc : lista){
            if(Gosc.isInviteSent()){
                System.out.println("Lista zaproszonych: ");
                System.out.println(Gosc);
                select = true;
            }
        }
        if (!select) {
            System.out.println("Brak zaproszonych osób.");
        }
    }
}

public class ListaGosci {
    public static void main(String[] args) {
        Lista lista1 = new Lista();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Dodaj osobę");
            System.out.println("2. Wyświetl listę");
            System.out.println("3. Wyświetl listę zaproszonych");
            System.out.println("4. Wyślij zaproszenie");
            System.out.println("5. Zakończ");
            System.out.println("Wybierz opcję: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Imię i Nazwisko: ");
                    String name = scanner.nextLine();
                    System.out.println("Czy wysłano zaproszenie: ");
                    System.out.println("1. Tak, 2. Nie");
                    int input = scanner.nextInt();
                    boolean inviteSent = false;
                    if (input == 1) {
                        inviteSent = true;
                    } else if (input == 2) {
                        inviteSent = false;
                    } else {
                        System.out.println("Błędna wartość!");
                    }
                    System.out.println("Czy zaproszony potwierdził obecność: ");
                    System.out.println("1. Tak, 2. Nie");
                    input = scanner.nextInt();
                    boolean attendanceConfirmed = false;
                    if (input == 1) {
                        attendanceConfirmed = true;
                    } else if (input == 2) {
                        attendanceConfirmed = false;
                    } else {
                        System.out.println("Błędna wartość!");
                    }
                    lista1.addGuest(name, inviteSent, attendanceConfirmed);
                    break;
                case 2:
                    lista1.display();
                    break;
                case 3:
                    lista1.select();
                    break;
                case 4:
                    System.out.println("Podaj indeks osoby, którą chcesz usunąć: ");
                    int i = scanner.nextInt();
                    lista1.removeGuest(i);
                    System.out.println("Usunięto osobę");
                    break;
                case 5:
                    System.out.println("Dziękuję za użycie");
                    break;
                default:
                    System.out.println("Nieprawidłowe dane!");
            }
        }
        while (choice != 5);
    }
}
