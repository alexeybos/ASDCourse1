package org.skillsmart.asd2.lesson1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortLevelTest {

    @Test
    void selectionSortStep() {
        int[] array = {5, 4, 2, 3, 1};
        SortLevel.SelectionSortStep(array, 0);
        assertEquals(1, array[0]);
        assertEquals(5, array[4]);
        SortLevel.SelectionSortStep(array, 3);
        assertEquals(3, array[3]);
        assertEquals(5, array[4]);
        SortLevel.SelectionSortStep(array, 4);
        assertEquals(5, array[4]);
    }

    @Test
    void testSelectionSortStep_OneElement() {
        int[] array = {5};
        SortLevel.SelectionSortStep(array, 0);
        assertEquals(5, array[0]);
    }

    @Test
    void bubbleSortStep_withExchanges() {
        int[] array = {5, 4, 2, 3, 1};
        assertFalse(SortLevel.BubbleSortStep(array));
        assertEquals(4, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
        assertEquals(1, array[3]);
        assertEquals(5, array[4]);
        int[] array2 = {3, 2, 4, 5, 1};
        assertFalse(SortLevel.BubbleSortStep(array2));
        assertEquals(2, array2[0]);
        assertEquals(3, array2[1]);
        assertEquals(4, array2[2]);
        assertEquals(1, array2[3]);
        assertEquals(5, array2[4]);
    }

    @Test
    void bubbleSortStep_withOutExchanges() {
        int[] array = {1, 2, 3, 4, 5};
        assertTrue(SortLevel.BubbleSortStep(array));
    }

    @Test
    void bubbleSortStep_oneElement() {
        int[] array = {3};
        assertTrue(SortLevel.BubbleSortStep(array));
    }
}