package org.skillsmart.lesson5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixQueueTest {

    @Test
    void enqueue_WithOverflow() {
        FixQueue<Integer> fixQueue = new FixQueue<>(3);
        fixQueue.enqueue(1);
        fixQueue.enqueue(2);
        fixQueue.enqueue(3);
        ArrayStoreException e = assertThrowsExactly(ArrayStoreException.class, () -> fixQueue.enqueue(4));
    }

    @Test
    void dequeue_FromMaxToEmpty() {
        FixQueue<Integer> fixQueue = new FixQueue<>(3);
        fixQueue.enqueue(1);
        fixQueue.enqueue(2);
        fixQueue.enqueue(3);
        assertEquals(3, fixQueue.size());
        fixQueue.dequeue();
        fixQueue.dequeue();
        assertEquals(1, fixQueue.size());
        assertEquals(3, fixQueue.dequeue());
        assertEquals(0, fixQueue.size());
        assertNull(fixQueue.dequeue());
        assertEquals(0, fixQueue.size());
        fixQueue.enqueue(1);
        assertEquals(1, fixQueue.size());
        assertEquals(1, fixQueue.dequeue());
        assertEquals(0, fixQueue.size());
    }

    @Test
    void enqueue() {
        FixQueue<Integer> fixQueue = new FixQueue<>(3);
        fixQueue.enqueue(1);
        fixQueue.enqueue(2);
        fixQueue.enqueue(3);
        assertEquals(3, fixQueue.size());
        fixQueue.dequeue();
        assertEquals(2, fixQueue.size());
        fixQueue.dequeue();
        assertEquals(1, fixQueue.size());
        fixQueue.enqueue(4);
        fixQueue.enqueue(5);
        assertEquals(3, fixQueue.size());
        fixQueue.dequeue();
        fixQueue.enqueue(6);
        assertEquals(3, fixQueue.size());
        assertEquals(4, fixQueue.dequeue());
        assertEquals(5, fixQueue.dequeue());
        assertEquals(6, fixQueue.dequeue());
        assertEquals(0, fixQueue.size());
    }

}