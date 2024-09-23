package org.skillsmart.lesson5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueByStacksTest {

    @Test
    void enqueue() {
        QueueByStacks<Integer> queue = new QueueByStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals(2, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals(3, queue.dequeue());
        assertEquals(0, queue.size());
        assertNull(queue.dequeue());
        assertEquals(0, queue.size());
    }
}