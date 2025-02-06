package org.skillsmart.asd2real.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {

    @Test
    void testGenerateTree_EmptyOrOneRootTree() {
        int[] arr = new int[0];
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(arr);
        assertNull(tree.Root);
        arr = new int[]{8};
        tree = new BalancedBST();
        tree.GenerateTree(arr);
        assertEquals(8, tree.Root.NodeKey);
        assertNull(tree.Root.Parent);
        assertNull(tree.Root.LeftChild);
        assertNull(tree.Root.RightChild);
        assertEquals(0, tree.Root.Level);
    }

    @Test
    void testGenerateTree() {
        BalancedBST tree = new BalancedBST();
        int[] arr = {12, 8, 4};
        tree.GenerateTree(arr);
        assertEquals(8, tree.Root.NodeKey);
        assertEquals(4, tree.Root.LeftChild.NodeKey);
        assertEquals(12, tree.Root.RightChild.NodeKey);
        assertNull(tree.Root.LeftChild.LeftChild);
        assertNull(tree.Root.LeftChild.RightChild);
        assertNull(tree.Root.RightChild.LeftChild);
        assertNull(tree.Root.RightChild.RightChild);
        assertEquals(0, tree.Root.Level);
        assertEquals(1, tree.Root.LeftChild.Level);
        assertEquals(1, tree.Root.RightChild.Level);

        arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        tree = new BalancedBST();
        tree.GenerateTree(arr);
        assertEquals(7, tree.Root.NodeKey);
        assertEquals(3, tree.Root.LeftChild.NodeKey);
        assertEquals(11, tree.Root.RightChild.NodeKey);
        assertEquals(0, tree.Root.Level);
        assertEquals(1, tree.Root.LeftChild.Level);
        assertEquals(1, tree.Root.RightChild.Level);

        assertEquals(1, tree.Root.LeftChild.LeftChild.NodeKey);
        assertEquals(8, tree.Root.RightChild.LeftChild.NodeKey);
        assertEquals(5, tree.Root.LeftChild.RightChild.NodeKey);
        assertEquals(14, tree.Root.RightChild.RightChild.NodeKey);
        assertEquals(2, tree.Root.LeftChild.LeftChild.Level);
        assertEquals(2, tree.Root.RightChild.LeftChild.Level);
        assertEquals(2, tree.Root.LeftChild.RightChild.Level);
        assertEquals(2, tree.Root.RightChild.RightChild.Level);

        assertNull(tree.Root.LeftChild.LeftChild.LeftChild);
        assertNull(tree.Root.RightChild.LeftChild.LeftChild);
        assertEquals(2, tree.Root.LeftChild.LeftChild.RightChild.NodeKey);
        assertEquals(9, tree.Root.RightChild.LeftChild.RightChild.NodeKey);
        assertEquals(4, tree.Root.LeftChild.RightChild.LeftChild.NodeKey);
        assertEquals(12, tree.Root.RightChild.RightChild.LeftChild.NodeKey);
        assertEquals(6, tree.Root.LeftChild.RightChild.RightChild.NodeKey);
        assertEquals(15, tree.Root.RightChild.RightChild.RightChild.NodeKey);

        assertEquals(3, tree.Root.LeftChild.LeftChild.RightChild.Level);
        assertEquals(3, tree.Root.RightChild.LeftChild.RightChild.Level);
        assertEquals(3, tree.Root.LeftChild.RightChild.LeftChild.Level);
        assertEquals(3, tree.Root.RightChild.RightChild.LeftChild.Level);
        assertEquals(3, tree.Root.LeftChild.RightChild.RightChild.Level);
        assertEquals(3, tree.Root.RightChild.RightChild.RightChild.Level);
    }

    @Test
    void testIsCorrect_EmptyOrOneRootTree() {
        BalancedBST tree = new BalancedBST();
        assertTrue(tree.isCorrect(tree.Root));
        int[] arr = new int[]{8};
        tree.GenerateTree(arr);
        assertTrue(tree.isCorrect(tree.Root));
    }

    @Test
    void testIsCorrect_Positive() {
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(arr);
        assertTrue(tree.isCorrect(tree.Root));
        assertTrue(tree.isCorrect(tree.Root.LeftChild));
        assertTrue(tree.isCorrect(tree.Root.RightChild));
    }

    @Test
    void testIsCorrect_Negative() {
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(arr);
        BSTNode node1 = tree.Root.RightChild.RightChild.RightChild; //15
        BSTNode node2 = tree.Root.LeftChild.LeftChild.RightChild; //2
        BSTNode tmpParent = node1.Parent;
        node1.Parent.RightChild = node2;
        node1.Parent = node2.Parent;
        node2.Parent.RightChild = node1;
        node2.Parent = tmpParent;
        assertFalse(tree.isCorrect(tree.Root));
        assertFalse(tree.isCorrect(tree.Root.RightChild));
        assertTrue(tree.isCorrect(tree.Root.LeftChild));
    }

    @Test
    void testIsBalanced_EmptyOrOneRootTree() {
        BalancedBST tree = new BalancedBST();
        assertTrue(tree.IsBalanced(null));
        int[] arr = new int[]{8};
        tree.GenerateTree(arr);
        assertTrue(tree.IsBalanced(tree.Root));
    }

    @Test
    void testIsBalanced_PositiveWithEqLevels() {
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(arr);
        assertTrue(tree.IsBalanced(tree.Root));
        assertTrue(tree.IsBalanced(tree.Root.LeftChild));
        assertTrue(tree.IsBalanced(tree.Root.RightChild));
    }

    @Test
    void testIsBalanced_PositiveWithOneLevelDiff() {
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(arr);
        tree.Root.RightChild.LeftChild.LeftChild = null;
        tree.Root.RightChild.LeftChild.RightChild = null;
        assertTrue(tree.IsBalanced(tree.Root));
        assertTrue(tree.IsBalanced(tree.Root.RightChild));
    }

    @Test
    void testIsBalanced_Negative() {
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(arr);
        tree.Root.RightChild.LeftChild = null;
        tree.Root.RightChild.RightChild = null;
        assertFalse(tree.IsBalanced(tree.Root));

        arr = new int[63];
        for (int i = 0; i < 63; i++) {
            arr[i] = i + 1;
        }
        tree = new BalancedBST();
        tree.GenerateTree(arr);

        tree.Root.LeftChild.LeftChild.RightChild.LeftChild = null;
        tree.Root.LeftChild.LeftChild.RightChild.RightChild = null;
        assertFalse(tree.IsBalanced(tree.Root));
        assertFalse(tree.IsBalanced(tree.Root.LeftChild.LeftChild));
        assertTrue(tree.IsBalanced(tree.Root.LeftChild.LeftChild.LeftChild));
    }
}