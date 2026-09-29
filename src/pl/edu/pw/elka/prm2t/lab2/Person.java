package pl.edu.pw.elka.prm2t.lab2;

/**
 * @author Kajetan Rosik
 */

import java.util.Objects;

public class Person {

    // -----> klasa Person przyjmująca imie, nazwisko i miasto, robiona bez konstruktora tylko z oddzielnie zdefiniowanymi metodami
    private String name;
    private String surname;
    private String city;

    // -----> settery wstawiające wartości w imie, nazwisko i miasto
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setCity(String city) {
        this.city = city;
    }

    // -----> gettery wyświetlające wartości z imie, nazwisko i miasto
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getCity() {
        return city;
    }

    // -----> toString wyświetlająca cały obiekt w formie jednego ciągu znaków
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    // -----> equals porównująca miasta w dwóch obiektach
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return city.equals(person.city);
    }

    // -----> hashCode wyliczająca i wyświetlająca hashcode
    @Override
    public int hashCode() {
        return Objects.hash(city);
    }

    // -----> główna funkcja
    public static void main(String[] args) {
        // -----> generowanie trzech osób do testowania metod
        Person osoba1 = new Person();
        osoba1.setName("Harry");
        osoba1.setSurname("DuBois");
        osoba1.setCity("Martinaise");

        Person osoba2 = new Person();
        osoba2.setName("Harry");
        osoba2.setSurname("DuBois");
        osoba2.setCity("Martinaise");

        Person osoba3 = new Person();
        osoba3.setName("Johnny");
        osoba3.setSurname("Mnemonic");
        osoba3.setCity("Los Angeles");

        // -----> użycie getterów
        System.out.println(osoba1.getName() + " " + osoba1.getSurname() + ", " + osoba1.getCity());

        // -----> wyświetlenie niejawnym toStringiem
        System.out.println(osoba1);

        // -----> porównanie equalsami dwóch takich samych a potem dwóch innych obiektów
        System.out.println(osoba1.equals(osoba2));
        System.out.println(osoba1.equals(osoba3));

        // -----> wyświetlenie hashcode'u
        System.out.println(osoba1.hashCode());
    }
}