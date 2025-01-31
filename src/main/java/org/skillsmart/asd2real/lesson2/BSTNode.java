package org.skillsmart.asd2real.lesson2;

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


    //сложность O(n) - time, пространственная сложность по стеку вызовов: О(h) - высота дерева (в худшем случае O(n))
    public boolean isEqual(BST<T> tree) {
        //if (Count() != tree.Count()) return false; // но тут уже перебор всего дерева. не думаю, что рационально, хотя возможно сэкономим на сравнениях
        return isNodeEqual(Root, tree.Root);
    }

    private boolean isNodeEqual(BSTNode<T> tree1, BSTNode<T> tree2) {
        if (tree1 == null && tree2 == null) return true;
        if (!isValuesEqual(tree1, tree2)) return false;
        if (!isNodeEqual(tree1.LeftChild, tree2.LeftChild)) return false;
        return isNodeEqual(tree1.RightChild, tree2.RightChild);
    }

    private boolean isValuesEqual(BSTNode<T> firstNode, BSTNode<T> secondNode) {
        if (firstNode == null || secondNode == null) return false;
        return firstNode.NodeKey == secondNode.NodeKey && firstNode.NodeValue == secondNode.NodeValue;
    }

    //сложность - время: O(n), пространственная по стеку вызовов: О(length)
    //возвращает список наборов узлов (т.е. путей, от корня до листа), длина которых равна length
    public ArrayList<ArrayList<BSTNode<T>>> allPathsToLeavesWithLength(int length) {
        ArrayList<ArrayList<BSTNode<T>>> paths = new ArrayList<>();
        ArrayList<BSTNode<T>> nodes = new ArrayList<>();
        pathToLeafOnLevel(Root, length, paths, nodes, false);
        return paths;
    }

    private void pathToLeafOnLevel(BSTNode<T> startNode, int length, ArrayList<ArrayList<BSTNode<T>>> paths,
                                   ArrayList<BSTNode<T>> path, boolean deleteParentInPath) {
        if (startNode == null) {
            if (deleteParentInPath) path.removeLast(); //если я пришел слева, то пока не удалять!!!
            return;
        }
        if (length == 0 && startNode.LeftChild == null && startNode.RightChild == null) {
            path.add(startNode);
            paths.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        if (startNode.LeftChild == null && startNode.RightChild == null) {
            if (deleteParentInPath) path.removeLast(); //если я пришел слева, то пока не удалять!!!
            return;
        }
        path.add(startNode);
        pathToLeafOnLevel(startNode.LeftChild, length - 1, paths, path, false);
        pathToLeafOnLevel(startNode.RightChild, length - 1, paths, path, true);
    }

    //Первоначальная реализация, когда возвращаем просто лист, путь к которому от корня занимает length.
    // Путь можно восстановить реверсивно по Parent.
    public ArrayList<BSTNode<T>> allLeavesWithPathLength(int length) {
        ArrayList<BSTNode<T>> nodes = new ArrayList<>();
        isLeafOnLevel(Root, length, nodes);
        return nodes;
    }

    private BSTNode<T> isLeafOnLevel(BSTNode<T> startNode, int length, ArrayList<BSTNode<T>> path) {
        if (startNode == null) return null;
        if (length == 0 && startNode.LeftChild == null && startNode.RightChild == null) return startNode;
        if (startNode.LeftChild == null && startNode.RightChild == null) {
            return null;
        }
        BSTNode<T> leaf;
        leaf = isLeafOnLevel(startNode.LeftChild, length - 1, path);
        if (leaf != null) path.add(leaf);
        leaf = isLeafOnLevel(startNode.RightChild, length - 1, path);
        if (leaf != null) path.add(leaf);
        return null;
    }

    //сложность - время: O(n), пространственная по стеку вызовов: О(h) - высота дерева (в худшем случае O(n))
    //Возвращает список наборов узлов (т.е. путей, от корня до листа) с максимальной суммой узлов по пути (без учета значения листа)
    public ArrayList<ArrayList<BSTNode<T>>> pathsWithMaxSumOfValues() {
        ArrayList<ArrayList<BSTNode<T>>> paths = new ArrayList<>();
        ArrayList<BSTNode<T>> nodes = new ArrayList<>();
        int[] max = new int[1];
        pathToLeavesWithMaxSum(Root, paths, nodes, max,  0, false);
        return paths;
    }

    private void pathToLeavesWithMaxSum(BSTNode<T> node, ArrayList<ArrayList<BSTNode<T>>> paths,
                              ArrayList<BSTNode<T>> path, int[] currentMax, int sum, boolean deleteParentInPath) {
        if (node == null) {
            if (deleteParentInPath) path.removeLast();
            return;
        }
        if (node.RightChild == null && node.LeftChild == null) {
            //это лист - фиксируем путь и сумму
            if (sum >= currentMax[0]) {
                if (sum > currentMax[0]) paths.clear();
                currentMax[0] = sum;
                path.add(node);
                paths.add(new ArrayList<>(path));
                path.removeLast();
            }
            if (deleteParentInPath) path.removeLast();
            return;
        }
        path.add(node);
        pathToLeavesWithMaxSum(node.RightChild, paths, path, currentMax, sum + node.NodeKey, false);
        pathToLeavesWithMaxSum(node.LeftChild, paths, path, currentMax, sum + node.NodeKey, true);
    }

    //сложность - время: O(n), пространственная по стеку вызовов: О(h) - высота дерева (в худшем случае O(n/2))
    public boolean isSymmetric() {
        if (Root == null) return false;
        return isTreesMirroredEqual(Root.LeftChild, Root.RightChild);
    }

    private boolean isTreesMirroredEqual(BSTNode<T> tree1, BSTNode<T> tree2) {
        if (tree1 == null && tree2 == null) return true;
        if (tree1 == null || tree2 == null) return false;
        if (!isTreesMirroredEqual(tree1.LeftChild, tree2.RightChild)) return false;
        return isTreesMirroredEqual(tree1.RightChild, tree2.LeftChild);
    }

    private void myPathToLeafOnLevel(BSTNode<T> startNode, int length, ArrayList<ArrayList<BSTNode<T>>> paths,
                                   ArrayList<BSTNode<T>> path, boolean deleteParentInPath) {
        if (startNode == null) {
            if (deleteParentInPath) path.removeLast(); //если я пришел слева, то пока не удалять!!!
            return;
        }
        if (length == 0 && startNode.LeftChild == null && startNode.RightChild == null) {
            path.add(startNode);
            paths.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        if (startNode.LeftChild == null && startNode.RightChild == null) {
            if (deleteParentInPath) path.removeLast(); //если я пришел слева, то пока не удалять!!!
            return;
        }
        path.add(startNode);
        myPathToLeafOnLevel(startNode.LeftChild, length - 1, paths, path, false);
        myPathToLeafOnLevel(startNode.RightChild, length - 1, paths, path, true);
    }
    //конкретная логика для PathToLeafOnLevel

    private void generalRecursive(BSTNode<T> startNode, int length, ArrayList<ArrayList<BSTNode<T>>> paths,
                                  ArrayList<BSTNode<T>> path, boolean deleteParentInPath) {
        if (startNode == null) {
            if (deleteParentInPath) path.removeLast(); //если я пришел слева, то пока не удалять!!!
            return;
        }

        if (startNode.LeftChild == null && startNode.RightChild == null) {
            if (deleteParentInPath) path.removeLast(); //если я пришел слева, то пока не удалять!!!
            return;
        }

        path.add(startNode);
        myPathToLeafOnLevel(startNode.LeftChild, length - 1, paths, path, false);
        myPathToLeafOnLevel(startNode.RightChild, length - 1, paths, path, true);
    }

    private void myPathToLeavesWithMaxSum(BSTNode<T> node, ArrayList<ArrayList<BSTNode<T>>> paths,
                                        ArrayList<BSTNode<T>> path, int[] currentMax, int sum, boolean deleteParentInPath) {
        if (node == null) {
            if (deleteParentInPath) path.removeLast();
            return;
        }
        if (node.RightChild == null && node.LeftChild == null) {
            //это лист - фиксируем путь и сумму
            if (sum >= currentMax[0]) {
                if (sum > currentMax[0]) paths.clear();
                currentMax[0] = sum;
                path.add(node);
                paths.add(new ArrayList<>(path));
                path.removeLast();
            }
            if (deleteParentInPath) path.removeLast();
            return;
        }
        path.add(node);
        myPathToLeavesWithMaxSum(node.RightChild, paths, path, currentMax, sum + node.NodeKey, false);
        myPathToLeavesWithMaxSum(node.LeftChild, paths, path, currentMax, sum + node.NodeKey, true);
    }
}



