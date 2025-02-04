package org.skillsmart.asd2real.lesson5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {

    @Test
    void testGenerateBBSTArray_OneRootArr() {
        int[] arr = {8};
        int[] bbst = AlgorithmsDataStructures2.GenerateBBSTArray(arr);
        assertEquals(1, bbst.length);
        assertEquals(8, bbst[0]);
    }

    @Test
    void testGenerateBBSTArray() {
        int[] arr = {12, 8, 4};
        int[] bbst = AlgorithmsDataStructures2.GenerateBBSTArray(arr);
        assertEquals(8, bbst[0]);
        assertEquals(4, bbst[1]);
        assertEquals(12, bbst[2]);

        arr = new int[]{8, 4, 12, 2, 6, 10, 14};
        bbst = AlgorithmsDataStructures2.GenerateBBSTArray(arr);
        assertEquals(8, bbst[0]);
        assertEquals(4, bbst[1]);
        assertEquals(12, bbst[2]);
        assertEquals(2, bbst[3]);
        assertEquals(6, bbst[4]);
        assertEquals(10, bbst[5]);
        assertEquals(14, bbst[6]);

        arr = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 7, 5, 9, 11, 15, 13};
        bbst = AlgorithmsDataStructures2.GenerateBBSTArray(arr);
        assertEquals(8, bbst[0]);
        assertEquals(4, bbst[1]);
        assertEquals(12, bbst[2]);
        assertEquals(2, bbst[3]);
        assertEquals(6, bbst[4]);
        assertEquals(10, bbst[5]);
        assertEquals(14, bbst[6]);
        assertEquals(1, bbst[7]);
        assertEquals(3, bbst[8]);
        assertEquals(5, bbst[9]);
        assertEquals(7, bbst[10]);
        assertEquals(9, bbst[11]);
        assertEquals(11, bbst[12]);
        assertEquals(13, bbst[13]);
        assertEquals(15, bbst[14]);

        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        bbst = AlgorithmsDataStructures2.GenerateBBSTArray(sortedArray);
        assertEquals(5, bbst[0]);
    }
}