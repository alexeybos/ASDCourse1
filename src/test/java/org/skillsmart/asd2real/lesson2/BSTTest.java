package org.skillsmart.asd2real.lesson2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    public BST<Integer> bTree;
    public BSTNode<Integer> node0;

    /**
     * Базовое тестовое дерево:
     *                  8
     *               /     \
     *              4       12
     *            /  \     /  \
     *           2    6   10   14
     *         /
     *        1
     */
    @BeforeEach
    void setUp() {
        node0 = new BSTNode<>(8, 8, null);
        /*node1 = new BSTNode<>(4, 4, node0);
        node2 = new BSTNode<>(12, 12, node0);
        node3 = new BSTNode<>(2, 2, node1);
        node4 = new BSTNode<>(6, 6, node1);
        node5 = new BSTNode<>(10, 10, node2);
        node6 = new BSTNode<>(14, 14, node2);
        node7 = new BSTNode<>(1, 1, node3);*/
        bTree = new BST<>(node0);
        bTree.AddKeyValue(4, 4);
        bTree.AddKeyValue(12, 12);
        bTree.AddKeyValue(2, 2);
        bTree.AddKeyValue(6, 6);
        bTree.AddKeyValue(10, 10);
        bTree.AddKeyValue(14, 14);
        bTree.AddKeyValue(1, 1);
    }

    @Test
    void testFindNodeByKey_Positive() {
        BSTFind<Integer> foundNode = bTree.FindNodeByKey(10);
        assertTrue(foundNode.NodeHasKey);
        assertFalse(foundNode.ToLeft);
        assertEquals(10, foundNode.Node.NodeKey);
        assertEquals(10, foundNode.Node.NodeValue);
        assertEquals(12, foundNode.Node.Parent.NodeKey);
    }

    @Test
    void testFindNodeByKey_NegativeToLeft() {
        bTree.AddKeyValue(3, 3);
        BSTFind<Integer> foundNode = bTree.FindNodeByKey(5);
        assertFalse(foundNode.NodeHasKey);
        assertTrue(foundNode.ToLeft);
        assertEquals(6, foundNode.Node.NodeKey);
        assertNull(foundNode.Node.LeftChild);
        assertEquals(4, foundNode.Node.Parent.NodeKey);

    }

    @Test
    void testFindNodeByKey_NegativeToRight() {
        BSTFind<Integer> foundNode = bTree.FindNodeByKey(3);
        assertFalse(foundNode.NodeHasKey);
        assertFalse(foundNode.ToLeft);
        assertEquals(2, foundNode.Node.NodeKey);
        assertEquals(4, foundNode.Node.Parent.NodeKey);
    }

    @Test
    void testAddKeyValue_ToLeft() {
        assertEquals(8, bTree.Count());
        assertTrue(bTree.AddKeyValue(3, 3));
        assertEquals(9, bTree.Count());
        assertNull(node0.LeftChild.RightChild.LeftChild);
        assertEquals(2, node0.LeftChild.LeftChild.RightChild.Parent.NodeKey);
        assertTrue(bTree.AddKeyValue(5, 5));
        assertEquals(10, bTree.Count());
        assertEquals(5, node0.LeftChild.RightChild.LeftChild.NodeKey);
        assertEquals(6, node0.LeftChild.RightChild.LeftChild.Parent.NodeKey);
    }

    @Test
    void testAddKeyValue_ToRight() {
        assertEquals(8, bTree.Count());
        assertNull(node0.LeftChild.LeftChild.RightChild);
        assertTrue(bTree.AddKeyValue(3, 3));
        assertEquals(9, bTree.Count());
        assertEquals(3, node0.LeftChild.LeftChild.RightChild.NodeKey);
        assertEquals(2, node0.LeftChild.LeftChild.RightChild.Parent.NodeKey);
    }

    @Test
    void testAddKeyValue_Exists() {
        assertEquals(8, bTree.Count());
        assertFalse(bTree.AddKeyValue(14, 14));
        assertEquals(8, bTree.Count());
    }

    @Test
    void testAddKeyValue_EmptyTree() {
        BST<Integer> tree = new BST<>(null);
        assertTrue(tree.AddKeyValue(8, 8));
        assertEquals(8, tree.Root.NodeKey);
    }

    @Test
    void testFinMinMax_EmptyTree() {
        BST<Integer> tree = new BST<>(null);
        assertNull(tree.FinMinMax(null, false));
    }

    @Test
    void testFinMinMax_MinFromRoot() {
        bTree.AddKeyValue(3, 3);
        bTree.AddKeyValue(5, 5);
        bTree.AddKeyValue(16, 16);
        assertEquals(1, bTree.FinMinMax(node0, false).NodeKey);
    }

    @Test
    void testFinMinMax_MinFromNode() {
        bTree.AddKeyValue(3, 3);
        bTree.AddKeyValue(5, 5);
        bTree.AddKeyValue(16, 16);
        assertEquals(10, bTree.FinMinMax(bTree.FindNodeByKey(12).Node, false).NodeKey);
    }

    @Test
    void testFinMinMax_MaxFromRoot() {
        bTree.AddKeyValue(3, 3);
        bTree.AddKeyValue(5, 5);
        bTree.AddKeyValue(16, 16);
        assertEquals(16, bTree.FinMinMax(node0, true).NodeKey);
    }

    @Test
    void testFinMinMax_MaxFromNode() {
        bTree.AddKeyValue(3, 3);
        bTree.AddKeyValue(5, 5);
        bTree.AddKeyValue(16, 16);
        assertEquals(6, bTree.FinMinMax(bTree.FindNodeByKey(4).Node, true).NodeKey);
    }

    @Test
    void testFinMinMax_FromLeaf() {
        bTree.AddKeyValue(3, 3);
        bTree.AddKeyValue(5, 5);
        bTree.AddKeyValue(16, 16);
        assertEquals(16, bTree.FinMinMax(bTree.FindNodeByKey(16).Node, false).NodeKey);
        assertEquals(16, bTree.FinMinMax(bTree.FindNodeByKey(16).Node, true).NodeKey);
    }

    @Test
    void testDeleteNodeByKey_EmptyTree() {
        BST<Integer> tree = new BST<>(null);
        assertFalse(tree.DeleteNodeByKey(8));
    }

    @Test
    void testDeleteNodeByKey_OnlyRootTree() {
        BST<Integer> tree = new BST<>(new BSTNode<>(8, 8, null));
        assertTrue(tree.DeleteNodeByKey(8));
        assertNull(tree.Root);
    }

    @Test
    void testDeleteNodeByKey() {
        bTree.AddKeyValue(7, 7);
        bTree.AddKeyValue(13, 13);
        bTree.AddKeyValue(16, 16);
        assertEquals(4, node0.LeftChild.NodeKey);
        assertEquals(11, bTree.Count());
        assertTrue(bTree.FindNodeByKey(4).NodeHasKey);
        assertTrue(bTree.DeleteNodeByKey(4));
        assertEquals(10, bTree.Count());
        assertFalse(bTree.FindNodeByKey(4).NodeHasKey);
        assertEquals(6, node0.LeftChild.NodeKey);
        assertEquals(6, bTree.FindNodeByKey(2).Node.Parent.NodeKey);
        assertEquals(6, bTree.FindNodeByKey(7).Node.Parent.NodeKey);
        assertEquals(8, bTree.FindNodeByKey(6).Node.Parent.NodeKey);
        assertEquals(2, bTree.FindNodeByKey(6).Node.LeftChild.NodeKey);
        assertEquals(7, bTree.FindNodeByKey(6).Node.RightChild.NodeKey);

        assertEquals(12, node0.RightChild.NodeKey);
        assertTrue(bTree.DeleteNodeByKey(12));
        assertEquals(9, bTree.Count());
        assertEquals(13, node0.RightChild.NodeKey);
        assertEquals(13, bTree.FindNodeByKey(10).Node.Parent.NodeKey);
        assertEquals(13, bTree.FindNodeByKey(14).Node.Parent.NodeKey);
        assertEquals(8, bTree.FindNodeByKey(13).Node.Parent.NodeKey);

        assertFalse(bTree.DeleteNodeByKey(3));
        assertEquals(9, bTree.Count());

        assertTrue(bTree.FindNodeByKey(1).NodeHasKey);
        assertTrue(bTree.DeleteNodeByKey(1));
        assertFalse(bTree.FindNodeByKey(1).NodeHasKey);
        assertEquals(8, bTree.Count());
        assertNull(bTree.FindNodeByKey(2).Node.LeftChild);

        assertTrue(bTree.FindNodeByKey(7).NodeHasKey);
        assertTrue(bTree.DeleteNodeByKey(7));
        assertFalse(bTree.FindNodeByKey(7).NodeHasKey);
        assertEquals(7, bTree.Count());
        assertNull(bTree.FindNodeByKey(6).Node.RightChild);


        assertTrue(bTree.DeleteNodeByKey(8));
        assertNull(bTree.Root);
    }

    @Test
    void testDeleteNodeByKey_OnlyRights() {
        bTree.AddKeyValue(16, 16);
        assertEquals(9, bTree.Count());
        assertTrue(bTree.DeleteNodeByKey(12));
        assertEquals(8, bTree.Count());
        assertEquals(14, bTree.Root.RightChild.NodeKey);
        assertEquals(10, bTree.Root.RightChild.LeftChild.NodeKey);
        assertEquals(14, bTree.FindNodeByKey(10).Node.Parent.NodeKey);
        assertEquals(16, bTree.Root.RightChild.RightChild.NodeKey);
        assertEquals(14, bTree.FindNodeByKey(16).Node.Parent.NodeKey);
    }

    @Test
    void testDeleteNodeByKey_OnlyLeftOrRight() {
        bTree.AddKeyValue(7, 7);
        assertEquals(9, bTree.Count());
        assertTrue(bTree.DeleteNodeByKey(2));
        assertEquals(8, bTree.Count());
        assertEquals(1, bTree.FindNodeByKey(4).Node.LeftChild.NodeKey);
        assertEquals(4, bTree.FindNodeByKey(1).Node.Parent.NodeKey);

        assertTrue(bTree.DeleteNodeByKey(6));
        assertEquals(7, bTree.Count());
        assertEquals(7, bTree.FindNodeByKey(4).Node.RightChild.NodeKey);
        assertEquals(4, bTree.FindNodeByKey(1).Node.Parent.NodeKey);
    }

    @Test
    void count() {
        assertEquals(8, bTree.Count());
        bTree.AddKeyValue(3, 3);
        assertEquals(9, bTree.Count());
        assertEquals(0, new BST<Integer>(null).Count());
        BSTNode<Integer> node = new BSTNode<>(0, 0, null);
        BST<Integer> smallTree = new BST<>(node);
        assertEquals(1, smallTree.Count());
    }

    @Test
    void testIsEqual_Nulls() {
        BST<Integer> tree1 = new BST<>(null);
        BST<Integer> tree2 = new BST<>(null);
        assertTrue(tree1.isEqual(tree2));
    }

    @Test
    void testIsEqual_NullsNegative() {
        BST<Integer> tree1 = new BST<>(null);
        BST<Integer> tree2 = new BST<>(new BSTNode<>(10, 100, null));
        assertFalse(tree1.isEqual(tree2));
        assertFalse(tree2.isEqual(tree1));
    }

    @Test
    void testIsEqual_OneNodePositive() {
        BST<Integer> tree1 = new BST<>(new BSTNode<>(10, 100, null));
        BST<Integer> tree2 = new BST<>(new BSTNode<>(10, 100, null));
        assertTrue(tree1.isEqual(tree2));
    }

    @Test
    void testIsEqual_OneNodeNegative() {
        BST<Integer> tree1 = new BST<>(new BSTNode<>(10, 100, null));
        BST<Integer> tree2 = new BST<>(new BSTNode<>(10, 110, null));
        BST<Integer> tree3 = new BST<>(new BSTNode<>(20, 100, null));
        assertFalse(tree1.isEqual(tree2));
        assertFalse(tree1.isEqual(tree3));
    }

    @Test
    void testIsEqual_BIgTrees() {
        BST<Integer> tree = new BST<>(new BSTNode<>(8, 8, null));
        tree.AddKeyValue(4, 4);
        tree.AddKeyValue(12, 12);
        tree.AddKeyValue(2, 2);
        tree.AddKeyValue(6, 6);
        tree.AddKeyValue(10, 10);
        tree.AddKeyValue(14, 14);
        tree.AddKeyValue(1, 1);
        assertTrue(bTree.isEqual(tree));
        tree.AddKeyValue(16, 16);
        assertFalse(bTree.isEqual(tree));
    }

    @Test
    void testAllLeavesWithPathLength() {
        ArrayList<BSTNode<Integer>> leaves = bTree.allLeavesWithPathLength(3);
        assertEquals(1, leaves.size());
        bTree.AddKeyValue(13, 13);
        bTree.AddKeyValue(16, 16);
        leaves = bTree.allLeavesWithPathLength(3);
        assertEquals(3, leaves.size());
    }

    @Test
    void testAllPathsToLeavesWithLength_EmptyTree() {
        BST<Integer> tree = new BST<>(null);
        ArrayList<ArrayList<BSTNode<Integer>>> paths = tree.allPathsToLeavesWithLength(3);
        assertEquals(0, paths.size());
    }

    @Test
    void testAllPathsToLeavesWithLength_ExtraLength() {
        ArrayList<ArrayList<BSTNode<Integer>>> paths = bTree.allPathsToLeavesWithLength(10);
        assertEquals(0, paths.size());
    }

    @Test
    void testAllPathsToLeavesWithLength() {
        ArrayList<ArrayList<BSTNode<Integer>>> paths = bTree.allPathsToLeavesWithLength(3);
        assertEquals(1, paths.size());
        assertEquals(4, paths.getFirst().size()); //4 - т.к. путь вместе с листом на конце
        assertEquals(8, paths.getFirst().getFirst().NodeKey);
        assertEquals(4, paths.getFirst().get(1).NodeKey);
        assertEquals(2, paths.getFirst().get(2).NodeKey);
        assertEquals(1, paths.getFirst().get(3).NodeKey);

        bTree.AddKeyValue(13, 13);
        bTree.AddKeyValue(16, 16);
        paths = bTree.allPathsToLeavesWithLength(3);
        assertEquals(3, paths.size());
        assertEquals(4, paths.getFirst().size());
        assertEquals(4, paths.get(1).size());
        assertEquals(4, paths.get(2).size());

        assertEquals(8, paths.getFirst().getFirst().NodeKey);
        assertEquals(4, paths.getFirst().get(1).NodeKey);
        assertEquals(2, paths.getFirst().get(2).NodeKey);
        assertEquals(1, paths.getFirst().get(3).NodeKey);

        assertEquals(8, paths.get(1).getFirst().NodeKey);
        assertEquals(12, paths.get(1).get(1).NodeKey);
        assertEquals(14, paths.get(1).get(2).NodeKey);
        assertEquals(13, paths.get(1).get(3).NodeKey);

        assertEquals(8, paths.get(2).getFirst().NodeKey);
        assertEquals(12, paths.get(2).get(1).NodeKey);
        assertEquals(14, paths.get(2).get(2).NodeKey);
        assertEquals(16, paths.get(2).get(3).NodeKey);
    }

    @Test
    void pathsWithMaxSumOfValues_EmptyTree() {
        BST<Integer> tree = new BST<>(null);
        ArrayList<ArrayList<BSTNode<Integer>>> leaves = tree.pathsWithMaxSumOfValues();
        assertEquals(0, leaves.size());
    }

    @Test
    void pathsWithMaxSumOfValues() {
        ArrayList<ArrayList<BSTNode<Integer>>> leaves = bTree.pathsWithMaxSumOfValues();
        assertEquals(2, leaves.size());
        assertEquals(3, leaves.getFirst().size());

        assertEquals(8, leaves.getFirst().getFirst().NodeKey);
        assertEquals(12, leaves.getFirst().get(1).NodeKey);
        assertEquals(14, leaves.getFirst().get(2).NodeKey);

        assertEquals(3, leaves.get(1).size());

        assertEquals(8, leaves.get(1).get(0).NodeKey);
        assertEquals(12, leaves.get(1).get(1).NodeKey);
        assertEquals(10, leaves.get(1).get(2).NodeKey);
    }

    @Test
    void testIsSymmetric_NullRootAndNullChildrenTrees() {
        BST<Integer> tree = new BST<>(null);
        assertFalse(tree.isSymmetric());
        BSTNode<Integer> node = new BSTNode<>(8, 8, null);
        tree = new BST<>(node);
        assertTrue(tree.isSymmetric());
        tree.AddKeyValue(4, 4);
        assertFalse(tree.isSymmetric());
        tree.AddKeyValue(12, 12);
        assertTrue(tree.isSymmetric());
        tree.DeleteNodeByKey(4);
        assertFalse(tree.isSymmetric());
    }

    @Test
    void testIsSymmetric_bigTree() {
        assertFalse(bTree.isSymmetric());
        bTree.AddKeyValue(16, 16);
        assertTrue(bTree.isSymmetric());
    }
}