package org.skillsmart.asd2.lesson11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    void step() {
        int[] sortedArr = {1,2,3,4,5,6,7,8,9};
        BinarySearch bs = new BinarySearch(sortedArr);
        bs.Step(1);
        assertEquals(0, bs.GetResult());
        assertEquals(0, bs.Left);
        assertEquals(3, bs.Right);
        bs.Step(1);
        assertEquals(1, bs.GetResult());
        assertEquals(0, bs.Left);
        assertEquals(0, bs.Right);
    }

    @Test
    void step2() {
        int[] sortedArr = {1,2,3,4,5,6,7,8};
        BinarySearch bs = new BinarySearch(sortedArr);
        bs.Step(1);
        assertEquals(0, bs.GetResult());
        assertEquals(0, bs.Left);
        assertEquals(2, bs.Right);
        bs.Step(1);
        assertEquals(1, bs.GetResult());
        assertEquals(0, bs.Left);
        assertEquals(0, bs.Right);
    }

    @Test
    void step3() {
        int[] sortedArr = {1,2,3,4,5,6,7,8};
        BinarySearch bs = new BinarySearch(sortedArr);
        bs.Step(7);
        assertEquals(0, bs.GetResult());
        assertEquals(4, bs.Left);
        assertEquals(7, bs.Right);
        bs.Step(7);
        assertEquals(1, bs.GetResult());
        assertEquals(6, bs.Left);
        assertEquals(7, bs.Right);
    }

    @Test
    void step_Negative() {
        int[] sortedArr = {1,3,5,7,8,9,11};
        BinarySearch bs = new BinarySearch(sortedArr);
        bs.Step(2);
        assertEquals(0, bs.GetResult());
        bs.Step(4);
        assertEquals(-1, bs.GetResult());
        bs.Step(4);
        assertEquals(-1, bs.GetResult());
    }

    @Test
    void step_SmallArrPositive() {
        int[] sortedArr1 = {1};
        BinarySearch bs1 = new BinarySearch(sortedArr1);
        bs1.Step(1);
        assertEquals(1, bs1.GetResult());
        int[] sortedArr2 = {1,2};
        BinarySearch bs2 = new BinarySearch(sortedArr2);
        bs1.Step(1);
        assertEquals(1, bs1.GetResult());
    }

    @Test
    void step_SmallArrNegative() {
        int[] sortedArr = {2};
        BinarySearch bs = new BinarySearch(sortedArr);
        bs.Step(1);
        assertEquals(-1, bs.GetResult());
        int[] sortedArr1 = {1};
        BinarySearch bs1 = new BinarySearch(sortedArr1);
        bs1.Step(2);
        assertEquals(-1, bs1.GetResult());
        int[] sortedArr2 = {1,2};
        BinarySearch bs2 = new BinarySearch(sortedArr2);
        bs1.Step(3);
        assertEquals(-1, bs1.GetResult());
    }
}