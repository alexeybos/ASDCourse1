package org.skillsmart.lesson7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedListTest {

    @Test
    void testCompare() {
        OrderedList list = new OrderedList<>(true);
        assertEquals(-1, list.compare(1, 2));
        assertEquals(1, list.compare(2, 1));
        assertEquals(0, list.compare(2, 2));
        assertEquals(-1, list.compare("qwerty", "werty"));
        assertEquals(1, list.compare("werty", "qwerty"));
        assertEquals(0, list.compare(" qwerty", "qwerty "));
    }

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
        assertEquals(150, list.head.next.value);
        assertEquals(200, list.tail.value);
        assertEquals(3, list.count());
        list.add(10); //new head
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
        list.add(300); //new tail
        assertEquals(6, list.count());
        assertEquals(10, list.head.value);
        assertEquals(100, list.head.next.value);
        assertEquals(120, list.head.next.next.value);
        assertEquals(150, list.tail.prev.prev.value);
        assertEquals(200, list.tail.prev.value);
        assertEquals(300, list.tail.value);
    }

    @Test
    void testAdd_Desc() {
        OrderedList<Integer> list = new OrderedList<>(false);
        list.add(100);
        assertEquals(1, list.count());
        assertEquals(100, list.head.value);
        assertEquals(100, list.tail.value);
        list.add(200); //new head
        assertEquals(2, list.count());
        assertEquals(200, list.head.value);
        assertEquals(100, list.tail.value);
        list.add(150);
        assertEquals(3, list.count());
        assertEquals(200, list.head.value);
        assertEquals(150, list.head.next.value);
        assertEquals(100, list.tail.value);
        list.add(10); //new tail
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
        list.add(300); //new head
        assertEquals(6, list.count());
        assertEquals(300, list.head.value);
        assertEquals(200, list.head.next.value);
        assertEquals(150, list.head.next.next.value);
        assertEquals(120, list.head.next.next.next.value);
        assertEquals(100, list.tail.prev.value);
        assertEquals(10, list.tail.value);
    }

    @Test
    void testFind_Asc() {
        OrderedList<Integer> list = new OrderedList<>(true);
        assertNull(list.find(100));
        list.add(100);
        assertEquals(100, list.find(100).value);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        assertEquals(100, list.find(100).value);
        assertEquals(400, list.find(400).value);
        assertEquals(500, list.find(500).value);
        assertNull(list.find(150));
        assertNull(list.find(50));
        assertNull(list.find(600));
    }

    @Test
    void testFind_Desc() {
        OrderedList<Integer> list = new OrderedList<>(false);
        assertNull(list.find(100));
        list.add(100);
        assertEquals(100, list.find(100).value);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        assertEquals(100, list.find(100).value);
        assertEquals(400, list.find(400).value);
        assertEquals(500, list.find(500).value);
        assertNull(list.find(150));
        assertNull(list.find(50));
        assertNull(list.find(600));
    }

    @Test
    void testDelete_Asc() {
        OrderedList<Integer> list = new OrderedList<>(true);
        list.delete(100);
        list.add(100);
        assertEquals(1, list.count());
        list.delete(100);
        assertEquals(0, list.count());
        assertNull(list.head);
        assertNull(list.tail);
        list.add(100);
        list.add(200);
        list.delete(100);
        assertEquals(1, list.count());
        assertEquals(200, list.head.value);
        assertEquals(200, list.tail.value);
        list.add(100);
        list.add(150);
        list.add(300);
        list.add(150);
        list.delete(150);
        assertEquals(4, list.count());
        assertEquals(100, list.head.value);
        assertEquals(150, list.head.next.value);
        assertEquals(200, list.tail.prev.value);
        assertEquals(300, list.tail.value);
    }

    @Test
    void testDelete_Desc() {
        OrderedList<Integer> list = new OrderedList<>(false);
        list.delete(100);
        assertEquals(0, list.count());
        list.add(100);
        list.delete(100);
        assertEquals(0, list.count());
        assertNull(list.head);
        assertNull(list.tail);
        list.add(100);
        list.add(200);
        list.delete(100);
        assertEquals(1, list.count());
        assertEquals(200, list.head.value);
        assertEquals(200, list.tail.value);
        list.add(100);
        list.add(150);
        list.add(300);
        list.add(150);
        assertEquals(5, list.count());
        list.delete(150);
        assertEquals(4, list.count());
        assertEquals(300, list.head.value);
        assertEquals(200, list.head.next.value);
        assertEquals(150, list.tail.prev.value);
        assertEquals(100, list.tail.value);
    }

    @Test
    void clear() {
        OrderedList<Integer> list = new OrderedList<>(true);
        list.clear(true);
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.count());
        list.add(1);
        assertEquals(1, list.count());
        list.clear(true);
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.count());
        list.add(100);
        list.add(500);
        assertEquals(2, list.count());
        list.clear(true);
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.count());
    }

}