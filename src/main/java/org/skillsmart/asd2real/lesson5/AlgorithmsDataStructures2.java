package org.skillsmart.asd2real.lesson5;

import java.util.*;

public class AlgorithmsDataStructures2
{
    public static int[] GenerateBBSTArray(int[] a)
    {
        Arrays.sort(a);
        int[] resultTree = new int[a.length];
        generateSubTree(a, resultTree, 0, a.length, 0);
        return resultTree;
    }

    private static void generateSubTree(int[] a, int[] resultTree, int rootInd, int length, int offset) {
        if (rootInd >= a.length) return;
        int midInd = length / 2;
        resultTree[rootInd] = a[midInd + offset];
        generateSubTree(a, resultTree, rootInd * 2 + 1, midInd, 0);
        generateSubTree(a, resultTree, rootInd * 2 + 2, length - midInd, midInd + (a.length - midInd) / 2);
    }
}
