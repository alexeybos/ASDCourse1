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
    }
}