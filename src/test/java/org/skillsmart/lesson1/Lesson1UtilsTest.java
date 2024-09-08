package org.skillsmart.lesson1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson1UtilsTest {

    private final Lesson1Utils utils = new Lesson1Utils();
    private final LinkedList testListCount3 = new LinkedList();
    private final LinkedList testListCount2 = new LinkedList();
    private final LinkedList testListCount3Too = new LinkedList();

    @BeforeEach
    void setUp() {
        testListCount3.addInTail(new Node(11));
        testListCount3.addInTail(new Node(22));
        testListCount3.addInTail(new Node(33));

        testListCount3Too.addInTail(new Node(44));
        testListCount3Too.addInTail(new Node(55));
        testListCount3Too.addInTail(new Node(66));

        testListCount2.addInTail(new Node(77));
        testListCount2.addInTail(new Node(88));
    }

    @Test
    void testSumEqualLengthLists1_TwoEmptyLists() {
        LinkedList resultList = utils.sumEqualLengthLists1(new LinkedList(), new LinkedList());
        assertEquals(0, resultList.count());
    }

    @Test
    void testSumEqualLengthLists1_TwoNonEqualLists() {
        LinkedList resultList = utils.sumEqualLengthLists1(testListCount2, testListCount3);
        assertEquals(0, resultList.count());
    }

    @Test
    void testSumEqualLengthLists1_EqualLists() {
        LinkedList resultList = utils.sumEqualLengthLists1(testListCount3, testListCount3Too);
        assertEquals(3, resultList.count());
        assertEquals(resultList.head.value,55);
        assertEquals(resultList.head.next.value,77);
        assertEquals(resultList.tail.value,99);
    }

    @Test
    void testSumEqualLengthLists2_TwoEmptyLists() {
        LinkedList resultList = utils.sumEqualLengthLists2(new LinkedList(), new LinkedList());
        assertEquals(0, resultList.count());
    }

    @Test
    void testSumEqualLengthLists2_TwoNonEqualLists() {
        LinkedList resultList = utils.sumEqualLengthLists2(testListCount2, testListCount3);
        assertEquals(0, resultList.count());
    }

    @Test
    void testSumEqualLengthLists2_EqualLists() {
        LinkedList resultList = utils.sumEqualLengthLists2(testListCount3, testListCount3Too);
        assertEquals(3, resultList.count());
        assertEquals(resultList.head.value,55);
        assertEquals(resultList.head.next.value,77);
        assertEquals(resultList.tail.value,99);
    }

    @Test
    void testSumEqualLengthLists3_TwoEmptyLists() {
        LinkedList resultList = utils.sumEqualLengthLists3(new LinkedList(), new LinkedList());
        assertEquals(0, resultList.count());
    }

    @Test
    void testSumEqualLengthLists3_TwoNonEqualLists() {
        LinkedList resultList = utils.sumEqualLengthLists3(testListCount2, testListCount3);
        assertEquals(0, resultList.count());
    }

    @Test
    void testSumEqualLengthLists3_EqualLists() {
        LinkedList resultList = utils.sumEqualLengthLists3(testListCount3, testListCount3Too);
        assertEquals(3, resultList.count());
        assertEquals(resultList.head.value,55);
        assertEquals(resultList.head.next.value,77);
        assertEquals(resultList.tail.value,99);
    }
}