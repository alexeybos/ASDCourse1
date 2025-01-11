package org.skillsmart.asd2.lesson2;

public class ShellSort {
    public static void sort(int[] array) {
        for (Integer step: SortLevel.KnuthSequence(array.length)) {
            for (int j = 0; j + step < array.length; j++) {
                SortLevel.InsertionSortStep(array, step, j);
            }
        }
    }
}
