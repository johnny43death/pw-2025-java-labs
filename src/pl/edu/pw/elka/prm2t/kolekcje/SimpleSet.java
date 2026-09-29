package pl.edu.pw.elka.prm2t.kolekcje;

import java.util.AbstractSet;
import java.util.Iterator;

public class SimpleSet<T> extends AbstractSet<T> {
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    int size = 0;

    @Override
    public boolean add(T element) {
        size ++;
        return false;
    }
}
