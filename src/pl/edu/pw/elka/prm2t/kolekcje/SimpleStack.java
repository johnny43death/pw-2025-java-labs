package pl.edu.pw.elka.prm2t.kolekcje;

import static pl.edu.pw.elka.prm2t.GeneralUtil.prn;

public class SimpleStack<Item> {
    public static final int maxSize = 4;

    private final Item[] stack = (Item[]) new Object[maxSize];
    //stack = new Item[poj]; // to niedozwolona konstrukcja
    //zob. J.Bloch, Effective Java 3rd ed, Item 28

    private int top = 0; //pierwsza wolna komórka

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    public Item push(Item item) {
        if (top == stack.length) {
            throw new StackFullException("stack is full, maxSize=" + maxSize);
        }
        return (stack[top++] = item);
    }

    public Item pop() {
        if (isEmpty()) {
            throw new StackEmptyException("stack is empty, maxSize=" + maxSize);
        }
        Item item = stack[--top];
        stack[top] = null; //na potrzeby sprzątania
        return item;
    }

    public static void main(String[] args) {
        SimpleStack<String> simpleStack = new SimpleStack<>();

        String[] imiona = { "Ania", "Janek", "Basia", "Tomek" };
        for (String imię : imiona) {
            prn("push(%s): %s", imię, simpleStack.push(imię));
        }

        while (simpleStack.top != 0) {
            prn("pop(): %s", simpleStack.pop());
        }
    }
}
