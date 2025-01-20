package org.skillsmart.asd3.lesson1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    public SimpleTree<Integer> sTree;
    public SimpleTreeNode<Integer> tNode0;
    public SimpleTreeNode<Integer> tNode1;
    public SimpleTreeNode<Integer> tNode2;
    public SimpleTreeNode<Integer> tNode3;
    public SimpleTreeNode<Integer> tNode4;
    public SimpleTreeNode<Integer> tNode5;
    public SimpleTreeNode<Integer> tNode6;

    @BeforeEach
    void setUp() {
        tNode0 = new SimpleTreeNode<>(10, null);
        sTree = new SimpleTree<>(tNode0);
        tNode1 = new SimpleTreeNode<>(11, tNode0);
        tNode2 = new SimpleTreeNode<>(12, tNode1);
        tNode3 = new SimpleTreeNode<>(13, tNode1);
        tNode4 = new SimpleTreeNode<>(14, tNode0);
        tNode5 = new SimpleTreeNode<>(15, tNode4);
        tNode6 = new SimpleTreeNode<>(16, tNode3);
        sTree.AddChild(tNode0, tNode1);
        sTree.AddChild(tNode0, tNode4);
        sTree.AddChild(tNode1, tNode2);
        sTree.AddChild(tNode1, tNode3);
        sTree.AddChild(tNode4, tNode5);
        sTree.AddChild(tNode3, tNode6);
    }

    @Test
    void addChild() {
        SimpleTreeNode<Integer> treeNode = new SimpleTreeNode<>(100, null);
        SimpleTree<Integer> tree = new SimpleTree<>(treeNode); //корень
        SimpleTreeNode<Integer> treeNode2 = new SimpleTreeNode<>(102, null);
        SimpleTreeNode<Integer> treeNode3 = new SimpleTreeNode<>(103, null);
        SimpleTreeNode<Integer> treeNode4 = new SimpleTreeNode<>(104, null);
        tree.AddChild(treeNode, treeNode2);
        tree.AddChild(treeNode, treeNode3);
        tree.AddChild(treeNode3, treeNode4);
        assertEquals(treeNode2, treeNode.Children.getFirst());
        assertEquals(treeNode, treeNode2.Parent);
        assertEquals(treeNode, treeNode3.Parent);
        assertEquals(treeNode3, treeNode.Children.get(1));
        assertEquals(treeNode4, treeNode3.Children.getFirst());
        assertEquals(treeNode3, treeNode4.Parent);
        assertNull(treeNode2.Children);
        assertNull(treeNode4.Children);
    }

    @Test
    void deleteNode() {
        sTree.DeleteNode(tNode2);
        assertEquals(tNode1.Children.getFirst(), tNode3);
        assertEquals(1, tNode1.Children.size());
        assertNull(tNode2.Parent);
        sTree.DeleteNode(tNode3);
        assertNull(tNode2.Children);
        assertNull(tNode3.Parent);
        //Вопрос, надо ли "отвязывать" tNode5 от tNode3? - Ответ: скорее всего нет, т.к. потом заиспользуем
        // это метод в методе переноса поддерева
    }

    @Test
    void getAllNodes() {
        SimpleTree<Integer> tree = new SimpleTree<>(null); //корень
        assertNull(tree.GetAllNodes());
        List<SimpleTreeNode<Integer>> allNodes = sTree.GetAllNodes();
        assertEquals(7, allNodes.size());
        assertTrue(allNodes.contains(tNode0));
        assertTrue(allNodes.contains(tNode1));
        assertTrue(allNodes.contains(tNode2));
        assertTrue(allNodes.contains(tNode3));
        assertTrue(allNodes.contains(tNode4));
        assertTrue(allNodes.contains(tNode5));
        assertTrue(allNodes.contains(tNode6));
    }

    @Test
    void findNodesByValue() {
        assertEquals(tNode0, sTree.FindNodesByValue(10).getFirst());
        assertEquals(tNode5, sTree.FindNodesByValue(15).getFirst());
        assertEquals(1, sTree.FindNodesByValue(15).size());
        SimpleTreeNode<Integer> tNode7 = new SimpleTreeNode<>(11, tNode4);
        SimpleTreeNode<Integer> tNode8 = new SimpleTreeNode<>(11, tNode2);
        SimpleTreeNode<Integer> tNode9 = new SimpleTreeNode<>(11, tNode6);
        sTree.AddChild(tNode4,tNode7);
        sTree.AddChild(tNode2,tNode8);
        sTree.AddChild(tNode6,tNode9);
        assertEquals(10, sTree.GetAllNodes().size());
        assertEquals(4, sTree.FindNodesByValue(11).size());
        assertTrue(sTree.FindNodesByValue(11).contains(tNode1));
        assertTrue(sTree.FindNodesByValue(11).contains(tNode7));
        assertTrue(sTree.FindNodesByValue(11).contains(tNode8));
        assertTrue(sTree.FindNodesByValue(11).contains(tNode9));
        assertEquals(0, sTree.FindNodesByValue(55).size());
    }

    @Test
    void moveNode() {
        sTree.MoveNode(tNode1, tNode4);
        assertEquals(7, sTree.GetAllNodes().size());
        assertEquals(1, tNode0.Children.size());
        assertEquals(2, tNode4.Children.size());
        assertTrue(tNode4.Children.contains(tNode1));
        assertTrue(tNode4.Children.contains(tNode5));
        assertEquals(2, tNode1.Children.size());
        assertEquals(1, tNode3.Children.size());
        assertEquals(tNode4, tNode1.Parent);
        sTree.MoveNode(tNode3, tNode0);
        assertEquals(2, tNode0.Children.size());
        assertTrue(tNode0.Children.contains(tNode3));
        assertEquals(tNode0, tNode3.Parent);
        assertEquals(1, tNode3.Children.size());
        assertEquals(tNode3, tNode6.Parent);
        assertEquals(1, tNode1.Children.size());
        assertEquals(7, sTree.GetAllNodes().size());
    }

    @Test
    void count() {
        assertEquals(7, sTree.Count());
        sTree.DeleteNode(tNode6);
        assertEquals(6, sTree.Count());
        sTree.DeleteNode(tNode1);
        assertEquals(3, sTree.Count());

        SimpleTree<Integer> tree = new SimpleTree<>(null);
        assertEquals(0, tree.Count());
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(111, null);
        tree = new SimpleTree<>(node);
        assertEquals(1, tree.Count());
    }

    @Test
    void leafCount() {
        assertEquals(3, sTree.LeafCount());
        sTree.DeleteNode(tNode5);
        assertEquals(3, sTree.LeafCount());
        sTree.DeleteNode(tNode2);
        assertEquals(2, sTree.LeafCount());
        SimpleTree<Integer> tree = new SimpleTree<>(null);
        assertEquals(0, tree.LeafCount());
        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(111, null);
        tree = new SimpleTree<>(node);
        assertEquals(1, tree.LeafCount());
    }
}