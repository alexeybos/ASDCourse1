package org.skillsmart.asd2.lesson4;

import java.util.*;

public class SortLevel {
    public static int ArrayChunk(int[] array)
    {
        for (;;) {
            int pivotIndex = array.length / 2;
            int pivot = array[pivotIndex];
            int i1 = 0;
            int i2 = array.length - 1;
            for (;;) {
                for (; array[i1] < pivot; i1++) {
                }
                for (; array[i2] > pivot; i2--) {
                }
                if (i1 == i2 || (i1 == i2 - 1 && array[i1] < array[i2])) {
                    return pivotIndex;
                }
                int tmpForExchange = array[i2];
                array[i2] = array[i1];
                array[i1] = tmpForExchange;
                if (i1 == i2 - 1) {
                    break;
                }
                if (i1 == pivotIndex) {
                    pivotIndex = i2;
                } else if (i2 == pivotIndex) {
                    pivotIndex = i1;
                }
            }
        }
    }
}


