package org.skillsmart.lesson2_onedummy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedLinkedList2Test {

    private final ExtendedLinkedList2 testList = new ExtendedLinkedList2();
    private final ExtendedLinkedList2 testListEmpty = new ExtendedLinkedList2();
    private final ExtendedLinkedList2 testListSingleItem = new ExtendedLinkedList2();

    @BeforeEach
    void setUp() {
        testList.addInTail(new Node(10));
        testList.addInTail(new Node(11));
        testList.addInTail(new Node(20));
        testList.addInTail(new Node(11));

        testListSingleItem.addInTail(new Node(33));
    }

    @Test
    void testCount_EmptyList() {
        assertEquals(0, testListEmpty.count());
    }

    @Test
    void testCount_SingleList() {
        assertEquals(1, testListSingleItem.count());
    }

    @Test
    void testCount_MultiList() {
        assertEquals(4, testList.count());
    }

    @Test
    void testClear_EmptyList() {
        testListEmpty.clear();
        assertEquals(0,testListEmpty.count());
        assertNull(testListEmpty.getHead());
        assertNull(testListEmpty.getTail());
    }

    @Test
    void testClear_SingleList() {
        testListSingleItem.clear();
        assertEquals(0, testListSingleItem.count());
        assertNull(testListSingleItem.getHead());
        assertNull(testListSingleItem.getTail());
    }

    @Test
    void testClear_MultiList() {
        testList.clear();
        assertEquals(0,testList.count());
        assertNull(testList.getHead());
        assertNull(testList.getTail());
    }

    @Test
    void testRemove_FromEmptyList() {
        boolean result = testListEmpty.remove(11);
        assertFalse(result);
        assertEquals(0, testListEmpty.count());
    }

    @Test
    void testRemove_ExistsFromSingleList() {
        boolean result = testListSingleItem.remove(33);
        assertTrue(result);
        assertEquals(0, testListSingleItem.count());
        assertNull(testListSingleItem.getHead());
        assertNull(testListSingleItem.getTail());
    }

    @Test
    void testRemove_NotExistsFromSingleList() {
        boolean result = testListSingleItem.remove(99);
        assertFalse(result);
        assertEquals(1, testListSingleItem.count());
        assertEquals(33, testListSingleItem.getHead().value);
    }

    @Test
    void testRemove_ExistsFromMultiList() {
        boolean result = testList.remove(20);
        assertTrue(result);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertEquals(10, testList.getHead().value);
        assertEquals(11, testList.getHead().next.value);
        assertEquals(11, testList.getTail().value);
    }

    @Test
    void testRemove_ExistsFirstOfTwoFromMultiList() {
        boolean result = testList.remove(11);
        assertTrue(result);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertEquals(10, testList.getHead().value);
        assertEquals(20, testList.getHead().next.value);
        assertEquals(11, testList.getTail().value);
    }

    @Test
    void testRemove_FirstFromMultiList() {
        boolean result = testList.remove(10);
        assertTrue(result);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertEquals(11, testList.getHead().value);
        assertEquals(20, testList.getHead().next.value);
        assertEquals(11, testList.getTail().value);
    }

    @Test
    void testRemove_LastFromMultiList() {
        ExtendedLinkedList2 list = new ExtendedLinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(11));
        list.addInTail(new Node(12));
        boolean result = list.remove(12);
        assertTrue(result);
        assertEquals(2, list.count());
        assertEquals(2, reverseCount(list));
        assertEquals(10, list.getHead().value);
        assertNotNull(list.getHead().next);
        assertEquals(11, list.getTail().value);
        assertNotNull(list.getTail().prev);
    }

    @Test
    void testRemove_NotExistsFromMultiList() {
        boolean result = testList.remove(99);
        assertFalse(result);
        assertEquals(4, testList.count());
        assertEquals(4, reverseCount(testList));
    }

    @Test
    void testInsertAfter_FirstToEmptyList() {
        Node node = new Node(11);
        testListEmpty.insertAfter(null, node);
        assertEquals(1, testListEmpty.count());
        assertEquals(node, testListEmpty.getHead());
        assertEquals(node, testListEmpty.getTail());
        assertEquals(11, testListEmpty.getHead().value);
    }

    @Test
    void testInsertAfter_AfterNotExistsToEmptyList() {
        Node node = new Node(11);
        Node nodeToInsert = new Node(12);
        testListEmpty.insertAfter(node, nodeToInsert);
        assertEquals(0, testListEmpty.count());
        assertNull(testListEmpty.getHead());
        assertNull(testListEmpty.getTail());
    }

    @Test
    void testInsertAfter_FirstToSingleList() {
        Node node = new Node(11);
        testListSingleItem.insertAfter(null, node);
        assertEquals(2, testListSingleItem.count());
        assertEquals(11, testListSingleItem.getHead().value);
        assertEquals(33, testListSingleItem.getHead().next.value);
        assertEquals(33, testListSingleItem.getTail().value);
    }

    @Test
    void testInsertAfter_AfterLastInSingleList() {
        Node node = testListSingleItem.find(33);
        Node nodeToInsert = new Node(34);
        testListSingleItem.insertAfter(node, nodeToInsert);
        assertEquals(2, testListSingleItem.count());
        assertEquals(2, reverseCount(testListSingleItem));
        assertEquals(33, testListSingleItem.getHead().value);
        assertEquals(34, testListSingleItem.getTail().value);
    }

    @Test
    void testInsertAfter_AfterNotExistsToSingleList() {
        Node node = new Node(11);
        Node nodeToInsert = new Node(12);
        Node head = testListSingleItem.getHead();
        Node tail = testListSingleItem.getTail();
        testListSingleItem.insertAfter(node, nodeToInsert);
        assertEquals(1, testListSingleItem.count());
        assertEquals(33, testListSingleItem.getHead().value);
        assertEquals(head, testListSingleItem.getHead());
        assertEquals(tail, testListSingleItem.getTail());
    }

    @Test
    void testInsertAfter_InMultiList() {
        Node node = testList.find(20);
        Node nodeToInsert = new Node(12);
        Node head = testList.getHead();
        Node tail = testList.getTail();
        testList.insertAfter(node, nodeToInsert);
        assertEquals(head, testList.getHead());
        assertEquals(tail, testList.getTail());
        assertEquals(5, testList.count());
        assertEquals(5, reverseCount(testList));
        assertEquals(10, testList.getHead().value);
        assertEquals(11, testList.getHead().next.value);
        assertEquals(20, testList.getHead().next.next.value);
        assertEquals(12, testList.getTail().prev.value);
        assertEquals(11, testList.getTail().value);
    }

    @Test
    void testInsertAfter_AfterNotExistsInMultiList() {
        Node node = new Node(99);
        Node nodeToInsert = new Node(12);
        Node head = testList.getHead();
        Node tail = testList.getTail();
        testList.insertAfter(node, nodeToInsert);
        assertEquals(4, testList.count());
        assertEquals(head, testList.getHead());
        assertEquals(tail, testList.getTail());
    }

    private int reverseCount(ExtendedLinkedList2 list) {
        int cnt = 0;
        for (Node iNode = list.getTail(); iNode != list.getHead(); iNode = iNode.prev) {
            cnt++;
        }
        if (cnt != 0) {
            cnt++;
        }
        return cnt;
    }

}