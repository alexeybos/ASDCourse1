package org.skillsmart.asd2.lesson8;

import java.util.*;

public class SortLevel {
    public static ArrayList<Integer> MergeSort(ArrayList<Integer> array)
    {
        if (array.size() == 1) return array;
        ArrayList<Integer> leftArray = MergeSort(new ArrayList<>(array.subList(0, array.size() / 2)));
        ArrayList<Integer> rightArray = MergeSort(new ArrayList<>(array.subList(array.size() / 2, array.size())));
        ArrayList<Integer> mergeArray = new ArrayList<>();
        for (; !leftArray.isEmpty() && !rightArray.isEmpty();) {
            if (leftArray.getFirst() < rightArray.getFirst()) {
                mergeArray.add(leftArray.removeFirst());
            } else {
                mergeArray.add(rightArray.removeFirst());
            }
        }
        if (leftArray.isEmpty()) {
            mergeArray.addAll(rightArray);
        } else {
            mergeArray.addAll(leftArray);
        }
        return mergeArray;
    }
}



