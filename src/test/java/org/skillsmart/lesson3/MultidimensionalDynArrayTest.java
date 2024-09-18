package org.skillsmart.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultidimensionalDynArrayTest {

    @Test
    void makeArray() {
        MultidimensionalDynArray<Integer> array = new MultidimensionalDynArray<>(Integer.class, 2, 3);
        int[][] arrayJava = new int[2][3];
        assertEquals(4, arrayJava.length);
    }

    @Test
    void setItem() {
        MultidimensionalDynArray<Integer> array = new MultidimensionalDynArray<>(Integer.class, 2, 3);
        int[][] arrayJava = new int[2][3];
        array.setItem(123, 0, 0);
        array.setItem(1231, 0, 1);
        array.setItem(1232, 0, 2);
        array.setItem(456, 1, 0);
        array.setItem(4561, 1, 1);
        array.setItem(4562, 1, 2);
        assertEquals(6, array.count);
    }

    @Test
    void getItem() {
        MultidimensionalDynArray<Integer> array = new MultidimensionalDynArray<>(Integer.class, 2, 3);
        int[][] arrayJava = new int[2][3];
        array.setItem(123, 0, 0);
        array.setItem(1231, 0, 1);
        array.setItem(1232, 0, 2);
        array.setItem(456, 1, 0);
        array.setItem(4561, 1, 1);
        array.setItem(4562, 1, 2);
        assertEquals(6, array.count);
        assertEquals(4561, array.getItem(1, 1));
    }
}