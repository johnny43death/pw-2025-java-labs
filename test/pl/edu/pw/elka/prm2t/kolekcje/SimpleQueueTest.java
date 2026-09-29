package pl.edu.pw.elka.prm2t.kolekcje;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.elka.prm2t.kolekcje.SimpleQueue;

import java.util.Iterator;

/**
 * @author Bartosz Żak, Kajetan Rosik, Piotr Gramburg
 */
class SimpleQueueTest {

    private final SimpleQueue simpleQueue = new SimpleQueue();

    @BeforeEach
    void init() {

    }

    @Test
    void testEnqueue() {
        simpleQueue.enqueue("item");
        Object result = simpleQueue.isEmpty();
        Assertions.assertEquals(false,result);
    }

    @Test
    void testDequeue() {
        simpleQueue.enqueue(10);
        simpleQueue.enqueue(20);
        simpleQueue.dequeue();
        Object result = simpleQueue.getSize();
        Assertions.assertEquals(1, result);
    }
    @Test
    void testGetSize(){
        simpleQueue.enqueue(1);
        simpleQueue.enqueue(2);
        simpleQueue.enqueue(3);
        int result = simpleQueue.getSize();
        Assertions.assertEquals(3,result);
    }

    @Test
    void testIsEmpty() {
        boolean result = simpleQueue.isEmpty();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testPeek() {
        Integer a = 10;
        Integer b = 20;
        simpleQueue.enqueue(a);
        simpleQueue.enqueue(b);
        Object result = simpleQueue.peek();
        Assertions.assertEquals(10,result);
    }
    @Test
    void testIterator(){
        Iterator iterator = simpleQueue.iterator();
    }
}