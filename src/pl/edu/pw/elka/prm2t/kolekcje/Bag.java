/**
 * 
 */
package pl.edu.pw.elka.prm2t.kolekcje;

import java.util.Iterator;

/**
 * Generyczny wielozbiór. Obsługuje wstawianie elementów i iterowanie po wszystkich elementach. Nie można usuwać
 * elementów.
 */
public interface Bag<Item> extends Iterable<Item> {
    /**
     * Zwraca {@code true} gdy wielozbiór jest pusty.
     */
	default boolean isEmpty() {
		return getSize() == 0;
	}
	
    /**
     * Zwraca liczbę elementów w wielozbiorze.
     */
    int getSize();

    /**
     * Dodaje element do wielozbioru.
     */
    void add(final Item item);
    
    /**
     * Zwraca iterator, przechodzący przez wszystkie elementy wielozbioru.
     */
    Iterator<Item> iterator();
    
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("[");
//        for (final Item i : this) {
//            sb.append(i).append(" ");
//        }
//        if (sb.charAt(sb.length() - 1) == ' ') {
//            sb.delete(sb.length() - 1, sb.length());
//        }
//        sb.append("]");
//        return sb.toString();
//    }
}