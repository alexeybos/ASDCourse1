package org.skillsmart.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultidimensionalDynArrayTest {

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

    @Test
    void setItem_3d() {
        MultidimensionalDynArray<Integer> array = new MultidimensionalDynArray<>(Integer.class, 2, 2, 3);
        int[][] arrayJava = new int[2][3];
        array.setItem(0, 0, 0, 0);
        array.setItem(1, 0, 0, 1);
        array.setItem(2, 0, 0, 2);
        array.setItem(3, 0, 1, 0);
        array.setItem(4, 0, 1, 1);
        array.setItem(5, 0, 1, 2);
        array.setItem(6, 1, 0, 0);
        array.setItem(7, 1, 0, 1);
        array.setItem(8, 1, 0, 2);
        array.setItem(9, 1, 1, 0);
        array.setItem(10, 1, 1, 1);
        array.setItem(11, 1, 1, 2);

        assertEquals(12, array.count);
    }

    @Test
    void getItem_3d() {
        MultidimensionalDynArray<Integer> array = new MultidimensionalDynArray<>(Integer.class, 2, 2, 3);
        array.setItem(0, 0, 0, 0);
        array.setItem(1, 0, 0, 1);
        array.setItem(2, 0, 0, 2);
        array.setItem(3, 0, 1, 0);
        array.setItem(4, 0, 1, 1);
        array.setItem(5, 0, 1, 2);
        array.setItem(6, 1, 0, 0);
        array.setItem(7, 1, 0, 1);
        array.setItem(8, 1, 0, 2);
        array.setItem(9, 1, 1, 0);
        array.setItem(10, 1, 1, 1);
        array.setItem(11, 1, 1, 2);
        assertEquals(5, array.getItem(0, 1, 2));
        assertEquals(10, array.getItem(1, 1, 1));
    }

    @Test
    void testSetItem_WithResize() {
        MultidimensionalDynArray<Integer> array = new MultidimensionalDynArray<>(Integer.class, 1, 1);
        array.setItem(123, 0, 0);
        array.setItem(1231, 0, 1);
        assertEquals(2, array.count);
        assertEquals(1231, array.getItem(0, 1));
    }

    @Test
    void testSetItem_3DWithResize() {
        MultidimensionalDynArray<Integer> array = new MultidimensionalDynArray<>(Integer.class, 2, 2, 2);
        array.setItem(123, 0, 0, 0);
        array.setItem(1231, 0, 1, 0);
        array.setItem(12312, 0, 1, 2);
        assertEquals(3, array.count);
        assertEquals(12312, array.getItem(0, 1, 2));
    }
}