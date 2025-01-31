package org.skillsmart.asd2real.lesson3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void wideAllNodes_EmptyAndOnlyRootTree() {
        BST<Integer> tree = new BST<>(null);
        ArrayList<BSTNode> nodes = tree.WideAllNodes();
        assertEquals(0, nodes.size());
        tree.AddKeyValue(8, 8);
        nodes = tree.WideAllNodes();
        assertEquals(1, nodes.size());
        assertEquals(8, nodes.get(0).NodeKey);
    }

    @Test
    void wideAllNodes() {
        //8, 4, 12, 2, 6, 10, 14, 1, 15
        bTree.AddKeyValue(15, 15);
        ArrayList<BSTNode> nodes = bTree.WideAllNodes();
        assertEquals(9, nodes.size());
        assertEquals(8, nodes.get(0).NodeKey);
        assertEquals(4, nodes.get(1).NodeKey);
        assertEquals(12, nodes.get(2).NodeKey);
        assertEquals(2, nodes.get(3).NodeKey);
        assertEquals(6, nodes.get(4).NodeKey);
        assertEquals(10, nodes.get(5).NodeKey);
        assertEquals(14, nodes.get(6).NodeKey);
        assertEquals(1, nodes.get(7).NodeKey);
        assertEquals(15, nodes.get(8).NodeKey);
    }

    @Test
    void deepAllNodes_EmptyAndOnlyRootTree() {
        BST<Integer> tree = new BST<>(null);
        ArrayList<BSTNode> nodes = tree.DeepAllNodes(0);
        assertEquals(0, nodes.size());
        nodes = tree.DeepAllNodes(1);
        assertEquals(0, nodes.size());
        nodes = tree.DeepAllNodes(2);
        assertEquals(0, nodes.size());

        tree.AddKeyValue(8, 8);
        nodes = tree.DeepAllNodes(0);
        assertEquals(1, nodes.size());
        assertEquals(8, nodes.getFirst().NodeKey);
        nodes = tree.DeepAllNodes(1);
        assertEquals(1, nodes.size());
        assertEquals(8, nodes.getFirst().NodeKey);
        nodes = tree.DeepAllNodes(2);
        assertEquals(1, nodes.size());
        assertEquals(8, nodes.getFirst().NodeKey);
    }

    @Test
    void deepAllNodes_InOrder() {
        //0 (in-order)
        ArrayList<BSTNode> nodes0 = bTree.DeepAllNodes(0);
        //1, 2, 4, 6, 8, 10, 12, 14
        assertEquals(1, nodes0.get(0).NodeKey);
        assertEquals(2, nodes0.get(1).NodeKey);
        assertEquals(4, nodes0.get(2).NodeKey);
        assertEquals(6, nodes0.get(3).NodeKey);
        assertEquals(8, nodes0.get(4).NodeKey);
        assertEquals(10, nodes0.get(5).NodeKey);
        assertEquals(12, nodes0.get(6).NodeKey);
        assertEquals(14, nodes0.get(7).NodeKey);
    }

    @Test
    void deepAllNodes_PostOrder() {
        //1 (post-order)
        ArrayList<BSTNode> nodes1 = bTree.DeepAllNodes(1);
        //1, 2, 6, 4, 10, 14, 12, 8
        assertEquals(1, nodes1.get(0).NodeKey);
        assertEquals(2, nodes1.get(1).NodeKey);
        assertEquals(6, nodes1.get(2).NodeKey);
        assertEquals(4, nodes1.get(3).NodeKey);
        assertEquals(10, nodes1.get(4).NodeKey);
        assertEquals(14, nodes1.get(5).NodeKey);
        assertEquals(12, nodes1.get(6).NodeKey);
        assertEquals(8, nodes1.get(7).NodeKey);
    }

    @Test
    void deepAllNodes_preOrder() {
        //2 (pre-order)
        ArrayList<BSTNode> nodes2 = bTree.DeepAllNodes(2);
        //8, 4, 2, 1, 6, 12, 10, 14
        assertEquals(8, nodes2.get(0).NodeKey);
        assertEquals(4, nodes2.get(1).NodeKey);
        assertEquals(2, nodes2.get(2).NodeKey);
        assertEquals(1, nodes2.get(3).NodeKey);
        assertEquals(6, nodes2.get(4).NodeKey);
        assertEquals(12, nodes2.get(5).NodeKey);
        assertEquals(10, nodes2.get(6).NodeKey);
        assertEquals(14, nodes2.get(7).NodeKey);
    }

    @Test
    void testInvert_EmptyAndOneRootTrees() {
        BST<Integer> tree = new BST<>(null);
        tree.invert();
        assertNull(tree.Root);

        tree = new BST<>(new BSTNode<>(8, 8, null));
        tree.invert();
        assertEquals(8, tree.Root.NodeKey);
    }

    @Test
    void testInvert() {
        BST<Integer> tree = new BST<>(new BSTNode<>(8, 8, null));
        tree.AddKeyValue(4, 4);
        tree.AddKeyValue(12, 12);
        tree.invert();
        assertEquals(8, tree.Root.NodeKey);
        assertEquals(12, tree.Root.LeftChild.NodeKey);
        assertEquals(4, tree.Root.RightChild.NodeKey);

        bTree.invert();
        assertEquals(8, bTree.Root.NodeKey);
        assertEquals(12, bTree.Root.LeftChild.NodeKey);
        assertEquals(4, bTree.Root.RightChild.NodeKey);
        assertEquals(6, bTree.Root.RightChild.LeftChild.NodeKey);
        assertEquals(2, bTree.Root.RightChild.RightChild.NodeKey);

        assertNull(bTree.Root.RightChild.RightChild.LeftChild);
        assertEquals(1, bTree.Root.RightChild.RightChild.RightChild.NodeKey);

        assertEquals(10, bTree.Root.LeftChild.RightChild.NodeKey);
        assertEquals(14, bTree.Root.LeftChild.LeftChild.NodeKey);
    }

    @Test
    void testGetLevelWithMaxSum_EmptyOrSingleRootTrees() {
        BST<Integer> tree = new BST<>(null);
        ArrayList<BSTNode> level = tree.getLevelWithMaxSum();
        assertNull(level);
        tree = new BST<>(new BSTNode<>(12, 12, null));
        level = tree.getLevelWithMaxSum();
        assertEquals(1, level.size());
        assertEquals(12, level.getFirst().NodeKey);
    }

    @Test
    void testGetLevelWithMaxSum() {
        ArrayList<BSTNode> level = bTree.getLevelWithMaxSum();
        assertEquals(4, level.size());
        assertEquals(2, level.getFirst().NodeKey);
        assertEquals(6, level.get(1).NodeKey);
        assertEquals(10, level.get(2).NodeKey);
        assertEquals(14, level.get(3).NodeKey);

        bTree.AddKeyValue(100, 100);
        level = bTree.getLevelWithMaxSum();
        assertEquals(2, level.size());
        assertEquals(1, level.getFirst().NodeKey);
        assertEquals(100, level.get(1).NodeKey);
    }

    @Test
    void testRestoreTree_NullAndOneRootTree() {
        BST<Integer> tree = new BST<>(null);
        tree.restoreTree(new int[0], new int[0]);
        assertNull(tree.Root);
        int[] preOrder = {1};
        int[] inOrder = {1};
        tree.restoreTree(preOrder, inOrder);
    }

    @Test
    void testRestoreTree_TwoLevelTree() {
        BST<Integer> tree = new BST<>(null);
        int[] preOrder = {1,2};
        int[] inOrder = {2,1};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(1, tree.Root.NodeKey);
        assertEquals(2, tree.Root.LeftChild.NodeKey);
        assertNull(tree.Root.RightChild);

        preOrder = new int[]{1,3};
        inOrder = new int[]{1,3};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(1, tree.Root.NodeKey);
        assertEquals(3, tree.Root.RightChild.NodeKey);
        assertNull(tree.Root.LeftChild);

        preOrder = new int[]{1,2,3};
        inOrder = new int[]{2,1,3};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(1, tree.Root.NodeKey);
        assertEquals(2, tree.Root.LeftChild.NodeKey);
        assertEquals(3, tree.Root.RightChild.NodeKey);

        preOrder = new int[]{1,2,3};
        inOrder = new int[]{3,2,1};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(1, tree.Root.NodeKey);
        assertNull(tree.Root.RightChild);
        assertEquals(2, tree.Root.LeftChild.NodeKey);
        assertNull(tree.Root.LeftChild.RightChild);
        assertEquals(3, tree.Root.LeftChild.LeftChild.NodeKey);
        assertNull(tree.Root.LeftChild.LeftChild.RightChild);

    }

    @Test
    void testRestoreTree_BigTree() {
        BST<Integer> tree = new BST<>(null);
        int[] preOrder = new int[]{1,2,3,4,8};
        int[] inOrder = new int[]{4,3,2,1,8};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(1, tree.Root.NodeKey);

        preOrder = new int[]{1,2,4,5,3,6,7};
        inOrder = new int[]{4,2,5,1,6,3,7};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(1, tree.Root.NodeKey);

        ArrayList<BSTNode> inOrd = tree.DeepAllNodes(0);
        for (int i = 0; i < inOrd.size(); i++) {
            assertEquals(inOrder[i], inOrd.get(i).NodeKey);
        }
        ArrayList<BSTNode> preOrd = tree.DeepAllNodes(2); //
        for (int i = 0; i < preOrd.size(); i++) {
            assertEquals(preOrder[i], preOrd.get(i).NodeKey);
        }

        preOrder = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        inOrder = new int[]{4, 3, 6, 5, 7, 2, 1, 8};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(1, tree.Root.NodeKey);

        inOrd = tree.DeepAllNodes(0);
        for (int i = 0; i < inOrd.size(); i++) {
            assertEquals(inOrder[i], inOrd.get(i).NodeKey);
        }
        preOrd = tree.DeepAllNodes(2); //
        for (int i = 0; i < preOrd.size(); i++) {
            assertEquals(preOrder[i], preOrd.get(i).NodeKey);
        }

        preOrder = new int[]{8, 4, 2, 1, 6, 12, 10, 14};
        inOrder = new int[]{1, 2, 4, 6, 8, 10, 12, 14};
        tree.restoreTree(preOrder, inOrder);
        assertEquals(8, tree.Root.NodeKey);

        inOrd = tree.DeepAllNodes(0);
        for (int i = 0; i < inOrd.size(); i++) {
            assertEquals(inOrder[i], inOrd.get(i).NodeKey);
        }
        preOrd = tree.DeepAllNodes(2); //
        for (int i = 0; i < preOrd.size(); i++) {
            assertEquals(preOrder[i], preOrd.get(i).NodeKey);
        }
    }
}