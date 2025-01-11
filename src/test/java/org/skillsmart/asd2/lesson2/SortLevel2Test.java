package org.skillsmart.asd2.lesson2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortLevel2Test {

    @Test
    void insertionSortStep() {
        int[] array = {7,6,5,4,3,2,1};
        SortLevel.InsertionSortStep(array, 3, 3);
        assertEquals(1, array[3]);
        assertEquals(4, array[6]);
        //Например, вызов InsertionSortStep( [1,6,5,4,3,2,7], 3, 1 ) изменит входной массив на [1,3,5,4,6,2,7].
        int[] array2 = {1,6,5,4,3,2,7};
        SortLevel.InsertionSortStep(array2, 3, 1);
        assertEquals(1, array2[0]);
        assertEquals(3, array2[1]);
        assertEquals(5, array2[2]);
        assertEquals(4, array2[3]);
        assertEquals(6, array2[4]);
        assertEquals(2, array2[5]);
        assertEquals(7, array2[6]);
        int[] array3 = {3,4,5,6,7,2,1};
        SortLevel.InsertionSortStep(array3, 3, 1);
        assertEquals(4, array3[1]);
        assertEquals(7, array3[4]);
    }

    @Test
    void testKnuthSequence_EmptyArray() {
        ArrayList<Integer> result = SortLevel.KnuthSequence(0);
        assertEquals(0, result.size());
    }

    @Test
    void testKnuthSequence_OneElementArray() {
        ArrayList<Integer> result = SortLevel.KnuthSequence(1);
        assertEquals(1, result.size());
        assertEquals(1, result.getFirst());
    }

    @Test
    void testKnuthSequence_ManyElementArray() {
        ArrayList<Integer> result1 = SortLevel.KnuthSequence(4);
        assertEquals(1, result1.size());
        assertEquals(1, result1.getFirst());
        ArrayList<Integer> result2 = SortLevel.KnuthSequence(5);
        assertEquals(2, result2.size());
        assertEquals(4, result2.getFirst());
        assertEquals(1, result2.get(1));
        ArrayList<Integer> result2a = SortLevel.KnuthSequence(13);
        assertEquals(2, result2a.size());
        assertEquals(4, result2a.getFirst());
        assertEquals(1, result2a.get(1));
        ArrayList<Integer> result3 = SortLevel.KnuthSequence(15);
        assertEquals(3, result3.size());
        assertEquals(13, result3.getFirst());
        assertEquals(4, result3.get(1));
        assertEquals(1, result3.get(2));
        ArrayList<Integer> result3a = SortLevel.KnuthSequence(14);
        assertEquals(3, result3a.size());
        assertEquals(13, result3a.getFirst());
        assertEquals(4, result3a.get(1));
        assertEquals(1, result3a.get(2));
        ArrayList<Integer> result4 = SortLevel.KnuthSequence(100);
        assertEquals(4, result4.size());
        assertEquals(40, result4.getFirst());
        assertEquals(13, result4.get(1));
        assertEquals(4, result4.get(2));
        assertEquals(1, result4.get(3));
        ArrayList<Integer> result5 = SortLevel.KnuthSequence(150);
        assertEquals(5, result5.size());
        assertEquals(121, result5.getFirst());
        assertEquals(40, result5.get(1));
        assertEquals(13, result5.get(2));
        assertEquals(4, result5.get(3));
        assertEquals(1, result5.get(4));
        ArrayList<Integer> result6 = SortLevel.KnuthSequence(400);
        assertEquals(6, result6.size());
        assertEquals(364, result6.getFirst());
        assertEquals(121, result6.get(1));
        assertEquals(40, result6.get(2));
        assertEquals(13, result6.get(3));
        assertEquals(4, result6.get(4));
        assertEquals(1, result6.get(5));

        ArrayList<Integer> result7 = SortLevel.KnuthSequence(3300);
        assertEquals(8, result7.size());
        assertEquals(3280, result7.getFirst());
        assertEquals(1093, result7.get(1));
        assertEquals(364, result7.get(2));
        assertEquals(121, result7.get(3));
        assertEquals(40, result7.get(4));
        assertEquals(13, result7.get(5));
        assertEquals(4, result7.get(6));
        assertEquals(1, result7.get(7));
    }
}