package org.skillsmart.asd2real.lesson3;

import java.io.*;
import java.util.*;

class BSTNode<T>
{
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T>
{
    public BSTNode<T> Node;
    public boolean NodeHasKey;
    public boolean ToLeft;

    public BSTFind() { Node = null; }

    public BSTFind(BSTNode<T> node, boolean nodeHasKey, boolean toLeft) {
        Node = node;
        NodeHasKey = nodeHasKey;
        ToLeft = toLeft;
    }
}

class BST<T>
{
    BSTNode<T> Root;

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        if (Root == null) return new BSTFind<>(null, false, false);
        return FindNodeByKey(key, Root, Root, false);
    }

    private BSTFind<T> FindNodeByKey(int key, BSTNode<T> node, BSTNode<T> prevNode, boolean toLeft) {
        if (node == null) return new BSTFind<>(prevNode, false, toLeft); //закончили поиск;
        BSTFind<T> foundNode;
        if (node.NodeKey == key) {
            return new BSTFind<>(node, true, false); //закончили поиск
        }
        if (node.NodeKey > key) {
            foundNode = FindNodeByKey(key, node.LeftChild, node, true);
        } else {
            foundNode = FindNodeByKey(key, node.RightChild, node, false);
        }
        return foundNode;
    }

    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> nodeToAdd = FindNodeByKey(key);
        if (nodeToAdd.NodeHasKey) return false; // если ключ уже есть
        if (nodeToAdd.Node == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }
        if (nodeToAdd.ToLeft) {
            nodeToAdd.Node.LeftChild = new BSTNode<>(key, val, nodeToAdd.Node);
        } else {
            nodeToAdd.Node.RightChild = new BSTNode<>(key, val, nodeToAdd.Node);
        }
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if (Root == null) return null;
        if (FindMax) return findMax(FromNode);
        return findMin(FromNode);
    }

    private BSTNode<T> findMax(BSTNode<T> node) {
        if (node.RightChild == null) return node;
        return findMax(node.RightChild);
    }

    private BSTNode<T> findMin(BSTNode<T> node) {
        if (node.LeftChild == null) return node;
        return findMin(node.LeftChild);
    }

    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> foundNode = FindNodeByKey(key);
        if (!foundNode.NodeHasKey) return false;
        BSTNode<T> node = foundNode.Node;
        if (node == Root) {
            Root = null;
            return true;
        }

        if (node.RightChild == null && node.LeftChild == null) {
            replaceLinkInParent(node, null);
            return true;
        }

        if (node.RightChild == null) {
            node.LeftChild.Parent = node.Parent;
            replaceLinkInParent(node, node.LeftChild);
            return true;
        }

        if (node.LeftChild == null) {
            node.RightChild.Parent = node.Parent;
            replaceLinkInParent(node, node.RightChild);
            return true;
        }

        BSTNode<T> deepNode = node.RightChild;
        for (; deepNode.LeftChild != null;) {
            deepNode = deepNode.LeftChild;
        }

        if (deepNode.RightChild != null) {
            node.NodeValue = deepNode.NodeValue;
            node.NodeKey = deepNode.NodeKey;
            deepNode.NodeKey = deepNode.RightChild.NodeKey;
            deepNode.NodeValue = deepNode.RightChild.NodeValue;
            deepNode.RightChild = null;
            return true;
        }
        node.NodeValue = deepNode.NodeValue;
        node.NodeKey = deepNode.NodeKey;
        replaceLinkInParent(deepNode, null);
        return true;
    }

    private void replaceLinkInParent(BSTNode<T> child, BSTNode<T> newChild) {
        if (child.Parent.LeftChild == child) {
            child.Parent.LeftChild = newChild;
            return;
        }
        child.Parent.RightChild = newChild;
    }

    public int Count()
    {
        return childrenCount(Root); // количество узлов в дереве
    }

    private int childrenCount(BSTNode<T> node) {
        if (node == null) return 0;
        return 1 + childrenCount(node.LeftChild) + childrenCount(node.RightChild);
    }

    public ArrayList<BSTNode> DeepAllNodes(int order) {
        //0 (in-order), 1 (post-order) и 2 (pre-order)
        ArrayList<BSTNode> nodes = new ArrayList<>();
        getNextNodes(Root, nodes, order);
        return nodes;
    }

    private void getNextNodes(BSTNode<T> node, ArrayList<BSTNode> nodes, int order) {
        if (node == null) return;
        if (order == 2) nodes.add(node);
        getNextNodes(node.LeftChild, nodes, order);
        if (order == 0) nodes.add(node);
        getNextNodes(node.RightChild, nodes, order);
        if (order == 1) nodes.add(node);
    }

    //TODO доп. задания

    //time - O(n) , пространственная сложность по стеку вызовов: О(h) - высота дерева (в худшем случае O(n))
    public void invert() {
        swapChildren(Root);
    }

    private void swapChildren(BSTNode<T> node) {
        if (node == null) return;
        swapChildren(node.LeftChild);
        swapChildren(node.RightChild);
        BSTNode<T> tmpForSwap = node.LeftChild;
        node.LeftChild = node.RightChild;
        node.RightChild = tmpForSwap;
    }

    //стандартный обход дерева в ширину (с помощью стека)
    public ArrayList<BSTNode> WideAllNodesClassic() {
        if (Root == null) return new ArrayList<BSTNode>();
        ArrayList<BSTNode> nodes = new ArrayList<>();
        Stack<BSTNode> curLevel = new Stack<>();
        curLevel.push(Root);
        for (; !curLevel.isEmpty();) {
            nodes.add(curLevel.pop());
            if (nodes.getLast().LeftChild != null) curLevel.push(nodes.getLast().LeftChild);
            if (nodes.getLast().RightChild != null) curLevel.push(nodes.getLast().RightChild);
        }
        return nodes;
    }

    public ArrayList<BSTNode> WideAllNodes() {
        if (Root == null) return new ArrayList<BSTNode>();
        ArrayList<BSTNode> nodes = new ArrayList<>();
        Stack<BSTNode> curLevel = new Stack<>();
        curLevel.push(Root);
        for (; !curLevel.isEmpty();) {
            nodes.addAll(curLevel);
            curLevel = getNextLevelNodes(curLevel);
        }
        return nodes;
    }

    //выделенный шаг обхода в ширину для возможности анализа узлов каждого уровня отдельно
    private Stack<BSTNode> getNextLevelNodes(Stack<BSTNode> curLevel) {
        Stack<BSTNode> nextLevel = new Stack<>();
        for (; !curLevel.isEmpty();) {
            BSTNode node = curLevel.pop();
            if (node.LeftChild != null) nextLevel.push(node.LeftChild);
            if (node.RightChild != null) nextLevel.push(node.RightChild);
        }
        return nextLevel;
    }

    //time - O(n), память: О(n) - (O(n) на хранение узлов + по стеку вызовов O(h) высота дерева (в худшем случае O(n))
    //возвращает первый встреченный уровень с максимальной суммой
    public ArrayList<BSTNode> getLevelWithMaxSum() {
        if (Root == null) return null;
        ArrayList<BSTNode> levelWithMaxSum = new ArrayList<>();
        int max = 0;
        Stack<BSTNode> curLevel = new Stack<>();
        curLevel.push(Root);
        for (; !curLevel.isEmpty();) {
            int levelSum = getNodesSum(curLevel);
            if (levelSum > max) {
                max = levelSum;
                levelWithMaxSum.clear();
                levelWithMaxSum.addAll(curLevel);
            }
            curLevel = getNextLevelNodes(curLevel);
        }
        return levelWithMaxSum;
    }

    private int getNodesSum(Stack<BSTNode> level) {
        int sum = 0;
        for (BSTNode bstNode : level) {
            sum += bstNode.NodeKey;
        }
        return sum;
    }

    //нужны оба прохода для однозначного определения взаимного положения узлов, т.к. по префиксному проходу мы можем понять только
    //порядок следования узлов, а вот их взаимная конфигурация относительно друг друга проясняется по смещениям "корней" в инфиксном проходе
    //время O(nlogn) - рекурсивный обход всех узлов + поиск текущего корня в массиве inOrder,
    //пространственная: О(nlogn) - (по стеку вызовов O(h) высота дерева (в худшем случае O(n) и + O(nlogn) на хранение временных массивов)
    public void restoreTree(int[] preOrder, int[] inOrder) {
        int[] rootPointer = {0};
        Root = restoreSubTree(null, preOrder, inOrder, rootPointer);
    }

    private BSTNode<T> restoreSubTree(BSTNode<T> parent, int[] preOrder, int[] inOrder, int[] rootPointer) {
        if (inOrder.length == 0) return null;

        BSTNode<T> curRoot = new BSTNode<>(preOrder[rootPointer[0]], null, parent);
        int rootPos = findIndexOfValInArr(inOrder, preOrder[rootPointer[0]]);
        int[] left = new int[rootPos];
        System.arraycopy(inOrder, 0, left, 0, rootPos);
        int[] right = new int[inOrder.length - rootPos - 1];
        System.arraycopy(inOrder, rootPos + 1, right, 0, inOrder.length - rootPos - 1);

        rootPointer[0] += 1;
        curRoot.LeftChild = restoreSubTree(curRoot, preOrder, left, rootPointer);

        curRoot.RightChild = restoreSubTree(curRoot, preOrder, right, rootPointer);
        return curRoot;
    }

    private int findIndexOfValInArr(int[] array, int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == val) return i;
        }
        return -1;
    }
}



