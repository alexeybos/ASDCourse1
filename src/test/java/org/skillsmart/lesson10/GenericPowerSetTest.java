package org.skillsmart.lesson10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericPowerSetTest {

    @Test
    void cartesianProduct() {
        GenericPowerSet<String> set1 = new GenericPowerSet<>();
        GenericPowerSet<String> set2 = new GenericPowerSet<>();
        set1.put("l1");
        set1.put("l2");
        set1.put("l3");

        set2.put("r1");
        set2.put("r2");
        set2.put("r3");
        GenericPowerSet<List<String>> result = set1.cartesianProduct(set2);
        assertEquals(9, result.size());
        assertTrue(result.get(new ArrayList<>(Arrays.asList("l1", "r1"))));
    }
}