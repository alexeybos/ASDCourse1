package org.skillsmart.lesson5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void enqueue() {
        Queue qu = new Queue();
        qu.enqueue(1);
        qu.enqueue(2);
        qu.enqueue(3);
        assertEquals(3, qu.size());
    }

    @Test
    void dequeue() {
        Queue qu = new Queue();
        qu.enqueue(1);
        qu.enqueue("test");
        qu.enqueue(3);
        assertEquals(1, qu.dequeue());
        assertEquals(2, qu.size());
        assertEquals("test", qu.dequeue());
        assertEquals(1, qu.size());
        assertEquals(3, qu.dequeue());
        assertEquals(0, qu.size());
        assertNull(qu.dequeue());
    }

    @Test
    void size() {
        Queue qu = new Queue();
        qu.enqueue(1);
        assertEquals(1, qu.size());
        qu.enqueue("test");
        assertEquals(2, qu.size());
        qu.enqueue(3);
        assertEquals(3, qu.size());
        qu.dequeue();
        assertEquals(2, qu.size());
        qu.dequeue();
        assertEquals(1, qu.size());
        qu.dequeue();
        assertEquals(0, qu.size());
        qu.dequeue();
        assertEquals(0, qu.size());
    }
}