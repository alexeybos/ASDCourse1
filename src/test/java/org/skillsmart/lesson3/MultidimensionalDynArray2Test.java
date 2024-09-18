package org.skillsmart.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultidimensionalDynArray2Test {

    @Test
    void makeDimension() {
        MultidimensionalDynArray2 array = new MultidimensionalDynArray2(2, 4, 5);
        int[][] arrayJava = new int[4][5];
        assertEquals(0, arrayJava.length);
    }
}