package org.skillsmart.asd2.lesson8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortLevelTest {

    @Test
    void mergeSort() {
        ArrayList<Integer> arraySmall = new ArrayList<>(Arrays.asList(6,3));
        ArrayList<Integer> resultSmall = SortLevel.MergeSort(arraySmall);
        assertEquals(3, resultSmall.get(0));
        assertEquals(6, resultSmall.get(1));
        for (int i = 0; i < 2; i++) {
            System.out.print(resultSmall.get(i) + ",");
        }
        System.out.println();
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(6,3,5,7,1,4,2));
        ArrayList<Integer> result = SortLevel.MergeSort(array);
        for (int i = 0; i < result.size(); i++) {
            assertEquals(i + 1, result.get(i));
            System.out.print(result.get(i) + ",");
        }
        System.out.println();
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(7,8,12,6,5,11,4,13,3,15,9,14,2,1,10));
        ArrayList<Integer> result2 = SortLevel.MergeSort(array2);
        for (int i = 0; i < result2.size(); i++) {
            assertEquals(i + 1, result2.get(i));
            System.out.print(result2.get(i) + ",");
        }

        System.out.println();
        ArrayList<Integer> array3 = new ArrayList<>(Arrays.asList(1,2,3,4,0));
        ArrayList<Integer> result3 = SortLevel.MergeSort(array3);
        for (int i = 0; i < result3.size(); i++) {
            assertEquals(i, result3.get(i));
            System.out.print(result3.get(i) + ",");
        }
    }
}