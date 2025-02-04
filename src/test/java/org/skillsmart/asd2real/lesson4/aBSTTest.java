package org.skillsmart.asd2real.lesson4;

import org.junit.jupiter.api.Test;
import org.skillsmart.asd2real.lesson5.AlgorithmsDataStructures2;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class aBSTTest {

    @Test
    void testCreateArray() {
        //1, 3, 7, 15, 31
        aBST tree = new aBST(0);
        assertEquals(1, tree.Tree.length);

        tree = new aBST(1);
        assertEquals(3, tree.Tree.length);

        tree = new aBST(2);
        assertEquals(7, tree.Tree.length);

        tree = new aBST(3);
        assertEquals(15, tree.Tree.length);

        tree = new aBST(4);
        assertEquals(31, tree.Tree.length);

        tree = new aBST(5);
        assertEquals(63, tree.Tree.length);
    }

    @Test
    void testFindKeyIndex_EmptyArr() {
        aBST tree = new aBST(0);
        assertEquals(0, tree.FindKeyIndex(8));
        tree = new aBST(3);
        assertEquals(0, tree.FindKeyIndex(8));
    }

    @Test
    void testFindKeyIndex_BigArrPositive() {
        aBST tree = new aBST(3);
        tree.AddKey(8);
        tree.AddKey(4);
        tree.AddKey(12);
        tree.AddKey(2);
        tree.AddKey(6);
        tree.AddKey(10);
        tree.AddKey(14);
        tree.AddKey(1);
        tree.AddKey(16);
        assertEquals(-8, tree.FindKeyIndex(3));
        assertEquals(-12, tree.FindKeyIndex(11));
        assertEquals(0, tree.FindKeyIndex(8));
        assertEquals(4, tree.FindKeyIndex(6));
        assertEquals(5, tree.FindKeyIndex(10));
        assertNull(tree.FindKeyIndex(17));
    }

    @Test
    void testFindKeyIndex_BigArrNegative() {
        aBST tree = new aBST(2);
        tree.AddKey(8);
        tree.AddKey(4);
        tree.AddKey(12);
        tree.AddKey(2);
        tree.AddKey(6);
        tree.AddKey(10);
        tree.AddKey(14);
        assertNull(tree.FindKeyIndex(16));
    }

    @Test
    void addKey() {
        aBST tree = new aBST(2);
        assertEquals(0, tree.AddKey(8));
        assertEquals(1, tree.AddKey(4));
        assertEquals(2, tree.AddKey(12));
        assertEquals(6, tree.AddKey(14));
        assertNull(tree.Tree[5]);
        assertEquals(4, tree.AddKey(6));
        assertEquals(3, tree.AddKey(2));
        assertEquals(5, tree.AddKey(10));
        assertEquals(-1, tree.AddKey(18));
        assertEquals(6, tree.AddKey(14));

        assertEquals(8, tree.Tree[0]);
        assertEquals(4, tree.Tree[1]);
        assertEquals(12, tree.Tree[2]);
        assertEquals(2, tree.Tree[3]);
        assertEquals(6, tree.Tree[4]);
        assertEquals(10, tree.Tree[5]);
        assertEquals(14, tree.Tree[6]);
    }

    @Test
    void testGetLCA_EmptyAndOneRootTree() {
        aBST tree = new aBST(0);
        assertEquals(-1, tree.getLCA(8, 10));
        tree.AddKey(8);
        assertEquals(-1, tree.getLCA(8, 10));
        assertEquals(0, tree.getLCA(8, 8));
    }

    @Test
    void testGetLCA_WithRoot() {
        aBST tree = new aBST(2);
        assertEquals(0, tree.AddKey(8));
        assertEquals(1, tree.AddKey(4));
        assertEquals(2, tree.AddKey(12));
        assertEquals(6, tree.AddKey(14));
        assertEquals(4, tree.AddKey(6));
        assertEquals(3, tree.AddKey(2));
        assertEquals(5, tree.AddKey(10));
        assertEquals(6, tree.AddKey(14));

        assertEquals(0, tree.getLCA(14, 8));
        assertEquals(0, tree.getLCA(8, 4));
        assertEquals(0, tree.getLCA(6, 14));
    }

    @Test
    void testGetLCA() {
        aBST tree = new aBST(5);
        String nodes = "32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, " +
                "42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, " +
                "43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63";
        String[] nodesList = nodes.split(", ");
        for (int i = 0; i < tree.Tree.length; i++) {
            tree.Tree[i] = Integer.parseInt(nodesList[i]);
        }
        assertEquals(32, tree.Tree[0]);
        assertEquals(0, tree.getLCA(31, 63));
        assertEquals(2, tree.getLCA(44, 52));
        assertEquals(11, tree.getLCA(33, 38));
        assertEquals(15, tree.getLCA(1, 3));
        assertEquals(15, tree.getLCA(1, 2));
        assertEquals(6, tree.getLCA(56, 63));
    }

    @Test
    void testBFS_EmptyAndOneRootArr() {
        aBST tree = new aBST(0);
        assertTrue(tree.WideAllNodes().isEmpty());
        tree.AddKey(8);
        ArrayList<Integer> wide = tree.WideAllNodes();
        assertEquals(1, wide.size());
        assertEquals(8, wide.getFirst());

    }

    @Test
    void testBFS() {
        aBST tree = new aBST(5);
        String nodes = "32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, " +
                "42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, " +
                "43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63";
        String[] nodesList = nodes.split(", ");
        for (int i = 0; i < tree.Tree.length; i++) {
            tree.Tree[i] = Integer.parseInt(nodesList[i]);
        }

        ArrayList<Integer> wide = tree.WideAllNodes();
        for (int i = 0; i < wide.size(); i++) {
            assertEquals(Integer.parseInt(nodesList[i]), wide.get(i));
        }

        tree = new aBST(3);
        assertEquals(0, tree.AddKey(8));
        assertEquals(1, tree.AddKey(4));
        assertEquals(2, tree.AddKey(12));
        assertEquals(6, tree.AddKey(14));
        assertEquals(3, tree.AddKey(2));
        assertEquals(5, tree.AddKey(10));
        assertEquals(7, tree.AddKey(1));
        assertEquals(14, tree.AddKey(16));

        wide = tree.WideAllNodes();
        assertEquals(8, wide.size());
        assertEquals(8, wide.get(0));
        assertEquals(4, wide.get(1));
        assertEquals(12, wide.get(2));
        assertEquals(2, wide.get(3));
        assertEquals(10, wide.get(4));
        assertEquals(14, wide.get(5));
        assertEquals(1, wide.get(6));
        assertEquals(16, wide.get(7));
    }

    @Test
    void testDeleteKey_EmptyOrOneRootTree() {
        aBST tree = new aBST(0);
        assertFalse(tree.deleteKey(5));
        tree.AddKey(8);
        assertFalse(tree.deleteKey(5));
        assertTrue(tree.deleteKey(8));
        assertNull(tree.Tree[0]);
    }

    @Test
    void testDeleteKey() {
        aBST tree = new aBST(3);
        int[] arr = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 7, 5, 9, 11, 15, 13};
        int[] bbst = AlgorithmsDataStructures2.GenerateBBSTArray(arr);
        for (int i = 0; i < arr.length; i++) {
            tree.Tree[i] = bbst[i];
        }
        tree.deleteKey(6);
        tree.deleteKey(5);
        tree.deleteKey(7);
        tree.deleteKey(14);
        tree.deleteKey(7);
        tree.deleteKey(11);
        tree.deleteKey(15);

        tree.deleteKey(15);
        tree.deleteKey(4);
        tree.deleteKey(13);
        assertEquals(8, tree.Tree[0]);
        for (int i = 7; i < tree.Tree.length; i++) {
            assertNull(tree.Tree[i]);
        }
    }

    @Test
    void testRemoveKey_EmptyOrOneRootTree() {
        aBST tree = new aBST(0);
        assertFalse(tree.deleteKey(5));
        tree.AddKey(8);
        assertFalse(tree.removeKey(5));
        assertTrue(tree.removeKey(8));
        assertNull(tree.Tree[0]);
    }

    @Test
    void testRemoveKey() {
        aBST tree = new aBST(3);
        int[] arr = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 7, 5, 9, 11, 15, 13};
        int[] bbst = AlgorithmsDataStructures2.GenerateBBSTArray(arr);
        for (int i = 0; i < arr.length; i++) {
            tree.Tree[i] = bbst[i];
        }
        tree.removeKey(6);
        assertEquals(7, tree.Tree[4]);
        tree.removeKey(12);
        assertEquals(13, tree.Tree[2]);
    }
}