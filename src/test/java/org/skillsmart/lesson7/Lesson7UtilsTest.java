package org.skillsmart.lesson7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson7UtilsTest {

    Lesson7Utils utils = new Lesson7Utils();

    @Test
    void mergeOrderedLists_Asc() {
        OrderedList<Integer> list1 = new OrderedList<>(true);
        OrderedList<Integer> list2 = new OrderedList<>(true);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list1.add(2);
        list1.add(3);
        list1.add(4);

        OrderedList<Integer> result = utils.mergeOrderedLists(list1, list2);

        assertEquals(6, result.count());
        assertEquals(1, result.head.value);
        assertEquals(2, result.head.next.value);
        assertEquals(2, result.head.next.next.value);
        assertEquals(3, result.head.next.next.next.value);
        assertEquals(3, result.tail.prev.value);
        assertEquals(4, result.tail.value);
    }

    @Test
    void mergeOrderedLists_Desc() {
        OrderedList<Integer> list1 = new OrderedList<>(false);
        OrderedList<Integer> list2 = new OrderedList<>(false);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list1.add(2);
        list1.add(3);
        list1.add(4);

        OrderedList<Integer> result = utils.mergeOrderedLists(list1, list2);

        assertEquals(6, result.count());
        assertEquals(4, result.head.value);
        assertEquals(3, result.head.next.value);
        assertEquals(3, result.head.next.next.value);
        assertEquals(2, result.head.next.next.next.value);
        assertEquals(2, result.tail.prev.value);
        assertEquals(1, result.tail.value);
    }
}