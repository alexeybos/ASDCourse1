package org.skillsmart.asd2real.lesson7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    @Test
    void testMakeHeap_EmptyOrOneRootHeap() {
        Heap heap = new Heap();
        int[] arr = new int[0];
        heap.MakeHeap(arr, 0);
        assertEquals(1, heap.HeapArray.length);
        assertEquals(0, heap.HeapArray[0]);
        arr = new int[]{8};
        heap.MakeHeap(arr, 0);
        assertEquals(1, heap.HeapArray.length);
        assertEquals(8, heap.HeapArray[0]);
    }

    @Test
    void testMakeHeap_MakeEmptyHeap() {
        Heap heap = new Heap();
        int[] arr = new int[0];
        heap.MakeHeap(arr, 2);
        assertEquals(7, heap.HeapArray.length);
    }

    @Test
    void testMakeHeap() {
        Heap heap = new Heap();
        int[] arr = new int[]{8, 4, 12};
        heap.MakeHeap(arr, 1);
        assertEquals(3, heap.HeapArray.length);
        assertEquals(12, heap.HeapArray[0]);
        assertEquals(4, heap.HeapArray[1]);
        assertEquals(8, heap.HeapArray[2]);

        heap = new Heap();
        arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap.MakeHeap(arr, 3);
        assertEquals(15, heap.HeapArray[0]);
        assertEquals(0, heap.HeapArray[14]);
        assertEquals(0, heap.HeapArray[13]);
        assertEquals(11, heap.HeapArray[12]);
    }

    @Test
    void testGetMax_EmptyOrOneRootHeap() {
        Heap heap = new Heap();
        int[] arr = new int[0];
        heap.MakeHeap(arr, 0);
        assertEquals(-1, heap.GetMax());
        assertTrue(heap.isCorrect());
        arr = new int[]{8};
        heap.MakeHeap(arr, 0);
        assertEquals(8, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(-1, heap.GetMax());
        assertTrue(heap.isCorrect());
    }

    @Test
    void testGetMax_WithFillingCheck() {
        Heap heap = new Heap();
        int[] arr = {11,7,9,5};
        heap.MakeHeap(arr, 2);
        for (int i = 0; i < arr.length; i++) {
            heap.HeapArray[i] = arr[i];
        }
        assertEquals(11, heap.GetMax());
        assertEquals(9, heap.HeapArray[0]);
        assertEquals(7, heap.HeapArray[1]);
        assertEquals(5, heap.HeapArray[2]);
        assertTrue(heap.isCorrect());
    }

    @Test
    void testGetMax() {
        Heap heap = new Heap();
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap.MakeHeap(arr, 3);
        assertEquals(15, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(14, heap.HeapArray[0]);
        assertEquals(14, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(12, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(11, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(9, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(8, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(7, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(6, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(5, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(4, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(3, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(2, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(1, heap.GetMax());
        assertTrue(heap.isCorrect());
        assertEquals(-1, heap.GetMax());
    }

    @Test
    void testGetMax_WithAdd() {
        Heap heap = new Heap();
        int[] arr = new int[]{10, 5, 8};
        heap.MakeHeap(arr, 2);
        assertTrue(heap.Add(4));
        assertTrue(heap.Add(1));
        assertEquals(10, heap.HeapArray[0]);
        assertEquals(5, heap.HeapArray[1]);
        assertEquals(8, heap.HeapArray[2]);
        assertEquals(4, heap.HeapArray[3]);
        assertEquals(1, heap.HeapArray[4]);
        assertEquals(10, heap.GetMax());
        assertEquals(8, heap.HeapArray[0]);
    }

    @Test
    void testAdd_EmptyHeap() {
        Heap heap = new Heap();
        int[] arr = new int[0];
        heap.MakeHeap(arr, 0);
        assertTrue(heap.Add(8));
        assertFalse(heap.Add(10));
    }

    @Test
    void testAdd() {
        Heap heap = new Heap();
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap.MakeHeap(arr, 3);
        assertTrue(heap.Add(10));
        assertTrue(heap.Add(13));
        assertFalse(heap.Add(20));
        for (int i = 0; i < heap.HeapArray.length; i++) {
            assertTrue(heap.HeapArray[i] != 0);
        }
    }

    @Test
    void testIsCorrect_EmptyOrOneElemHeap() {
        Heap heap = new Heap();
        int[] arr = new int[0];
        heap.MakeHeap(arr, 0);
        assertTrue(heap.isCorrect());
        heap.Add(8);
        assertTrue(heap.isCorrect());
    }

    @Test
    void testIsCorrect_Positive() {
        Heap heap = new Heap();
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap.MakeHeap(arr, 3);
        assertTrue(heap.isCorrect());
        assertTrue(heap.Add(10));
        assertTrue(heap.isCorrect());
        assertTrue(heap.Add(13));
        assertTrue(heap.isCorrect());
        assertFalse(heap.Add(20));
        assertTrue(heap.isCorrect());
    }

    @Test
    void testIsCorrect_Negative() {
        Heap heap = new Heap();
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap.MakeHeap(arr, 3);
        assertTrue(heap.isCorrect());
        int tmp = heap.HeapArray[4];
        heap.HeapArray[4] = heap.HeapArray[10];
        heap.HeapArray[10] = tmp;
        assertFalse(heap.isCorrect());
    }

    @Test
    void testFindMaxInRange_EmptyOrOneNodeHeap() {
        Heap heap = new Heap();
        int[] arr = new int[0];
        heap.MakeHeap(arr, 0);
        //assertTrue(heap.findMaxInRange(1, 10).isEmpty());
        assertNull(heap.findMaxInRange(1, 10));
        heap.Add(8);
        assertEquals(8, heap.findMaxInRange(1, 10));
        assertNull(heap.findMaxInRange(9, 50));
        assertNull(heap.findMaxInRange(1, 7));
    }

    @Test
    void testFindMaxInRange_WithNullsInRange() {
        Heap heap = new Heap();
        int[] arr = {8, 4};
        heap.MakeHeap(arr, 1);
        assertEquals(8, heap.findMaxInRange(1, 10));
        assertEquals(4, heap.findMaxInRange(1, 6));
        //assertEquals(8, heap.findMaxInRange(1, 10).getFirst());
        //assertEquals(4, heap.findMaxInRange(1, 6).getFirst());
    }

    @Test
    void testFindMaxInRange() {
        Heap heap = new Heap();
        int[] arr = new int[]{8, 4, 12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap.MakeHeap(arr, 3);
        //assertEquals(15, heap.findMaxInRange(10, 20).getFirst());
        assertEquals(15, heap.findMaxInRange(10, 20));
        //assertTrue(heap.findMaxInRange(10, 10).isEmpty());
        assertNull(heap.findMaxInRange(10, 10));
        //assertEquals(9, heap.findMaxInRange(5, 10).getFirst());
        assertEquals(9, heap.findMaxInRange(5, 10));
    }

    @Test
    void testUnion_TwoEmpty() {
        Heap heap = new Heap();
        int[] arr = new int[0];
        heap.MakeHeap(arr, 0);
        Heap heap2 = new Heap();
        int[] arr2 = new int[0];
        heap2.MakeHeap(arr2, 0);
        heap.union(heap2);
        assertEquals(1, heap.HeapArray.length);
        assertEquals(0, heap.HeapArray[0]);
    }

    @Test
    void testUnion_LeftEmpty() {
        Heap heap = new Heap();
        int[] arr = {8, 4};
        heap.MakeHeap(arr, 1);
        Heap heap2 = new Heap();
        int[] arr2 = new int[0];
        heap2.MakeHeap(arr2, 1);
        heap.union(heap2);
        assertEquals(3, heap.HeapArray.length);
        assertEquals(8, heap.HeapArray[0]);
        assertEquals(4, heap.HeapArray[1]);
        assertEquals(0, heap.HeapArray[2]);
    }

    @Test
    void testUnion_RightEmpty() {
        Heap heap = new Heap();
        int[] arr = {8, 4};
        heap.MakeHeap(arr, 1);
        Heap heap2 = new Heap();
        int[] arr2 = new int[0];
        heap2.MakeHeap(arr2, 1);
        heap2.union(heap);
        assertEquals(3, heap2.HeapArray.length);
        assertEquals(8, heap2.HeapArray[0]);
        assertEquals(4, heap2.HeapArray[1]);
        assertEquals(0, heap2.HeapArray[2]);
    }

    @Test
    void testUnion() {
        Heap heap = new Heap();
        int[] arr = {8, 4};
        heap.MakeHeap(arr, 3);
        Heap heap2 = new Heap();
        int[] arr2 = new int[]{12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap2.MakeHeap(arr2, 3);
        heap.union(heap2);
        assertEquals(15, heap.HeapArray[0]);
        assertTrue(heap.isCorrect());
        assertEquals(15, heap.GetMax());
        assertEquals(14, heap.HeapArray[0]);
        assertEquals(14, heap.GetMax());
        assertEquals(12, heap.GetMax());
        assertEquals(11, heap.GetMax());
        assertEquals(9, heap.GetMax());
        assertEquals(8, heap.GetMax());
        assertEquals(7, heap.GetMax());
        assertEquals(6, heap.GetMax());
        assertEquals(5, heap.GetMax());
        assertEquals(4, heap.GetMax());
        assertEquals(3, heap.GetMax());
        assertEquals(2, heap.GetMax());
        assertEquals(1, heap.GetMax());
        assertEquals(-1, heap.GetMax());

        assertEquals(15, heap2.HeapArray[0]);
        assertEquals(14, heap2.HeapArray[1]);

        assertEquals(15, heap2.GetMax());
        assertEquals(14, heap2.GetMax());
        assertEquals(12, heap2.GetMax());
        assertEquals(11, heap2.GetMax());
        assertEquals(9, heap2.GetMax());
        assertEquals(7, heap2.GetMax());
        assertEquals(6, heap2.GetMax());
        assertEquals(5, heap2.GetMax());
        assertEquals(3, heap2.GetMax());
        assertEquals(2, heap2.GetMax());
        assertEquals(1, heap2.GetMax());
    }

    @Test
    void testUnionByClone() {
        Heap heap = new Heap();
        int[] arr = {8, 4};
        heap.MakeHeap(arr, 3);
        Heap heap2 = new Heap();
        int[] arr2 = new int[]{12, 2, 6, 14, 1, 3, 7, 5, 9, 11, 15};
        heap2.MakeHeap(arr2, 3);
        Heap heap3 = heap.unionByClone(heap2);
        assertEquals(15, heap3.HeapArray[0]);
        assertTrue(heap3.isCorrect());
        assertEquals(15, heap3.GetMax());
        assertEquals(14, heap3.HeapArray[0]);
        assertEquals(14, heap3.GetMax());
        assertEquals(12, heap3.GetMax());
        assertEquals(11, heap3.GetMax());
        assertEquals(9, heap3.GetMax());
        assertEquals(8, heap3.GetMax());
        assertEquals(7, heap3.GetMax());
        assertEquals(6, heap3.GetMax());
        assertEquals(5, heap3.GetMax());
        assertEquals(4, heap3.GetMax());
        assertEquals(3, heap3.GetMax());
        assertEquals(2, heap3.GetMax());
        assertEquals(1, heap3.GetMax());
        assertEquals(-1, heap3.GetMax());

        assertEquals(8, heap.HeapArray[0]);
        assertEquals(4, heap.HeapArray[1]);
    }
}