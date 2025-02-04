package org.skillsmart.asd2real.lesson4;

import java.util.*;
import java.util.function.BiConsumer;

class aBST
{
    public Integer Tree [];

    public aBST(int depth)
    {
        int tree_size = (int) Math.pow(2, depth + 1) - 1;
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
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
        BFSFullTraversal(add);
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

    //Сложность - time: O(n*logn) из-за использования сортировки при пересоздании дерева (в худшем в принципе даже может O(n^2)); память - O(n)
    public boolean deleteKey(int key) {
        if (Tree[0] == null) return false;
        Integer index = FindKeyIndex(key);
        if (index == null || index < 0) return false;
        Tree[index] = null;
        reGenerateTree();
        return true;
    }

    public void reGenerateTree() {
        ArrayList<Integer> wideArr = WideAllNodes();
        Collections.sort(wideArr);
        Arrays.fill(Tree, null);
        generateSubTree(wideArr, 0, 0, wideArr.size() - 1);
    }

    private void generateSubTree(ArrayList<Integer> a, int rootInd, int start, int end) {
        if (start > end && rootInd >= a.size()) return;
        if (start > end) {
            Tree[rootInd] = null;
            return;
        }
        int midInd = (start + end) / 2;
        Tree[rootInd] = a.get(midInd);
        generateSubTree(a,rootInd * 2 + 1, start, midInd - 1);
        generateSubTree(a,rootInd * 2 + 2, midInd + 1, end);
    }

    //сложность: time - поиск узла O(log n), сама замена O(h). Пространство - O(h) - глубина рекурсии для "подтягивания" потомков
    public boolean removeKey(int key) {
        if (Tree[0] == null) return false;
        Integer index = FindKeyIndex(key);
        if (index == null || index < 0) return false;
        int leftChildInd = index * 2 + 1;
        int rightChildInd = index * 2 + 2;
        Integer leftChild = null;
        Integer rightChild = null;
        if (leftChildInd < Tree.length) leftChild = Tree[leftChildInd];
        if (rightChildInd < Tree.length) rightChild = Tree[rightChildInd];
        //лист
        if (leftChild == null && rightChild == null) {
            Tree[index] = null;
            return true;
        }
        //есть только левый
        if (rightChild == null) {
            Tree[index] = leftChild;
            Tree[leftChildInd] = null;
            adjustChildren(leftChildInd, index);
            return true;
        }
        //только правый
        if (leftChild == null) {
            Tree[index] = rightChild;
            Tree[rightChildInd] = null;
            adjustChildren(rightChildInd, index);
            return true;
        }
        //есть оба
        int deepNodeInd = rightChildInd;
        int nextInd = rightChildInd * 2 + 1;
        for (; nextInd < Tree.length && Tree[nextInd] != null; nextInd = nextInd * 2 + 1) {
            deepNodeInd = deepNodeInd * 2 + 1;
        }

        //есть правый
        rightChildInd = deepNodeInd * 2 + 2;
        if (rightChildInd < Tree.length && Tree[rightChildInd] != null) {
            Tree[index] = Tree[deepNodeInd];
            Tree[deepNodeInd] = Tree[rightChildInd];
            //надо "поднять" всех наследников
            adjustChildren(rightChildInd, deepNodeInd);
            return true;
        }
        Tree[index] = Tree[deepNodeInd];
        Tree[deepNodeInd] = null;
        return true;
    }

    private void adjustChildren(int oldInd, int newInd) {
        //"поднимаем" наследников на новое место
        int leftChild = oldInd * 2 + 1;
        int newLeftInd = newInd * 2 + 1;
        if (leftChild < Tree.length && Tree[leftChild] != null) {
            Tree[newLeftInd] = Tree[leftChild];
            Tree[leftChild] = null;
            adjustChildren(leftChild, newLeftInd);
        }
        int rightChild = oldInd * 2 + 1;
        int newRightInd = newInd * 2 + 2;
        if (rightChild < Tree.length && Tree[rightChild] != null) {
            Tree[newRightInd] = Tree[rightChild];
            Tree[rightChild] = null;
            adjustChildren(leftChild, newLeftInd);
        }
    }



    private void reBalance() {

    }

    private boolean isNeedReBalance(){

        return true;
    }
}



