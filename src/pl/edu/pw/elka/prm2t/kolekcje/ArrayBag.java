/**
 * 
 */
package pl.edu.pw.elka.prm2t.kolekcje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author kmi
 * @version 1.0.0 21 czerwca 2020 17:04
 */
public class ArrayBag<Item> implements Bag<Item> {
	/**
     * Bieżący rozmiar wielozbioru.
     */
    private int size;

    /**
     * Tablica z elementami wielozbioru.
     */
    private final List<Item> bagItems = new ArrayList<>();

    /**
     * Tworzenie pustego wielozbioru.
     */
    public ArrayBag() {
        size = 0;
    }

    /**
     * Zwraca liczbę elementów w wielozbiorze.
     */
    public int getSize() {
        return size;
    }

    /**
     * Dodaje element do wielozbioru.
     */
    public void add(final Item item) {
    	bagItems.add(item);
        size++;
    }

    /**
     * Zwraca iterator, przechodzący przez wszystkie elementy wielozbioru.
     */
    public Iterator<Item> iterator() {
        return bagItems.listIterator();
    }


    /**
     * @param args nieużywane.
     */
    public static void main(final String... args) {
        final Bag<Integer> b = new ArrayBag<>();
        System.out.println(b);
        b.add(5);
        System.out.println(b);
        b.add(15);
        System.out.println(b);
        for (int i : b) {
        	System.out.printf("%d, ", i);
        }
        System.out.println();
        
        Bag<String> bs = new ArrayBag<>();
        bs.add("Ania"); System.out.println(bs);
        bs.add("Kasia"); System.out.println(bs);
        bs.add("Michał"); System.out.println(bs);
        
        for (String s : bs) {
        	System.out.printf("%s, ", s);
        }
        System.out.println();
    }
}