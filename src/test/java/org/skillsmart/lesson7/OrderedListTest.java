package org.skillsmart.lesson7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedListTest {

    @Test
    void testAdd_Asc() {
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(100);
        assertEquals(1, list.count());
        assertEquals(100, list.head.value);
        assertEquals(100, list.tail.value);
        list.add(200);
        assertEquals(2, list.count());
        assertEquals(100, list.head.value);
        assertEquals(200, list.tail.value);
        list.add(150);
        assertEquals(100, list.head.value);
        assertEquals(200, list.tail.value);
        assertEquals(150, list.head.next.value);
        assertEquals(3, list.count());
        list.add(10);
        assertEquals(4, list.count());
        assertEquals(10, list.head.value);
        assertEquals(200, list.tail.value);
        assertEquals(100, list.head.next.value);
        assertEquals(150, list.tail.prev.value);
        list.add(120);
        assertEquals(5, list.count());
        assertEquals(10, list.head.value);
        assertEquals(100, list.head.next.value);
        assertEquals(120, list.head.next.next.value);
        assertEquals(150, list.tail.prev.value);
        assertEquals(200, list.tail.value);

    }

    @Test
    void testAdd_Desc() {
        OrderedList<Integer> list = new OrderedList<>(true);
        list.add(100);
        assertEquals(1, list.count());
        assertEquals(100, list.head.value);
        assertEquals(100, list.tail.value);
        list.add(200);
        assertEquals(2, list.count());
        assertEquals(200, list.head.value);
        assertEquals(100, list.tail.value);
        list.add(150);
        assertEquals(3, list.count());
        assertEquals(200, list.head.value);
        assertEquals(150, list.head.next.value);
        assertEquals(100, list.tail.value);
        list.add(10);
        assertEquals(4, list.count());
        assertEquals(200, list.head.value);
        assertEquals(150, list.head.next.value);
        assertEquals(100, list.tail.prev.value);
        assertEquals(10, list.tail.value);
        list.add(120);
        assertEquals(5, list.count());
        assertEquals(200, list.head.value);
        assertEquals(150, list.head.next.value);
        assertEquals(120, list.head.next.next.value);
        assertEquals(100, list.tail.prev.value);
        assertEquals(10, list.tail.value);
    }

    @Test
    void testFind() {
    }

    @Test
    void testDelete_Asc() {
    }

    @Test
    void testDelete_Desc() {
    }

    @Test
    void clear() {
    }

    @Test
    void count() {
    }
}