package org.skillsmart.asd2.lesson5;

public class SortLevel {
    public static int ArrayChunk(int[] array, int left, int right)
    {
        for (;;) {
            int pivotIndex = ((right - left + 1) / 2) + left;
            int pivot = array[pivotIndex];
            int i1 = left;
            int i2 = right;
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

    public static void QuickSort(int[] array, int left, int right)
    {
        if (left >= right) return;
        int pivotIndex = ArrayChunk(array, left, right);
        QuickSort(array, left, pivotIndex - 1);
        QuickSort(array, pivotIndex + 1, right);
    }
}



