package org.skillsmart.asd3.lesson6;

import java.util.*;

class BSTNode
{
    public int NodeKey;
    public BSTNode Parent;
    public BSTNode LeftChild;
    public BSTNode RightChild;
    public int     Level;

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
    public BSTNode Root;

    public BalancedBST()
    {
        Root = null;
    }

    public void GenerateTree(int[] a)
    {
        Arrays.sort(a);
        Root = generateChildren(a, null, 0, a.length - 1, 0);
    }

    private BSTNode generateChildren(int[] a, BSTNode parent, int start, int end, int level) {
        if (start > end) return null;
        int midInd = (start + end) / 2;
        BSTNode node = new BSTNode(a[midInd], parent);
        node.Level = level;
        node.LeftChild = generateChildren(a, node, start, midInd - 1, level + 1);
        node.RightChild = generateChildren(a, node, midInd + 1, end, level + 1);
        return node;
    }

    //Сложность: time O(n), память O(h^2), где h высота дерева
    public boolean IsBalanced(BSTNode root_node)
    {
        return checkTreeDepth(root_node) != -1;
    }

    private int checkTreeDepth(BSTNode node) {
        if (node == null) return 0;
        int left = checkTreeDepth(node.LeftChild);
        if (left == -1) return -1;
        int right = checkTreeDepth(node.RightChild);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    private int checkDeepestLevel(BSTNode node, int parentLevel) {
        if (node == null) return parentLevel;
        //if (node.LeftChild == null && node.RightChild == null) return node.Level;
        int leftSubTreeLevel = checkDeepestLevel(node.LeftChild, node.Level);
        if (leftSubTreeLevel == -1) return -1;
        int rightSubTreeLevel = checkDeepestLevel(node.RightChild, node.Level);
        if (rightSubTreeLevel == -1) return -1;
        if (Math.abs(leftSubTreeLevel - rightSubTreeLevel) > 1) return -1;
        return Math.max(leftSubTreeLevel, rightSubTreeLevel) + node.Level;
    }

    //Сложность: time O(n), память O(h), где h высота дерева
    public boolean isCorrect(BSTNode root_node) {
        if (root_node == null) return true;
        if (root_node.LeftChild == null && root_node.RightChild == null) return true;
        boolean result = true;
        if (root_node.LeftChild != null) {
            if (root_node.LeftChild.NodeKey >= root_node.NodeKey) return false;
            result = isCorrect(root_node.LeftChild);
        }
        if (result && root_node.RightChild != null) {
            if (root_node.RightChild.NodeKey < root_node.NodeKey) return false;
            return isCorrect(root_node.RightChild);
        }
        return result;
    }

}



