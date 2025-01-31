package org.skillsmart.asd3.lesson6;

import java.util.*;

class BSTNode
{
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int     Level; // глубина узла

    public BSTNode(int key, BSTNode parent)
    {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST
{
    public BSTNode Root; // корень дерева

    public BalancedBST()
    {
        Root = null;
    }

    public void GenerateTree(int[] a)
    {
        // создаём дерево с нуля из неотсортированного массива a
        // ...
    }

    public boolean IsBalanced(BSTNode root_node)
    {
        return false; // сбалансировано ли дерево с корнем root_node
    }
}


