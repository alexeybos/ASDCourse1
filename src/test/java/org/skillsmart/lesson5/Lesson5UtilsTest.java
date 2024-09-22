package org.skillsmart.lesson5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson5UtilsTest {

    Lesson5Utils utils = new Lesson5Utils();

    @Test
    void testRotateQueue() {
        Queue queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue("test");
        queue.enqueue(3);
        utils.rotateQueue(queue, 1);
        assertEquals(3, queue.size());
        assertEquals("test", queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(1, queue.dequeue());
    }

    @Test
    void testRotateQueue_MoreThanSize() {
        Queue queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue("test");
        queue.enqueue(3);
        utils.rotateQueue(queue, 5);
        assertEquals(3, queue.size());
        assertEquals(3, queue.dequeue());
        assertEquals(1, queue.dequeue());
        assertEquals("test", queue.dequeue());
    }

    @Test
    void testTurnQueue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        utils.turnQueue(queue);
        assertEquals(4, queue.size());
        assertEquals(4, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(1, queue.dequeue());
    }

}