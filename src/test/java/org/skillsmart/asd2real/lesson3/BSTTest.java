package org.skillsmart.asd2real.lesson3;

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
    void wideAllNodes() {
        //8, 4, 12, 2, 6, 10, 14, 1
        ArrayList<BSTNode> nodes = bTree.WideAllNodes();
        assertEquals(8, nodes.get(0).NodeKey);
        assertEquals(4, nodes.get(1).NodeKey);
        assertEquals(12, nodes.get(2).NodeKey);
        assertEquals(2, nodes.get(3).NodeKey);
        assertEquals(6, nodes.get(4).NodeKey);
        assertEquals(10, nodes.get(5).NodeKey);
        assertEquals(14, nodes.get(6).NodeKey);
        assertEquals(1, nodes.get(7).NodeKey);
    }

    @Test
    void deepAllNodes() {
        //0 (in-order), 1 (post-order) и 2 (pre-order)
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
}