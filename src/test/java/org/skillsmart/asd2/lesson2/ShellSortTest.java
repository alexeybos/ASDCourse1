package org.skillsmart.asd2.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShellSortTest {

    @Test
    void sort() {
        int[] array = {7,6,5,4,3,2,1};
        ShellSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            assertEquals(i + 1, array[i]);
            System.out.print(array[i] + ",");
        }
        System.out.println("\n----");
        int[] array2 = {7,8,12,6,5,11,4,13,3,9,14,2,1,10};
        ShellSort.sort(array2);
        for (int i = 0; i < array2.length; i++) {
            assertEquals(i + 1, array2[i]);
            System.out.print(array2[i] + ",");
        }
    }
}