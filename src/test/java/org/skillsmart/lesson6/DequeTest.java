package org.skillsmart.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void testAddFront() {
        Deque deque = new Deque<>();
        deque.addFront(3);
        assertEquals(1, deque.size());
        assertEquals(3, deque.deque.get(0));
        deque.addFront("two");
        assertEquals(2, deque.size());
        assertEquals(3, deque.deque.get(0));
        deque.addFront(1);
        assertEquals(3, deque.size());
        assertEquals(3, deque.deque.get(0));
        assertEquals("two", deque.deque.get(1));
        assertEquals(1, deque.deque.get(2));
        assertEquals(0, deque.size());
    }

    @Test
    void testAddTail() {
        Deque deque = new Deque<>();
        deque.addTail(1);
        assertEquals(1, deque.size());
        assertEquals(1, deque.deque.get(0));
        deque.addTail("two");
        assertEquals(2, deque.size());
        assertEquals("two", deque.deque.get(0));
        deque.addTail(3);
        assertEquals(3, deque.size());
        assertEquals(3, deque.deque.get(0));
        assertEquals("two", deque.deque.get(1));
        assertEquals(1, deque.deque.get(2));
        assertEquals(0, deque.size());
    }

    @Test
    void testRemoveFront() {
        Deque deque = new Deque<>();
        deque.addFront(1);
        deque.addFront("two");
        deque.addFront(3);
        assertEquals(3, deque.size());
        assertEquals(3, deque.removeFront());
        assertEquals(2, deque.size());
        assertEquals("two", deque.removeFront());
        assertEquals(1, deque.size());
        assertEquals(1, deque.removeFront());
        assertEquals(0, deque.size());
        assertNull(deque.removeFront());
        assertEquals(0, deque.size());
    }

    @Test
    void testRemoveTail() {
        Deque deque = new Deque<>();
        deque.addFront(3);
        deque.addFront("two");
        deque.addFront(1);
        assertEquals(3, deque.size());
        assertEquals(3, deque.removeTail());
        assertEquals(2, deque.size());
        assertEquals("two", deque.removeTail());
        assertEquals(1, deque.size());
        assertEquals(1, deque.removeTail());
        assertEquals(0, deque.size());
        assertNull(deque.removeTail());
        assertEquals(0, deque.size());
    }

    @Test
    void testMixFrontTail() {
        Deque deq = new Deque();
        deq.addFront("f1");
        deq.addTail("t1");
        deq.addFront("f2");
        deq.addTail("t2");
        assertEquals("f2", deq.removeFront());
        assertEquals("t2", deq.removeTail());
        assertEquals("f1", deq.removeFront());
        assertEquals("t1", deq.removeTail());
    }
}