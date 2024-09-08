package org.skillsmart.lesson1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private final LinkedList testList = new LinkedList();
    private final LinkedList testListEmpty = new LinkedList();
    private final LinkedList testListSingleItem = new LinkedList();

    @BeforeEach
    void setUp() {
        testList.addInTail(new Node(10));
        testList.addInTail(new Node(11));
        testList.addInTail(new Node(20));
        testList.addInTail(new Node(22));
        testList.addInTail(new Node(30));
        testList.addInTail(new Node(33));
        testList.addInTail(new Node(40));
        testList.addInTail(new Node(22));
        testList.addInTail(new Node(20));
        testList.addInTail(new Node(44));

        testListSingleItem.addInTail(new Node(22));
    }

    @Test
    void testCountMultiList() {
        assertEquals(10, testList.count());
    }

    @Test
    void testCountSingleList() {
        assertEquals(1, testListSingleItem.count());
    }

    @Test
    void testCountEmptyList() {
        assertEquals(0, testListEmpty.count());
    }

    @Test
    void testFindAll_SeveralResultInMultiList() {
        ArrayList<Node> resultList = testList.findAll(20);
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        for (Node node : resultList) {
            assertEquals(20, node.value);
        }
    }

    @Test
    void testFindAll_EmptyResultInMultiList() {
        ArrayList<Node> resultList = testList.findAll(99);
        assertTrue(resultList.isEmpty());
    }

    @Test
    void testFindAll_ResultInEmptyList() {
        ArrayList<Node> resultList = testListEmpty.findAll(20);
        assertTrue(resultList.isEmpty());
        assertEquals(0, testListEmpty.count());
    }

    @Test
    void testFindAll_ResultInSingleList() {
        ArrayList<Node> resultList = testListSingleItem.findAll(22);
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        assertEquals(22, resultList.get(0).value);
        assertEquals(1, testListSingleItem.count());
    }

    @Test
    void testFindAll_EmptyResultInSingleList() {
        ArrayList<Node> resultList = testListSingleItem.findAll(20);
        assertTrue(resultList.isEmpty());
        assertEquals(1, testListSingleItem.count());
    }

    @Test
    void testRemove_inEmptyList() {
        assertFalse(testListEmpty.remove(11));
        assertEquals(0, testListEmpty.count());
    }

    @Test
    void testRemove_ExistsInSingleList() {
        assertTrue(testListSingleItem.remove(22));
        assertEquals(0, testListSingleItem.count());
        assertNull(testListSingleItem.head);
        assertNull(testListSingleItem.tail);
    }

    @Test
    void testRemove_notExistsInSingleList() {
        assertFalse(testListSingleItem.remove(20));
        assertEquals(1, testListSingleItem.count());
        assertEquals(22, testListSingleItem.head.value);
        assertNull(testListSingleItem.head.next);
    }

    @Test
    void testRemove_ExistsInMultiList() {
        assertTrue(testList.remove(20));
        assertEquals(9, testList.count());
    }

    @Test
    void testRemove_FirstInMultiList() {
        assertTrue(testList.remove(10));
        assertEquals(9, testList.count());
        assertEquals(testList.head.value,11);
    }

    @Test
    void testRemove_LastInMultiList() {
        assertTrue(testList.remove(44));
        assertEquals(9, testList.count());
        assertEquals(testList.tail.value,20);
        assertNull(testList.tail.next);
    }

    @Test
    void testRemove_notExistsInMultiList() {
        assertFalse(testList.remove(99));
        assertEquals(10, testList.count());
    }

    @Test
    void testRemoveAll_inEmptyList() {
        testListEmpty.removeAll(11);
        assertEquals(0, testListEmpty.count());
    }

    @Test
    void testRemoveAll_ExistsInSingleList() {
        testListSingleItem.removeAll(22);
        assertEquals(0, testListSingleItem.count());
        assertNull(testListSingleItem.head);
        assertNull(testListSingleItem.tail);
    }

    @Test
    void testRemoveAll_notExistsInSingleList() {
        testListSingleItem.removeAll(20);
        assertEquals(1, testListSingleItem.count());
        assertEquals(22, testListSingleItem.head.value);
        assertNull(testListSingleItem.head.next);
    }

    @Test
    void testRemoveAll_OneExistsInMultiList() {
        testList.removeAll(30);
        assertEquals(9, testList.count());
    }

    @Test
    void testRemoveAll_FewExistsInMultiList() {
        testList.removeAll(22);
        assertEquals(8, testList.count());
    }

    @Test
    void testRemoveAll_FirstExistsInMultiList() {
        testList.removeAll(10);
        assertEquals(9, testList.count());
        assertEquals(testList.head.value,11);
    }

    @Test
    void testRemoveAll_LastExistsInMultiList() {
        testList.removeAll(44);
        assertEquals(9, testList.count());
        assertEquals(testList.tail.value,20);
        assertNull(testList.tail.next);
    }

    @Test
    void testRemoveAll_NotExistsInMultiList() {
        testList.removeAll(99);
        assertEquals(10, testList.count());
    }

    @Test
    void testRemoveAll_ToEmptyInMultiList() {
        LinkedList testListSimilar = new LinkedList();
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.removeAll(11);
        assertEquals(0, testListSimilar.count());
        assertNull(testListSimilar.head);
        assertNull(testListSimilar.tail);
    }

    @Test
    void testRemoveAll_FirstLastInMultiList() {
        LinkedList testListSimilar = new LinkedList();
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(22));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.removeAll(11);
        assertEquals(1, testListSimilar.count());
        assertEquals(testListSimilar.head, testListSimilar.tail);
        assertEquals(22, testListSimilar.head.value);
        assertNull(testListSimilar.tail.next);
    }

    @Test
    void testRemoveAll_FirstAndSecondInMultiList() {
        LinkedList testListSimilar = new LinkedList();
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(22));
        testListSimilar.removeAll(11);
        assertEquals(1, testListSimilar.count());
        assertEquals(testListSimilar.head, testListSimilar.tail);
        assertEquals(22, testListSimilar.head.value);
        assertNull(testListSimilar.tail.next);
    }

    @Test
    void testRemoveAll_PreLastAndLastInMultiList() {
        LinkedList testListSimilar = new LinkedList();
        testListSimilar.addInTail(new Node(22));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.removeAll(11);
        assertEquals(1, testListSimilar.count());
        assertEquals(testListSimilar.head, testListSimilar.tail);
        assertEquals(22, testListSimilar.head.value);
        assertNull(testListSimilar.tail.next);
    }

    @Test
    void testRemoveAll_TwoInARowInMultiList() {
        LinkedList testListSimilar = new LinkedList();
        testListSimilar.addInTail(new Node(22));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(11));
        testListSimilar.addInTail(new Node(23));
        testListSimilar.removeAll(11);
        assertEquals(2, testListSimilar.count());
        assertEquals(22, testListSimilar.head.value);
        assertEquals(23, testListSimilar.tail.value);
        assertNull(testListSimilar.tail.next);
    }

    @Test
    void testClear_EmptyList() {
        testListEmpty.clear();
        assertNull(testListEmpty.head);
        assertNull(testListEmpty.tail);
    }

    @Test
    void testClear_SingleList() {
        testListSingleItem.clear();
        assertNull(testListSingleItem.head);
        assertNull(testListSingleItem.tail);
    }

    @Test
    void testClear_MultiList() {
        testList.clear();
        assertNull(testList.head);
        assertNull(testList.tail);
    }

    @Test
    void testInsertAfter_FirstInEmptyList() {
        Node nodeToInsert = new Node(99);
        testListEmpty.insertAfter(null, nodeToInsert);
        assertEquals(1, testListEmpty.count());
        assertEquals(testListEmpty.head, nodeToInsert);
        assertEquals(testListEmpty.tail, nodeToInsert);
        assertNull(testListEmpty.head.next);
    }

    @Test
    void testInsertAfter_FirstInSingleList() {
        Node nodeToInsert = new Node(99);
        testListSingleItem.insertAfter(null, nodeToInsert);
        assertEquals(2, testListSingleItem.count());
        assertEquals(testListSingleItem.head.value, 99);
        assertEquals(testListSingleItem.tail.value, 22);
        assertNull(testListSingleItem.tail.next);
    }

    @Test
    void testInsertAfter_LastInSingleList() {
        Node nodeToInsert = new Node(99);
        Node node = testListSingleItem.find(22);
        testListSingleItem.insertAfter(node, nodeToInsert);
        assertEquals(2, testListSingleItem.count());
        assertEquals(testListSingleItem.tail, nodeToInsert);
        assertNull(testListSingleItem.tail.next);
    }

    @Test
    void testInsertAfter_FirstInMultiList() {
        Node nodeToInsert = new Node(99);
        testList.insertAfter(null, nodeToInsert);
        assertEquals(11, testList.count());
        assertEquals(testList.head.value, 99);
        assertEquals(testList.head.next.value, 10);
        assertEquals(testList.tail.value, 44);
        assertNull(testList.tail.next);
    }

    @Test
    void testInsertAfter_MiddleInMultiList() {
        Node nodeToInsert = new Node(99);
        Node node = testList.find(30);
        testList.insertAfter(node, nodeToInsert);
        assertEquals(11, testList.count());
        assertEquals(testList.find(30).next.value, 99);
        assertEquals(testList.head.value, 10);
        assertEquals(testList.tail.value, 44);
        assertNull(testList.tail.next);
    }

    @Test
    void testInsertAfter_LastInMultiList() {
        Node nodeToInsert = new Node(99);
        testList.insertAfter(testList.tail, nodeToInsert);
        assertEquals(11, testList.count());
        assertEquals(testList.tail, nodeToInsert);
    }
}