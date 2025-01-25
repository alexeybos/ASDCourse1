package org.skillsmart.asd3.lesson2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    public BST<Integer> bTree;
    public BSTNode<Integer> node0;
    public BSTNode<Integer> node1;
    public BSTNode<Integer> node2;
    public BSTNode<Integer> node3;
    public BSTNode<Integer> node4;
    public BSTNode<Integer> node5;
    public BSTNode<Integer> node6;
    public BSTNode<Integer> node7;

    @BeforeEach
    void setUp() {
        node0 = new BSTNode<>(8, 8, null);
        node1 = new BSTNode<>(4, 4, node0);
        node2 = new BSTNode<>(12, 12, node0);
        node3 = new BSTNode<>(2, 2, node1);
        node4 = new BSTNode<>(6, 6, node1);
        node5 = new BSTNode<>(10, 10, node2);
        node6 = new BSTNode<>(14, 14, node2);
        node7 = new BSTNode<>(1, 1, node3);
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
    void testIsEqual() {

    }
}