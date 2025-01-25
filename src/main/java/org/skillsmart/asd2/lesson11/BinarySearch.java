package org.skillsmart.asd2.lesson11;

import java.util.*;

public class BinarySearch {

    public int[] array;
    public int Left;
    public int Right;
    private int findResult;

    public BinarySearch(int[] sortedArray) {
        array = sortedArray;
        Left = 0;
        Right = array.length - 1;
        findResult = 0;
    }

    public void Step(int N) {
        if (GetResult() != 0) return;
        int mid = (Left + Right) / 2;
        if (array[mid] == N) {
            findResult = 1;
            return;
        }
        if (array[mid] > N) {
            Right = Math.max(Left, mid - 1);
        } else {
            Left = Math.min(Right, mid + 1);
        }
        if (Left == Right || Right - Left == 1) {
            if (array[Left] == N || array[Right] == N) {
                findResult = 1;
            } else {
                findResult = -1;
            }
        }
    }

    public int GetResult() {
        return findResult;
    }

    public boolean GallopingSearch(int[] sortedArr, int N) {
        int index = 1;
        int i = 1;
        for (; index < sortedArr.length - 1 && sortedArr[index] <= N; i++, index = getIndexForGalloping(i)) {
            if (sortedArr[index] == N) return true;
        }
        if (index > sortedArr.length - 1) index = sortedArr.length - 1;
        array = sortedArr;
        findResult = 0;
        this.Left = getIndexForGalloping(i - 1) + 1;
        this.Right = index;
        for (; GetResult() == 0;) {
            Step(N);
        }
        return findResult == 1;
    }

    private int getIndexForGalloping(int i) {
        Double index = Math.pow(2, i) - 2;
        return index.intValue();
    }
}


