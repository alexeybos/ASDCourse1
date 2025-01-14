package org.skillsmart.asd2.lesson5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortLevelTest {

    @Test
    void quickSort() {
        int[] array4 = {1,2,3,7,0};
        SortLevel.QuickSort(array4, 0, array4.length - 1);
        assertEquals(0, array4[0]);
        assertEquals(1, array4[1]);
        assertEquals(2, array4[2]);
        assertEquals(3, array4[3]);
        assertEquals(7, array4[4]);
        for (int i = 0; i < array4.length; i++) {
            System.out.print(array4[i] + ",");
        }
        System.out.println(" ");
        int[] array2 = {7,5,4,3,1,2};
        SortLevel.QuickSort(array2, 0, array2.length - 1);
        assertEquals(1, array2[0]);
        assertEquals(2, array2[1]);
        assertEquals(3, array2[2]);
        assertEquals(4, array2[3]);
        assertEquals(5, array2[4]);
        assertEquals(7, array2[5]);
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + ",");
        }

        int[] array = {7,6,5,4,3,2,1};
        SortLevel.QuickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            assertEquals(i + 1, array[i]);
            System.out.print(array[i] + ",");
        }
        System.out.println("\n----");
        int[] array3 = {7,8,12,6,5,11,4,13,3,15,9,14,2,1,10};
        SortLevel.QuickSort(array3, 0, array3.length - 1);
        for (int i = 0; i < array3.length; i++) {
            assertEquals(i + 1, array3[i]);
            System.out.print(array3[i] + ",");
        }
    }
}