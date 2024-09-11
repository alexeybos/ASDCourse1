package org.skillsmart.lesson2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Lesson2UtilsTest {

    private Lesson2Utils utils = new Lesson2Utils();

    @Test
    void testMergeListsToSortedList() {
        LinkedList2 list1 = new LinkedList2();
        list1.addInTail(new Node(2));
        list1.addInTail(new Node(3));
        list1.addInTail(new Node(1));
        LinkedList2 list2 = new LinkedList2();
        list2.addInTail(new Node(2));
        list2.addInTail(new Node(6));
        list2.addInTail(new Node(4));
        LinkedList2 resultList = utils.mergeListsToSortedList(list1, list2);
        assertEquals(6, resultList.count());
        assertEquals(1, resultList.head.value);
        assertEquals(2, resultList.head.next.value);
        assertEquals(2, resultList.head.next.next.value);
        assertEquals(3, resultList.tail.prev.prev.value);
        assertEquals(4, resultList.tail.prev.value);
        assertEquals(6, resultList.tail.value);
    }

    @Test
    void testMergeListsToSortedList_DifferentCount() {
        LinkedList2 list1 = new LinkedList2();
        list1.addInTail(new Node(2));
        list1.addInTail(new Node(1));
        LinkedList2 list2 = new LinkedList2();
        list2.addInTail(new Node(2));
        list2.addInTail(new Node(6));
        list2.addInTail(new Node(4));
        LinkedList2 resultList = utils.mergeListsToSortedList(list1, list2);
        assertEquals(5, resultList.count());
        assertEquals(1, resultList.head.value);
        assertEquals(2, resultList.head.next.value);
        assertEquals(2, resultList.head.next.next.value);
        assertEquals(4, resultList.tail.prev.value);
        assertEquals(6, resultList.tail.value);
    }

    @Test
    void testMergeListsToSortedList_TwoEmptyLists() {
        LinkedList2 list1 = new LinkedList2();
        LinkedList2 list2 = new LinkedList2();
        LinkedList2 resultList = utils.mergeListsToSortedList(list1, list2);
        assertEquals(0, resultList.count());
    }

    @Test
    void testMergeListsToSortedList_SecondEmptyLists() {
        LinkedList2 list1 = new LinkedList2();
        list1.addInTail(new Node(2));
        list1.addInTail(new Node(3));
        list1.addInTail(new Node(1));
        LinkedList2 list2 = new LinkedList2();
        LinkedList2 resultList = utils.mergeListsToSortedList(list1, list2);
        assertEquals(3, resultList.count());
        assertEquals(1, resultList.head.value);
        assertEquals(2, resultList.head.next.value);
        assertEquals(3, resultList.tail.value);
    }

    @Test
    void testMergeListsToSortedList_FirstEmptyLists() {
        LinkedList2 list1 = new LinkedList2();
        LinkedList2 list2 = new LinkedList2();
        list2.addInTail(new Node(2));
        list2.addInTail(new Node(6));
        list2.addInTail(new Node(4));
        LinkedList2 resultList = utils.mergeListsToSortedList(list1, list2);
        assertEquals(3, resultList.count());
        assertEquals(2, resultList.head.value);
        assertEquals(4, resultList.head.next.value);
        assertEquals(6, resultList.tail.value);
    }

    @Test
    void testMergeListsToSortedList_TwoSingleLists() {
        LinkedList2 list1 = new LinkedList2();
        list1.addInTail(new Node(5));
        LinkedList2 list2 = new LinkedList2();
        list2.addInTail(new Node(2));
        LinkedList2 resultList = utils.mergeListsToSortedList(list1, list2);
        assertEquals(2, resultList.count());
        assertEquals(2, resultList.head.value);
        assertEquals(5, resultList.tail.value);
    }
}