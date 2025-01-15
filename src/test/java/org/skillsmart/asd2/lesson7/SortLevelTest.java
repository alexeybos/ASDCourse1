package org.skillsmart.asd2.lesson7;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortLevelTest {

    @Test
    void kthOrderStatisticsStep() {
        int[] array = {4,3,2,1,5};
        ArrayList<Integer> res = SortLevel.KthOrderStatisticsStep(array, 0, array.length - 1, 1);
        System.out.println(res);
        res = SortLevel.KthOrderStatisticsStep(array, res.get(0), res.get(1), 1);
        System.out.println(res);
    }

    @Test
    void kthOrderStatisticsStep_OneStep() {
        int[] array = {4,3,2,1,5};
        ArrayList<Integer> res = SortLevel.KthOrderStatisticsStep(array, 0, array.length - 1, 2);
        System.out.println(res);
    }
}