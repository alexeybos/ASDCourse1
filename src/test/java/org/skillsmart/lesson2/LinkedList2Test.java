package org.skillsmart.lesson2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LinkedList2Test {

    private final LinkedList2 testList = new LinkedList2();
    private final LinkedList2 testListEmpty = new LinkedList2();
    private final LinkedList2 testListSingleItem = new LinkedList2();

    private final LinkedList2 cycleList = new LinkedList2();

    @BeforeEach
    void setUp() {
        testList.addInTail(new Node(10));
        testList.addInTail(new Node(11));
        testList.addInTail(new Node(20));
        testList.addInTail(new Node(11));

        testListSingleItem.addInTail(new Node(33));

        cycleList.addInTail(new Node(10));
        cycleList.addInTail(new Node(11));
        cycleList.addInTail(new Node(12));
        cycleList.head.prev = cycleList.tail;
        cycleList.tail.next = cycleList.head;
    }

    @Test
    void testFind_InEmptyList() {
        Node node = testListEmpty.find(12);
        assertNull(node);
    }

    @Test
    void testFind_ExistsInSingleList() {
        Node node = testListSingleItem.find(33);
        assertEquals(33, node.value);
        assertNull(node.prev);
        assertNull(node.next);
        assertEquals(1, testListSingleItem.count());
    }

    @Test
    void testFind_NotExistsInSingleList() {
        Node node = testListSingleItem.find(44);
        assertNull(node);
        assertEquals(1, testListSingleItem.count());
    }

    @Test
    void testFind_ExistsInMultiList() {
        Node node = testList.find(11);
        assertEquals(11, node.value);
        assertNotNull(node.prev);
        assertNotNull(node.next);
        assertEquals(4, testList.count());
    }

    @Test
    void testFind_NotExistsInMultiList() {
        Node node = testList.find(33);
        assertNull(node);
        assertEquals(4, testList.count());
    }

    @Test
    void testFindAll_InEmptyList() {
        ArrayList<Node> nodes = testListEmpty.findAll(11);
        assertTrue(nodes.isEmpty());
    }

    @Test
    void testFindAll_ExistsInSingleList() {
        ArrayList<Node> nodes = testListSingleItem.findAll(33);
        assertEquals(1, nodes.size());
        assertEquals(1, testListSingleItem.count());
        assertEquals(33, nodes.get(0).value);
    }

    @Test
    void testFindAll_NotExistsInSingleList() {
        ArrayList<Node> nodes = testListSingleItem.findAll(11);
        assertTrue(nodes.isEmpty());
    }

    @Test
    void testFindAll_OneExistsInMultiList() {
        ArrayList<Node> nodes = testList.findAll(20);
        assertEquals(1, nodes.size());
        assertEquals(4, testList.count());
        assertEquals(20, nodes.get(0).value);
    }

    @Test
    void testFindAll_TwoExistsInMultiList() {
        ArrayList<Node> nodes = testList.findAll(11);
        assertEquals(2, nodes.size());
        assertEquals(4, testList.count());
        assertEquals(11, nodes.get(0).value);
        assertEquals(11, nodes.get(1).value);
    }

    @Test
    void testFindAll_NotExistsInMultiList() {
        ArrayList<Node> nodes = testList.findAll(99);
        assertTrue(nodes.isEmpty());
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
        assertNull(testListSingleItem.head);
        assertNull(testListSingleItem.tail);
    }

    @Test
    void testRemove_NotExistsFromSingleList() {
        boolean result = testListSingleItem.remove(99);
        assertFalse(result);
        assertEquals(1, testListSingleItem.count());
        assertEquals(33, testListSingleItem.head.value);
        assertNull(testListSingleItem.head.next);
        assertNull(testListSingleItem.head.prev);
        assertNull(testListSingleItem.tail.next);
        assertNull(testListSingleItem.tail.prev);
    }

    @Test
    void testRemove_ExistsFromMultiList() {
        boolean result = testList.remove(20);
        assertTrue(result);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertEquals(10, testList.head.value);
        assertEquals(11, testList.head.next.value);
        assertEquals(11, testList.tail.value);
    }

    @Test
    void testRemove_ExistsFirstOfTwoFromMultiList() {
        boolean result = testList.remove(11);
        assertTrue(result);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertEquals(10, testList.head.value);
        assertEquals(20, testList.head.next.value);
        assertEquals(11, testList.tail.value);
    }

    @Test
    void testRemove_FirstFromMultiList() {
        boolean result = testList.remove(10);
        assertTrue(result);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertEquals(11, testList.head.value);
        assertNull(testList.head.prev);
        assertEquals(20, testList.head.next.value);
        assertEquals(11, testList.tail.value);
    }

    @Test
    void testRemove_LastFromMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(11));
        list.addInTail(new Node(12));
        boolean result = list.remove(12);
        assertTrue(result);
        assertEquals(2, list.count());
        assertEquals(2, reverseCount(list));
        assertEquals(10, list.head.value);
        assertNull(list.head.prev);
        assertNotNull(list.head.next);
        assertEquals(11, list.tail.value);
        assertNotNull(list.tail.prev);
        assertNull(list.tail.next);
    }

    @Test
    void testRemove_NotExistsFromMultiList() {
        boolean result = testList.remove(99);
        assertFalse(result);
        assertEquals(4, testList.count());
        assertEquals(4, reverseCount(testList));
    }

    @Test
    void testRemoveAll_FromEmptyList() {
        testListEmpty.removeAll(99);
        assertEquals(0, testListEmpty.count());
    }

    @Test
    void testRemoveAll_ExistsFromSingleList() {
        testListSingleItem.removeAll(33);
        assertEquals(0, testListSingleItem.count());
        assertNull(testListSingleItem.head);
        assertNull(testListSingleItem.tail);
    }

    @Test
    void testRemoveAll_NotExistsFromSingleList() {
        testListSingleItem.removeAll(99);
        assertEquals(1, testListSingleItem.count());
        assertEquals(1, reverseCount(testListSingleItem));
    }

    @Test
    void testRemoveAll_FirstExistsFromMultiList() {
        testList.removeAll(10);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertNull(testList.head.prev);
        assertEquals(11, testList.head.value);
    }

    @Test
    void testRemoveAll_OneExistsFromMultiList() {
        testList.removeAll(20);
        assertEquals(3, testList.count());
        assertEquals(3, reverseCount(testList));
        assertEquals(10, testList.head.value);
        assertEquals(11, testList.head.next.value);
        assertEquals(11, testList.tail.value);
    }

    @Test
    void testRemoveAll_TwoExistsThisFirstFromMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(11));
        list.addInTail(new Node(10));
        list.addInTail(new Node(12));
        list.removeAll(10);
        assertEquals(2, list.count());
        assertEquals(2, reverseCount(list));
        assertEquals(11, list.head.value);
        assertNull(list.head.prev);
        assertEquals(12, list.head.next.value);
        assertEquals(12, list.tail.value);
    }

    @Test
    void testRemoveAll_TwoFirstsExistsFromMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(10));
        list.addInTail(new Node(11));
        list.addInTail(new Node(12));
        list.removeAll(10);
        assertEquals(2, list.count());
        assertEquals(2, reverseCount(list));
        assertEquals(11, list.head.value);
        assertNull(list.head.prev);
        assertEquals(12, list.head.next.value);
        assertEquals(12, list.tail.value);
    }

    @Test
    void testRemoveAll_TwoExistsInMiddleFromMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(12));
        list.addInTail(new Node(11));
        list.addInTail(new Node(12));
        list.addInTail(new Node(11));
        Node head = list.head;
        Node tail = list.tail;
        list.removeAll(12);
        assertEquals(3, list.count());
        assertEquals(3, reverseCount(list));
        assertEquals(head, list.head);
        assertEquals(tail, list.tail);
        assertEquals(10, list.head.value);
        assertEquals(11, list.head.next.value);
        assertEquals(11, list.tail.value);
    }

    @Test
    void testRemoveAll_TwoExistsInARowInMiddleFromMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(11));
        list.addInTail(new Node(11));
        list.addInTail(new Node(12));
        Node head = list.head;
        Node tail = list.tail;
        list.removeAll(11);
        assertEquals(2, list.count());
        assertEquals(2, reverseCount(list));
        assertEquals(head, list.head);
        assertEquals(tail, list.tail);
        assertEquals(10, list.head.value);
        assertNull(list.head.prev);
        assertEquals(12, list.head.next.value);
        assertEquals(12, list.tail.value);
    }

    @Test
    void testRemoveAll_LastExistsFromMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(11));
        list.addInTail(new Node(12));
        list.removeAll(12);
        assertEquals(2, list.count());
        assertEquals(2, reverseCount(list));
        assertNull(list.head.prev);
        assertNull(list.tail.next);
        assertEquals(10, list.head.value);
        assertEquals(11, list.head.next.value);
        assertEquals(11, list.tail.value);
    }

    @Test
    void testRemoveAll_TwoLastsExistsFromMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(10));
        list.addInTail(new Node(11));
        list.addInTail(new Node(12));
        list.addInTail(new Node(12));
        list.removeAll(12);
        assertEquals(2, list.count());
        assertEquals(2, reverseCount(list));
        assertEquals(10, list.head.value);
        assertEquals(11, list.head.next.value);
        assertEquals(11, list.tail.value);
        assertNull(list.tail.next);
    }

    @Test
    void testRemoveAll_NoExistsFromMultiList() {
        Node head = testList.head;
        Node tail = testList.tail;
        testList.removeAll(99);
        assertEquals(4, testList.count());
        assertEquals(4, reverseCount(testList));
        assertEquals(head, testList.head);
        assertEquals(tail, testList.tail);
    }

    @Test
    void testClear_EmptyList() {
        testListEmpty.clear();
        assertEquals(0,testListEmpty.count());
        assertNull(testListEmpty.head);
        assertNull(testListEmpty.tail);
    }

    @Test
    void testClear_SingleList() {
        testListSingleItem.clear();
        assertEquals(0, testListSingleItem.count());
        assertNull(testListSingleItem.head);
        assertNull(testListSingleItem.tail);
    }

    @Test
    void testClear_MultiList() {
        testList.clear();
        assertEquals(0,testList.count());
        assertNull(testList.head);
        assertNull(testList.tail);
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
    void testInsertAfter_FirstToEmptyList() {
        Node node = new Node(11);
        testListEmpty.insertAfter(null, node);
        assertEquals(1, testListEmpty.count());
        assertEquals(node, testListEmpty.head);
        assertEquals(node, testListEmpty.tail);
        assertNull(testListEmpty.head.prev);
        assertNull(testListEmpty.head.next);
        assertEquals(11, testListEmpty.head.value);
    }

    @Test
    void testInsertAfter_AfterNotExistsToEmptyList() {
        Node node = new Node(11);
        Node nodeToInsert = new Node(12);
        testListEmpty.insertAfter(node, nodeToInsert);
        assertEquals(0, testListEmpty.count());
        assertNull(testListEmpty.head);
        assertNull(testListEmpty.tail);
    }

    @Test
    void testInsertAfter_FirstToSingleList() {
        Node node = new Node(11);
        testListSingleItem.insertAfter(null, node);
        assertEquals(2, testListSingleItem.count());
        assertNull(testListSingleItem.head.prev);
        assertNotNull(testListSingleItem.head.next);
        assertNotNull(testListSingleItem.tail.prev);
        assertNull(testListSingleItem.tail.next);
        assertEquals(11, testListSingleItem.head.value);
        assertEquals(33, testListSingleItem.head.next.value);
        assertEquals(33, testListSingleItem.tail.value);
    }

    @Test
    void testInsertAfter_AfterLastInSingleList() {
        Node node = testListSingleItem.find(33);
        Node nodeToInsert = new Node(34);
        testListSingleItem.insertAfter(node, nodeToInsert);
        assertEquals(2, testListSingleItem.count());
        assertEquals(2, reverseCount(testListSingleItem));
        assertEquals(33, testListSingleItem.head.value);
        assertEquals(34, testListSingleItem.tail.value);
        assertNotNull(testListSingleItem.head.next);
        assertNull(testListSingleItem.head.prev);
        assertNull(testListSingleItem.tail.next);
        assertNotNull(testListSingleItem.tail.prev);
    }

    @Test
    void testInsertAfter_AfterNotExistsToSingleList() {
        Node node = new Node(11);
        Node nodeToInsert = new Node(12);
        Node head = testListSingleItem.head;
        Node tail = testListSingleItem.tail;
        testListSingleItem.insertAfter(node, nodeToInsert);
        assertEquals(1, testListSingleItem.count());
        assertEquals(33, testListSingleItem.head.value);
        assertEquals(head, testListSingleItem.head);
        assertEquals(tail, testListSingleItem.tail);
    }

    @Test
    void testInsertAfter_InMultiList() {
        Node node = testList.find(20);
        Node nodeToInsert = new Node(12);
        Node head = testList.head;
        Node tail = testList.tail;
        testList.insertAfter(node, nodeToInsert);
        assertEquals(head, testList.head);
        assertEquals(tail, testList.tail);
        assertEquals(5, testList.count());
        assertEquals(5, reverseCount(testList));
        assertEquals(10, testList.head.value);
        assertEquals(11, testList.head.next.value);
        assertEquals(20, testList.head.next.next.value);
        assertEquals(12, testList.tail.prev.value);
        assertEquals(11, testList.tail.value);
    }

    @Test
    void testInsertAfter_AfterLastInMultiList() {
        ArrayList<Node> nodes = testList.findAll(11);
        Node nodeToInsert = new Node(12);
        Node head = testList.head;
        Node node = nodes.get(1);
        testList.insertAfter(node, nodeToInsert);
        assertEquals(5, testList.count());
        assertEquals(5, reverseCount(testList));
        assertEquals(10, testList.head.value);
        assertEquals(11, testList.head.next.value);
        assertEquals(20, testList.head.next.next.value);
        assertEquals(11, testList.tail.prev.value);
        assertEquals(12, testList.tail.value);
    }

    @Test
    void testInsertAfter_AfterNotExistsInMultiList() {
        Node node = new Node(99);
        Node nodeToInsert = new Node(12);
        Node head = testList.head;
        Node tail = testList.tail;
        testList.insertAfter(node, nodeToInsert);
        assertEquals(4, testList.count());
        assertEquals(head, testList.head);
        assertEquals(tail, testList.tail);
    }

    @Test
    void testRevertList_EmptyList() {
        testListEmpty.revert();
        assertEquals(0, testListEmpty.count());
        assertNull(testListEmpty.head);
        assertNull(testListEmpty.tail);
    }

    @Test
    void testRevertList_SingleList() {
        testListSingleItem.revert();
        assertEquals(1, testListSingleItem.count());
        assertEquals(33, testListSingleItem.head.value);
        assertNull(testListSingleItem.head.prev);
        assertNull(testListSingleItem.tail.next);
    }

    @Test
    void testRevertList_MultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(4));
        list.revert();
        assertEquals(4, list.count());
        assertEquals(4, reverseCount(list));

        assertEquals(4, list.head.value);
        assertEquals(3, list.head.next.value);
        assertEquals(2, list.tail.prev.value);
        assertEquals(1, list.tail.value);
    }

    @Test
    void testSort_EmptyList() {
        testListEmpty.sort();
        assertEquals(0, testListEmpty.count());
        assertNull(testListEmpty.head);
        assertNull(testListEmpty.tail);
    }

    @Test
    void testSort_SingleList() {
        testListSingleItem.sort();
        assertEquals(1, testListSingleItem.count());
        assertNull(testListSingleItem.head.prev);
        assertNull(testListSingleItem.head.next);
        assertNull(testListSingleItem.tail.prev);
        assertNull(testListSingleItem.tail.next);
    }

    @Test
    void testSort_UnsortedMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(2));
        list.addInTail(new Node(4));
        list.addInTail(new Node(3));
        list.addInTail(new Node(1));
        list.sort();
        assertEquals(4, list.count());
        assertEquals(4, reverseCount(list));
        assertEquals(1, list.head.value);
        assertEquals(2, list.head.next.value);
        assertEquals(3, list.tail.prev.value);
        assertEquals(4, list.tail.value);
    }

    @Test
    void testSort_SortedMultiList() {
        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(4));
        list.sort();
        assertEquals(4, list.count());
        assertEquals(4, reverseCount(list));
        assertEquals(1, list.head.value);
        assertEquals(2, list.head.next.value);
        assertEquals(3, list.tail.prev.value);
        assertEquals(4, list.tail.value);
    }

    private int reverseCount(LinkedList2 list) {
        int cnt = 0;
        Node node = list.tail;
        while (node != null) {
            cnt++;
            node = node.prev;
        }
        return cnt;
    }
}