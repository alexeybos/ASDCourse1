package org.skillsmart.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeByDynArrayTest {

    /*@Test
    void addFront() {
        DequeByDynArray<Integer> deque = new DequeByDynArray<>();
        deque.addFront(100);
        assertEquals(0, deque.headIndex);
        assertEquals(0, deque.tailIndex);
        deque.addFront(200);
        assertEquals(1, deque.headIndex);
        assertEquals(0, deque.tailIndex);
        deque.addFront(300);
        assertEquals(2, deque.headIndex);
        assertEquals(0, deque.tailIndex);
        assertEquals(3, deque.size);
        for (int i = 0; i < 13; i++) {
            deque.addFront(i);
        }
        assertEquals(15, deque.headIndex);
        assertEquals(0, deque.tailIndex);
        //assertEquals(16, deque.array.capacity);
        assertEquals(16, deque.size);
        deque.addFront(14);
        assertEquals(16, deque.headIndex);
        assertEquals(0, deque.tailIndex);
        //assertEquals(32, deque.array.capacity);
        assertEquals(17, deque.size);
    }

    @Test
    void addTail() {
        DequeByDynArray<Integer> deque = new DequeByDynArray<>();
        deque.addTail(100);
        assertEquals(0, deque.headIndex);
        assertEquals(0, deque.tailIndex);
        deque.addTail(200);
        assertEquals(0, deque.headIndex);
        assertEquals(15, deque.tailIndex);
        for (int i = 0; i < 14; i++) {
            deque.addTail(i);
        }
        assertEquals(0, deque.headIndex);
        assertEquals(1, deque.tailIndex);
        //assertEquals(16, deque.array.capacity);
        assertEquals(16, deque.size);
        deque.addTail(14);
        assertEquals(14, deque.array.getItem(0));
        assertEquals(13, deque.array.getItem(1));
        assertEquals(200, deque.array.getItem(15));
        assertEquals(100, deque.array.getItem(16));
        assertEquals(16, deque.headIndex);
        assertEquals(0, deque.tailIndex);
        //assertEquals(32, deque.array.capacity);
        assertEquals(17, deque.size);
    }

    @Test
    void removeFrontNTail() {
        DequeByDynArray<Integer> deque = new DequeByDynArray<>();
        deque.addFront(100);
        assertEquals(100, deque.removeFront());
        assertEquals(0, deque.size);
        assertNull(deque.removeFront());
        assertNull(deque.removeTail());
        assertEquals(0, deque.size);
        deque.addTail(200);
        assertEquals(200, deque.removeTail());
        assertEquals(0, deque.size);
        assertNull(deque.removeFront());
        assertNull(deque.removeTail());
        assertEquals(0, deque.size);
        deque.addFront(300);
        assertEquals(300, deque.removeTail());
        assertEquals(0, deque.size);
        assertNull(deque.removeFront());
        assertNull(deque.removeTail());
        assertEquals(0, deque.size);
        deque.addTail(400);
        assertEquals(400, deque.removeFront());
        assertEquals(0, deque.size);
        assertNull(deque.removeFront());
        assertNull(deque.removeTail());
        assertEquals(0, deque.size);

        deque.addFront(100);
        deque.addFront(200);
        deque.addTail(400);
        deque.addTail(300);
        assertEquals(200, deque.removeFront());
        assertEquals(100, deque.removeFront());
        assertEquals(400, deque.removeFront());
        assertEquals(300, deque.removeFront());
        assertEquals(deque.headIndex, deque.tailIndex);
        assertNull(deque.removeFront());
        assertNull(deque.removeTail());
        assertEquals(deque.headIndex, deque.tailIndex);

        deque.addFront(100);
        deque.addFront(200);
        deque.addTail(400);
        deque.addTail(300);
        assertEquals(300, deque.removeTail());
        assertEquals(400, deque.removeTail());
        assertEquals(100, deque.removeTail());
        assertEquals(200, deque.removeTail());
        assertEquals(deque.headIndex, deque.tailIndex);
        assertNull(deque.removeFront());
        assertNull(deque.removeTail());
        assertEquals(deque.headIndex, deque.tailIndex);
    }*/

}