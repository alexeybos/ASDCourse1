package org.skillsmart.asd2.lesson9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    void getNextMax() {
        int[] array = {7,2,8,4};
        HeapSort heap = new HeapSort(array);
        for (int i : heap.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();
        int[] array1 = {7,3,8,4,2};
        HeapSort heap1 = new HeapSort(array1);
        for (int i : heap1.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();
        int[] array2 = {11,9,4,7,8,3,1,2,5,6};
        HeapSort heap2 = new HeapSort(array2);
        for (int i : heap2.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();

        int nextMax = heap.GetNextMax();
        assertEquals(8, nextMax);
        for (int i : heap.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();
        nextMax = heap.GetNextMax();
        assertEquals(7, nextMax);
        for (int i : heap.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();
        nextMax = heap.GetNextMax();
        assertEquals(4, nextMax);
        for (int i : heap.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();
        nextMax = heap.GetNextMax();
        assertEquals(2, nextMax);
        for (int i : heap.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();
        nextMax = heap.GetNextMax();
        assertEquals(-1, nextMax);

        nextMax = heap1.GetNextMax();
        assertEquals(8, nextMax);
        for (int i : heap1.HeapObject.HeapArray) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}