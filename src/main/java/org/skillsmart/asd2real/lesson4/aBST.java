package org.skillsmart.asd2real.lesson4;

import java.util.*;
import java.util.function.BiConsumer;

class aBST
{
    public Integer Tree [];

    public aBST(int depth)
    {
        int tree_size = getSizeByDepth(depth, 0);
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    private int getSizeByDepth(int depth, int levelSum) {
        if (depth == 0) return levelSum + 1;
        double nodesCnt = Math.pow(2, depth);
        return getSizeByDepth(depth - 1, (int) nodesCnt + levelSum);
    }

    public Integer FindKeyIndex(int key)
    {
        return findKeyIndex(key, 0);
    }

    private Integer findKeyIndex(int key, int currentNode) {
        if (currentNode > Tree.length - 1) return null;
        if (Tree[currentNode] == null) return -currentNode;
        if (Tree[currentNode] == key) return currentNode;
        if (Tree[currentNode] > key) return findKeyIndex(key, 2 * currentNode + 1);
        return findKeyIndex(key, 2 * currentNode + 2);
    }

    public int AddKey(int key)
    {
        Integer indexToAdd = FindKeyIndex(key);
        if (indexToAdd == null) return -1;
        indexToAdd = Math.abs(indexToAdd);
        Tree[indexToAdd] = key;
        return indexToAdd;
    }

    //TODO additional tasks

    //время - поиск узла по значению O(logn), сам дальнейший поиск LCA узлов O(h), где h - глубина дерева (если искомые узлы на последнем уровне дерева, а LCA - корень)
    //пространство - O(1)
    public int getLCA(int key1, int key2) {
        if (Tree[0] == null) return -1;
        Integer index1 = FindKeyIndex(key1);
        Integer index2 = FindKeyIndex(key2);
        if (index1 == null || index2 == null || index1 < 0 || index2 < 0) return -1;

        int level1 = (int) (Math.log(index1 + 1) / Math.log(2));
        int level2 = (int) (Math.log(index2 + 1) / Math.log(2));

        int levelsUp = Math.abs(level1 - level2);
        int divider = (int) Math.pow(2, levelsUp);
        if (level1 > level2) index1 = (index1 - divider + 1) / divider;
        if (level2 > level1) index2 = (index2 - divider + 1) / divider;

        for (; !Objects.equals(Tree[index1], Tree[index2]);) {
            index1 = (index1 - 1) / 2;
            index2 = (index2 - 1) / 2;
        }
        return index1;
    }

    public ArrayList<Integer> WideAllNodes() {
        ArrayList<Integer> nodes = new ArrayList<>();
        BiConsumer<Integer, Integer> add = (node, level) -> nodes.add(node);
        BFS(add);
        return nodes;
    }

    //время O(n), пространство O(n) ну или можно уточнить, что O(n/2)
    private void BFS(BiConsumer<Integer, Integer> processNode) {
        if (Tree[0] == null) return;
        int currentLevel = 0;
        Queue<Integer> nodes = new LinkedList<>();
        nodes.add(0);
        for (; !nodes.isEmpty();) {
            int levelSize = nodes.size();
            for (int i = 0; i < levelSize; i++) {
                Integer nodeIndex = nodes.poll();
                int nextLeft = (nodeIndex * 2) + 1;
                if (nextLeft < Tree.length && Tree[nextLeft] != null) nodes.add(nextLeft);
                int nextRight = (nodeIndex * 2) + 2;
                if (nextRight < Tree.length && Tree[nextRight] != null) nodes.add(nextRight);
                processNode.accept(Tree[nodeIndex], currentLevel);
            }
            currentLevel++;
        }
    }

    //время O(Nmax), где Nmax - это максимальное кол-во узлов дерева глубиной N, т.к. мы обходим все, независимо от заполненности
    //пространство O(1)
    private void BFSFullTraversal(BiConsumer<Integer, Integer> processNode) {
        if (Tree[0] == null) return;
        for (int i = 0; i < Tree.length; i++) {
            if (Tree[i] != null) {
                processNode.accept(Tree[i], (int) (Math.log(i + 1) / Math.log(2)));
            }
        }
    }
}



