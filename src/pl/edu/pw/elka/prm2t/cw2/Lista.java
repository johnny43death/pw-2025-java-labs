package pl.edu.pw.elka.prm2t.cw2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static pl.edu.pw.elka.prm2t.GeneralUtil.prn;

/**
 * Przykładowa realizacja zadania z listą:
 * </br>
 * Napisz klasę Lista, której obiekty przechowują listę liczb całkowitych. Klasa ma następujące
 * pola prywatne:</br>
 * private int [] liczby; – tablica, w której przechowywane są liczby</br>
 * private int rozmiar; – aktualna liczba przechowywanych elementów</br>
 * </br>
 * Klasa ma następujące metody:</br>
 * 1. Konstruktor z parametrem określającym maksymalną pojemność listy, który przydziela pamięć
 * dla tablicy liczb.</br>
 * 2. Metodę boolean dodajElement(), która przyjmuje jeden argument typu int – liczbę dodawaną
 * do listy; metoda zwraca true gdy udało się dodać element i false, gdy lista jest pełna i nie
 * można dodać kolejnego elementu.</br>
 * 3. Metodę int znajdz(), której parametrem jest szukana liczba, zaś rezultatem indeks liczby
 * w liście lub -1, gdy liczba nie występuje w liście.</br>
 * 4. Metodę boolean usuń(), która usuwa pierwsze wystąpienie podanej jako parametr liczby, jeżeli
 * znajduje się ona na liście, tzn. jeżeli podana liczba występuje więcej niż jeden raz, to usuwane
 * jest jedynie pierwsze jej wystąpienie;</br>
 * 5. Metodę toString() która zwraca zawartość listy w postaci napisu, np. „3 43 1 -23 -1” oraz
 * metody equals() i hashCode() wg standardowych zasad.</br>
 * </br>
 * Komentarze dokumentujące napisz tylko w miejscach nieoczywistych. Napisz prosty program testujący
 * wszystkie metody.
 * @author Michał Sergiel, Kajetan Rosik
 */
public class Lista {
	
	/**
	 * Aktualna liczba elementów w liście.
	 */
	private int rozmiar;
	
    /**
     * Tablica przechowywanych liczb.
     */
    private final int[] liczby;
    
    /**
     * Konstruuje listę z maksymalną pojemnością określoną argumentem.
     * @param pojemność maksymalna pojemność tej listy.
     */
    public Lista(int pojemność) {
    	liczby = new int[pojemność];
        //rozmiar = 0;
    }

    /**
     * Dodaje liczbę do tej listy.
     * @param element dodawana liczba.
     * @return {@code true} gdy dodano element, {@code false} gdy nie udało
     * 		się dodać elementu ze względu na osiągnięcie maksymalnej pojemności.
     */
    public boolean dodajElement(int element) {
    	if (rozmiar == liczby.length) {
    		return false;
    	}
    	liczby[rozmiar++] = element;
    	return true;
    }

    /**
     * Zwraca indeks pierwszego wystąpienia podanej liczby w liście, albo -1 jeśli liczba
     * nie występuje w liście.
     * @param liczba poszukiwana liczba
     * @return indeks podanej liczby lub -1 gdy liczba nie występuje w liście.
     */
    public int znajdz(int liczba){
        /*int i = 0;
        for (int l : liczby) {
            if (l == liczba) {
                return i;
            }
            i++;
        }*/

        for (int i = 0; i < rozmiar; i++) {
            if (liczby[i] == liczba) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Usuwa pierwsze wystąpienie liczby w liście.
     * @param liczba liczba do usunięcia.
     * @return true gdy znaleziono i pomyślnie usunięto liczbę z listy, false gdy liczba
     * 			nie występuje w liście.
     */
    public boolean usun(int liczba) {
    	int indx = znajdz(liczba);
    	if (indx < 0) {
    		return false;
    	}
    	rozmiar--;
    	System.arraycopy(liczby, indx + 1, liczby, indx, rozmiar - indx);
       	return true;
    }

    @Override
    public String toString(){
    	StringBuilder sb = new StringBuilder(        
    			String.format("Lista{pojemność=%d, rozmiar=%d, liczby=[", 
        		liczby.length, rozmiar));
        for (int i = 0; i < rozmiar; i++) {
            sb.append(liczby[i]).append(", ");
        }
        if (rozmiar > 0) {
        	sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o instanceof Lista) {
        	Lista lista = (Lista) o;
        	return rozmiar == lista.rozmiar && Arrays.equals(liczby, lista.liczby);
        }
        return false;
    }

    @Override
    public int hashCode() {
        // Niepoprawna wersja: Objects.hash(rozmiar, liczby) używa domyślnego hashCode() tablicy,
        // co oznacza, że różne obiekty tej samej tablicy mogą mieć różne wartości hashCode.

        // Poprawna wersja: Używamy Arrays.hashCode(liczby), aby obliczyć hash na podstawie zawartości tablicy,
        // a nie jej referencji w pamięci. Dzięki temu obiekt o identycznych danych zwróci ten sam hashCode.
        return Objects.hash(rozmiar, Arrays.hashCode(liczby));
    }
    
    public static void main(String[] args){
    	int v = 3;
        Lista l = new Lista(v);
        prn("l = new Lista(%d), l=%s",  v, l);
        boolean b = l.dodajElement(v = 7);
        prn("l.dodajElement(%d)=%b, l=%s", v, b, l);
        b = l.dodajElement(v = 9);
        prn("l.dodajElement(%d)=%b, l=%s", v, b, l);
        b = l.dodajElement(v = 2);
        prn("l.dodajElement(%d)=%b, l=%s", v, b, l);
        b = l.dodajElement(v = 5);
        prn("dodawanie ponad pojemność, l.dodajElement(%d)=%b, l=%s", v, b, l);
        
        int s = 7;
        int rs = l.znajdz(s);
        prn("wyszukiwanie istniejącego, l.znajdz(%d)=%d", s, rs);
        rs = l.znajdz(s = 9);
        prn("wyszukiwanie istniejącego, l.znajdz(%d)=%d", s, rs);
        rs = l.znajdz(s = 2);
        prn("wyszukiwanie istniejącego, l.znajdz(%d)=%d", s, rs);
        rs = l.znajdz(s = 32);
        prn("wyszukiwanie nieistniejącego, l.znajdz(%d)=%d", s, rs);
        
        b = l.usun(v = 7);
        prn("usuwanie na początku, l.usun(%d)=%b, l=%s", v, b, l);
        b = l.usun(v = 2);
        prn("usuwanie na końcu, l.usun(%d)=%b, l=%s", v, b, l);
        b = l.usun(v = 9);
        prn("usuwanie w przypadku jedynego elementu, l.usun(%d)=%b, l=%s", v, b, l);
        
        l.dodajElement(3);l.dodajElement(5);l.dodajElement(7);
        prn("aktualna lista, l=%s", l);
        b = l.usun(v = 5);
        prn("usuwanie ze środka, l.usun(%d)=%b, l=%s", v, b, l);
        b = l.usun(v = 25);
        prn("usuwanie nieistniejącego elementu, l.usun(%d)=%b, l=%s", v, b, l);
        
        l.dodajElement(v = 14);
        prn("aktualna lista, l=%s", l);
        
        Lista lp1 = new Lista(4);
        Lista lp2 = new Lista(4);
        prn("pusta lista 1, lp1=%s", lp1);
        prn("pusta lista 2, lp2=%s", lp2);
        
        Lista ld1 = new Lista(v = 30);
        Random rd = new Random();
        rd.setSeed(s = 100);
        for (int i = 0; i < v; i++) {
        	ld1.dodajElement(rd.nextInt(30));
        }
        prn("duża lista 1, ld=%s", ld1);
        rd.setSeed(s);
        Lista ld2 = new Lista(v);
        for (int i = 0; i < v; i++) {
        	ld2.dodajElement((rd.nextInt(30)));
        }
        prn("duża lista 2, ld=%s", ld2);
        b = ld1.equals(ld2);
        prn("porównaie identycznych list, ld1.equals(ld2)=%b", b);
        ld2.usun(9);
        ld2.usun(7);
        ld2.usun(23);
        ld2.usun(18);
        
        prn("duża lista 1, ld=%s", ld1);
        prn("duża lista 2, ld=%s", ld2);
        b = ld1.equals(ld2);
        prn("porównaie (prawdopodobnie) różnych list, ld1.equals(ld2)=%b", b);
        
        int h = ld1.hashCode();
        prn("skrót dużej listy 1, ld1.hashCode()=%d", h);
        ld1.usun(v = 4);
        h = ld1.hashCode();
        prn("skrót dużej listy 1 po próbie usunięcia wartości %d, ld1.hashCode()=%d", v, h);
    }
}