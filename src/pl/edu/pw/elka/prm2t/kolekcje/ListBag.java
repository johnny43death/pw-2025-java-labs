/**
 * 
 */
package pl.edu.pw.elka.prm2t.kolekcje;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author kmi
 * @version 1.0.0 21 czerwca 2020 17:04
 */
public class ListBag<Item> extends AbstractBag<Item> implements Bag<Item> {
	/**
     * Bieżący rozmiar wielozbioru.
     */
    private int size;

    /**
     * Pierwszy element wielozbioru.
     */
    private Node first;

    /**
     * Reprezentacja elementu listy wiązanej, przechowującej elementy wielozbioru.
     */
    private class Node {
        Item item = null;
        Node next = null;
    }

    /**
     * Tworzenie pustego wielozbioru.
     */
    public ListBag() {
        first = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return first == null; // return size == 0; - to też jest dobre wyjście
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
        final Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    /**
     * Zwraca iterator, przechodzący przez wszystkie elementy wielozbioru.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Iterator listy węzłów przechowujących elementy wielozbioru.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * Aktualnie iterowany węzeł.
         */
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * @param args nieużywane.
     */
    public static void main(final String... args) {
        final Bag<Integer> b = new ListBag<>();
        System.out.println(b);
        b.add(5);
        System.out.println(b);
        b.add(15);
        System.out.println(b);
        for (int i : b) {
        	System.out.printf("%d, ", i);
        }
        System.out.println();
        
        Bag<String> bs = new ListBag<>();
        bs.add("Ania"); System.out.println(bs);
        bs.add("Kasia"); System.out.println(bs);
        bs.add("Michał"); System.out.println(bs);
        
        for (String s : bs) {
        	System.out.printf("%s, ", s);
        }
        System.out.println();
    }
}