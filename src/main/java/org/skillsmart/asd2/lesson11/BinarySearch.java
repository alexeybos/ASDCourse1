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
}


