package org.skillsmart.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeByTwoDynArrayTest {

    @Test
    void testAddFront_ToNativeArray() {
        DequeByTwoDynArray deque = new DequeByTwoDynArray<>();
        deque.addFront(1);
        assertEquals(0, deque.headIndex);
        deque.addFront("two");
        assertEquals(1, deque.headIndex);
        deque.addFront(3);
        assertEquals(2, deque.headIndex);
        assertEquals(3, deque.size());
        assertEquals(1, deque.headArray.getItem(0));
        assertEquals("two", deque.headArray.getItem(1));
        assertEquals(3, deque.headArray.getItem(2));
        assertEquals(-1, deque.tailIndex);
    }

    @Test
    void testAddFront_ToAlienArray() {
        DequeByTwoDynArray deque = new DequeByTwoDynArray<>();
        deque.addFront("h1");
        deque.addFront("h2");
        deque.addTail("t1");
        deque.addTail("t2");
        assertEquals(4, deque.size());
        assertEquals("h2", deque.removeFront());
        assertEquals(3, deque.size());
        assertEquals(0, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertEquals("h1", deque.removeFront());
        assertEquals(2, deque.size());
        assertEquals(-1, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertEquals("t1", deque.removeFront());
        assertEquals(1, deque.size());
        assertEquals(-2, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertEquals("t2", deque.removeFront());
        assertEquals(0, deque.size());
        assertEquals(-3, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertNull(deque.removeFront());
        //------------------------------------------------
        deque.addFront("th2");
        assertEquals(1, deque.size());
        deque.addFront("th1");
        assertEquals(2, deque.size());
        deque.addFront("new_h1");
        assertEquals(3, deque.size());
        assertEquals(0, deque.headIndex);
        deque.addFront("new_h2");
        assertEquals(4, deque.size());
        assertEquals(1, deque.headIndex);
        /*assertEquals("th1", deque.tailArray.array[0]);
        assertEquals("th2", deque.tailArray.array[1]);
        assertEquals("new_h1", deque.headArray.array[0]);
        assertEquals("new_h2", deque.headArray.array[1]);
        assertEquals(2, deque.headArray.count);
        assertEquals(2, deque.tailArray.count);*/
    }

    @Test
    void testAddTail_NativeArray() {
        DequeByTwoDynArray deque = new DequeByTwoDynArray<>();
        deque.addTail(1);
        deque.addTail("two");
        deque.addTail(3);
        assertEquals(2, deque.tailIndex);
        assertEquals(3, deque.size());
        assertEquals(1, deque.tailArray.getItem(0));
        assertEquals("two", deque.tailArray.getItem(1));
        assertEquals(3, deque.tailArray.getItem(2));
        assertEquals(-1, deque.headIndex);
    }

    @Test
    void testRemoveFront_NativeArray() {
        DequeByTwoDynArray deque = new DequeByTwoDynArray<>();
        deque.addFront(1);
        deque.addFront("two");
        deque.addFront(3);
        assertEquals(2, deque.headIndex);
        assertEquals(3, deque.size());
        assertEquals(3, deque.removeFront());
        assertEquals(1, deque.headIndex);
        assertEquals("two", deque.removeFront());
        assertEquals(0, deque.headIndex);
        assertEquals(1, deque.removeFront());
        assertEquals(-1, deque.headIndex);
        assertEquals(0, deque.size());
        assertNull(deque.removeFront());
    }

    @Test
    void testRemoveFront_AlienArray() {
        DequeByTwoDynArray deque = new DequeByTwoDynArray<>();
        deque.addFront("h1");
        deque.addFront("h2");
        deque.addTail("t1");
        deque.addTail("t2");
        assertEquals(4, deque.size());
        assertEquals("h2", deque.removeFront());
        assertEquals(3, deque.size());
        assertEquals(0, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertEquals("h1", deque.removeFront());
        assertEquals(2, deque.size());
        assertEquals(-1, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertEquals("t1", deque.removeFront());
        assertEquals(1, deque.size());
        assertEquals(-2, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertEquals("t2", deque.removeFront());
        assertEquals(0, deque.size());
        assertEquals(-3, deque.headIndex);
        assertEquals(1, deque.tailIndex);

        assertNull(deque.removeFront());
        assertEquals(0, deque.size());
    }

    @Test
    void testRemoveTail_NativeArray() {
        DequeByTwoDynArray deque = new DequeByTwoDynArray<>();
        deque.addTail(1);
        deque.addTail("two");
        deque.addTail(3);
        assertEquals(2, deque.tailIndex);
        assertEquals(3, deque.size());
        assertEquals(3, deque.removeTail());
        assertEquals(1, deque.tailIndex);
        assertEquals("two", deque.removeTail());
        assertEquals(0, deque.tailIndex);
        assertEquals(1, deque.removeTail());
        assertEquals(-1, deque.tailIndex);
        assertEquals(0, deque.size());
        assertNull(deque.removeTail());
    }

}