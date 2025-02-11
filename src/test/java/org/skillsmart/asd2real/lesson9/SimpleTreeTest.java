package org.skillsmart.asd2real.lesson9;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    @Test
    void testEvenTrees_Standard() {
        SimpleTreeNode<Integer> tNode1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> tNode2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> tNode3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> tNode4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> tNode5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> tNode6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> tNode7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> tNode8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> tNode9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> tNode10 = new SimpleTreeNode<>(10, null);
        SimpleTree<Integer> sTree = new SimpleTree<>(tNode1);
        sTree.AddChild(tNode1, tNode2);
        sTree.AddChild(tNode1, tNode3);
        sTree.AddChild(tNode1, tNode6);
        sTree.AddChild(tNode2, tNode5);
        sTree.AddChild(tNode2, tNode7);
        sTree.AddChild(tNode3, tNode4);
        sTree.AddChild(tNode6, tNode8);
        sTree.AddChild(tNode8, tNode9);
        sTree.AddChild(tNode8, tNode10);
        ArrayList<Integer> res = sTree.EvenTrees();
        assertEquals(4, res.size());
        assertEquals(1, res.get(0));
        assertEquals(3, res.get(1));
        assertEquals(1, res.get(2));
        assertEquals(6, res.get(3));
    }

    @Test
    void testEvenTrees_Small() {
        SimpleTreeNode<Integer> tNode1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> tNode2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> tNode3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> tNode4 = new SimpleTreeNode<>(4, null);
        SimpleTree<Integer> sTree = new SimpleTree<>(tNode1);
        sTree.AddChild(tNode1, tNode2);
        sTree.AddChild(tNode1, tNode3);
        sTree.AddChild(tNode3, tNode4);
        ArrayList<Integer> res = sTree.EvenTrees();
        assertEquals(2, res.size());
        assertEquals(1, res.get(0));
        assertEquals(3, res.get(1));
    }

    @Test
    void testEvenTrees_Many() {
        SimpleTreeNode<Integer> tNode1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> tNode2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> tNode3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> tNode4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> tNode5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> tNode6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> tNode7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> tNode8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> tNode9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> tNode10 = new SimpleTreeNode<>(10, null);
        SimpleTree<Integer> sTree = new SimpleTree<>(tNode1);
        sTree.AddChild(tNode1, tNode2);
        sTree.AddChild(tNode1, tNode3);
        sTree.AddChild(tNode1, tNode6);
        sTree.AddChild(tNode2, tNode5);
        sTree.AddChild(tNode2, tNode7);
        sTree.AddChild(tNode3, tNode4);
        sTree.AddChild(tNode6, tNode8);
        sTree.AddChild(tNode7, tNode9);
        sTree.AddChild(tNode8, tNode10);
        ArrayList<Integer> res = sTree.EvenTrees();
        assertEquals(8, res.size());
        assertEquals(2, res.get(0));
        assertEquals(7, res.get(1));
        assertEquals(1, res.get(2));
        assertEquals(2, res.get(3));
        assertEquals(1, res.get(4));
        assertEquals(3, res.get(5));
        assertEquals(6, res.get(6));
        assertEquals(8, res.get(7));
    }

    @Test
    void testBalanceBinaryTree_EmptyTree() {
        SimpleTree<Integer> tree = new SimpleTree<>(null);
        SimpleTree<Integer> tree2 = new SimpleTree<>(null);
        tree.balanceBinaryTree(tree2);
        assertTrue(tree2.IsBalanced(tree2.Root));
    }

    @Test
    void testBalanceBinaryTree() {
        SimpleTree<Integer> tree = new SimpleTree<>(null);
        SimpleTreeNode<Integer> tNode1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> tNode2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> tNode3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> tNode4 = new SimpleTreeNode<>(4, null);
        SimpleTree<Integer> tree2 = new SimpleTree<>(tNode1);
        tree2.AddChild(tNode1, tNode2);
        tree2.AddChild(tNode2, tNode3);
        tree2.AddChild(tNode3, tNode4);
        assertFalse(tree2.IsBalanced(tree2.Root));
        tree.balanceBinaryTree(tree2);
        assertTrue(tree2.IsBalanced(tree2.Root));
    }

    @Test
    void testGetEvenSubTreesCnt_EmptyOrSmallTree() {
        SimpleTree<Integer> sTree = new SimpleTree<>(null);
        SimpleTreeNode<Integer> tNode1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> tNode2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> tNode3 = new SimpleTreeNode<>(3, null);
        assertEquals(0, sTree.getEvenSubTreesCnt(null));
        sTree.Root = tNode1;
        assertEquals(0, sTree.getEvenSubTreesCnt(tNode1));
        sTree.AddChild(tNode1, tNode2);
        assertEquals(0, sTree.getEvenSubTreesCnt(tNode1));
        sTree.AddChild(tNode2, tNode3);
        assertEquals(1, sTree.getEvenSubTreesCnt(tNode1));
    }

    @Test
    void testGetEvenSubTreesCnt_Standard() {
        SimpleTreeNode<Integer> tNode1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> tNode2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> tNode3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> tNode4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> tNode5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> tNode6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> tNode7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> tNode8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> tNode9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> tNode10 = new SimpleTreeNode<>(10, null);
        SimpleTree<Integer> sTree = new SimpleTree<>(tNode1);
        sTree.AddChild(tNode1, tNode2);
        sTree.AddChild(tNode1, tNode3);
        sTree.AddChild(tNode1, tNode6);
        sTree.AddChild(tNode2, tNode5);
        sTree.AddChild(tNode2, tNode7);
        sTree.AddChild(tNode3, tNode4);
        sTree.AddChild(tNode6, tNode8);
        sTree.AddChild(tNode8, tNode9);
        sTree.AddChild(tNode8, tNode10);
        assertEquals(2, sTree.getEvenSubTreesCnt(tNode1));
    }

    @Test
    void testGetEvenSubTreesCnt() {
        SimpleTreeNode<Integer> tNode1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> tNode2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> tNode3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> tNode4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> tNode5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> tNode6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> tNode7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> tNode8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> tNode9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> tNode10 = new SimpleTreeNode<>(10, null);
        SimpleTree<Integer> sTree = new SimpleTree<>(tNode1);
        sTree.AddChild(tNode1, tNode2);
        sTree.AddChild(tNode1, tNode3);
        sTree.AddChild(tNode1, tNode6);
        sTree.AddChild(tNode2, tNode5);
        sTree.AddChild(tNode2, tNode7);
        sTree.AddChild(tNode3, tNode4);
        sTree.AddChild(tNode6, tNode8);
        sTree.AddChild(tNode7, tNode9);
        sTree.AddChild(tNode8, tNode10);
        assertEquals(4, sTree.getEvenSubTreesCnt(tNode1));
    }
}