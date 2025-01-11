package org.skillsmart.asd2.lesson1;

import java.util.*;

public class SortLevel {
    public static void SelectionSortStep(int[] array, int i)
    {
        int currentMin = array[i];
        int minIndex = i;
        for (int j = i + 1; j < array.length; j++) {
            if (currentMin > array[j]) {
                currentMin = array[j];
                minIndex = j;
            }
        }
        int tmpForExchange = array[i];
        array[i] = array[minIndex];
        array[minIndex] = tmpForExchange;
    }

    public static boolean BubbleSortStep(int[] array)
    {
        boolean isUnchanged = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                int tmpForExchange = array[i];
                array[i] = array[i + 1];
                array[i + 1] = tmpForExchange;
                isUnchanged = false;
            }
        }
        return isUnchanged;
    }
}



