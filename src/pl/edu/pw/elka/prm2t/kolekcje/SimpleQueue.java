package pl.edu.pw.elka.prm2t.kolekcje;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node next;
    }

    public SimpleQueue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    public Item peek() { // opcjonalna operacja
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return first.item;
    }

    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) {
            first = x;
            last = x;
        } else {
            last.next = x;
            last = x;
        }
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null; // wyciek pamięci
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new FIFOIterator();
    }

    private class FIFOIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}