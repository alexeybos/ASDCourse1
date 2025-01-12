package org.skillsmart.asd2.lesson2;

import java.util.*;

public class SortLevel {
    public static void InsertionSortStep(int[] array, int step, int i)
    {
        for (int j = i + step; j < array.length; j += step) {
            int tmpForExchange = array[j];
            for (int k = j - step; k >= i && array[k] > tmpForExchange; k -= step) {
                array[k + step] = array[k];
                array[k] = tmpForExchange;
            }
        }
    }

    public static ArrayList<Integer> KnuthSequence(int array_size)
    {
        ArrayList<Integer> knuthSequence = new ArrayList<>();
        if (array_size == 0) return knuthSequence;
        knuthSequence.add(1);
        for (; ;) {
            int nextStep = 3 * knuthSequence.getFirst() + 1;
            if (nextStep < array_size) {
                knuthSequence.addFirst(nextStep);
            } else {
                return knuthSequence;
            }
        }
    }

    public static void shellSort(int[] array) {
        for (Integer step: KnuthSequence(array.length)) {
            for (int j = 0; j + step < array.length; j++) {
                InsertionSortStep(array, step, j);
            }
        }
    }
}


