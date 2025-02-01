package org.skillsmart.asd2real.lesson4;

import java.util.*;

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
        levelSum = getSizeByDepth(depth - 1, (int) nodesCnt + levelSum);
        return levelSum;
    }

    public Integer FindKeyIndex(int key)
    {
        return findKeyIndex(key, 0);
    }

    private Integer findKeyIndex(int key, int currentNode) {
        if (currentNode > Tree.length) return null;
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

    public int getLCA(int key1, int key2) {
        if (Tree[0] == null) return -1;
        Integer index1 = FindKeyIndex(key1);
        Integer index2 = FindKeyIndex(key2);
        //тут не ищу общего предка если узлов нет (index < 0), но могли бы быть (хотя в принципе ничего не мешает и у потенциальных узлов найти общего предка)
        if (index1 == null || index2 == null || index1 < 0 || index2 < 0) return -1;
        int level1 = (int) (Math.log(index1) / Math.log(2));
        int level2 = (int) (Math.log(index2) / Math.log(2));
        for (; level1 > level2; level1--) {
            index1 = (index1 - 1) / 2;
        }
        for (; level2 > level1; level2--) {
            index2 = (index2 - 1) / 2;
        }
        for (; !Objects.equals(Tree[index1], Tree[index2]);) {
            index1 = (index1 - 1) / 2;
            index2 = (index2 - 1) / 2;
        }
        return index1;
    }

    public void WideAllNodes() {
        if (Tree[0] == null) return;
        int currentLevel = 0;
        /*ArrayList<BSTNode> nodes = new ArrayList<>();
        Queue<BSTNode> curLevel = new LinkedList<>();
        curLevel.add(Root);
        for (; !curLevel.isEmpty();) {
            int levelSize = curLevel.size();
            for (int i = 0; i < levelSize; i++) {
                BSTNode node = curLevel.poll();
                nodes.add(node);
                if (node.LeftChild != null) curLevel.add(node.LeftChild);
                if (node.RightChild != null) curLevel.add(node.RightChild);
            }
            if (!processor.processLevel(nodes, currentLevel)) return;
            currentLevel++;
            nodes.clear();
        }*/
    }
}



