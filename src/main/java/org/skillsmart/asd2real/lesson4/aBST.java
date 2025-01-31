package org.skillsmart.asd2real.lesson4;

import java.util.*;

class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = getSizeByDepth(depth, 0);
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    private int getSizeByDepth(int depth, int levelSum) {
        if (depth == 0) return levelSum + 1;
        double nodesCnt = Math.pow(2, depth);
        levelSum = getSizeByDepth(depth - 1, (int) nodesCnt + levelSum);
        return levelSum;
    }

    public Integer FindKeyIndex(int key)
    {
        for (int i = 0; i < 0; i = (i - 1) / 2) {

        }
        // ищем в массиве индекс ключа
        return null; // не найден
    }

    private Integer findKeyIndex(int key, int currentNode) {
        if (currentNode > Tree.length) return null;
        if (Tree[currentNode] == key) return currentNode;
        return findKeyIndex(key, 2 * currentNode + 1);
    }

    public int AddKey(int key)
    {
        Integer indexToAdd = FindKeyIndex(key);
        if (indexToAdd == null) return -1;
        indexToAdd = Math.abs(indexToAdd);
        Tree[indexToAdd] = key;
        return indexToAdd;
    }

}



