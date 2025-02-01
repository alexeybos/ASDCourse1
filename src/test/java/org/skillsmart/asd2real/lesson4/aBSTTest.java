package org.skillsmart.asd2real.lesson4;

import org.junit.jupiter.api.Test;

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
        assertEquals(-1, tree.AddKey(18));
        assertEquals(6, tree.AddKey(14));
    }

    @Test
    void testGetLCA() {
        aBST tree = new aBST(2);
        assertEquals(0, tree.AddKey(8));
        assertEquals(1, tree.AddKey(4));
        assertEquals(2, tree.AddKey(12));
        assertEquals(6, tree.AddKey(14));
        assertEquals(4, tree.AddKey(6));
        assertEquals(3, tree.AddKey(2));
        assertEquals(5, tree.AddKey(10));
        assertEquals(-1, tree.AddKey(18));
        assertEquals(6, tree.AddKey(14));
    }
}