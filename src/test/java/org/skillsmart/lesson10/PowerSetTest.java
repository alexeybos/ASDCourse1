package org.skillsmart.lesson10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PowerSetTest {

    @Test
    void testPut() {
        PowerSet set = new PowerSet();
        set.put("12");
        assertEquals(1, set.size());
        set.put("12");
        assertEquals(1, set.size());
    }

    @Test
    void testGet() {
        PowerSet set = new PowerSet();
        set.put("12");
        assertEquals(1, set.size());
        set.put("121");
        assertTrue(set.get("121"));
        assertTrue(set.get("12"));
        assertFalse(set.get("1211"));
    }

    @Test
    void testRemove() {
        PowerSet set = new PowerSet();
        set.put("12");
        assertEquals(1, set.size());
        set.put("121");
        assertEquals(2, set.size());
        assertFalse(set.remove("111"));
        assertEquals(2, set.size());
        assertTrue(set.remove("121"));
        assertEquals(1, set.size());
        assertTrue(set.remove("12"));
        assertEquals(0, set.size());
        assertFalse(set.remove("1"));
    }

    @Test
    void testIntersection() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("1211");
        set2.put("112");
        set2.put("121");
        set2.put("12111");
        PowerSet set3 = set1.intersection(set2);
        assertEquals(1, set3.size());
        assertTrue(set3.get("112"));
    }

    @Test
    void testIntersection_empty() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("1");
        set2.put("2");
        set2.put("11112");
        PowerSet set3 = set1.intersection(set2);
        assertNotNull(set3);
        assertEquals(0, set3.size());
    }

    @Test
    void testUnion() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("121");
        PowerSet set3 = set1.union(set2);
        assertEquals(4, set3.size());
        assertTrue(set3.get("12"));
        assertTrue(set3.get("112"));
        assertTrue(set3.get("1112"));
        assertTrue(set3.get("121"));
    }

    @Test
    void testUnion_withEmpty() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        PowerSet set3 = set1.union(set2);
        assertEquals(3, set3.size());
        assertTrue(set3.get("12"));
        assertTrue(set3.get("112"));
        assertTrue(set3.get("1112"));

        PowerSet set4 = set2.union(set1);
        assertEquals(3, set4.size());
        assertTrue(set4.get("12"));
        assertTrue(set4.get("112"));
        assertTrue(set4.get("1112"));
    }

    @Test
    void testDifference() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("121");
        PowerSet set3 = set1.difference(set2);
        assertEquals(1, set3.size());
        assertTrue(set3.get("1112"));
    }

    @Test
    void testDifference_empty() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("1112");
        PowerSet set3 = set1.difference(set2);
        assertNotNull(set3);
        assertEquals(0, set3.size());
    }

    @Test
    void testIsSubset_Subset() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        assertTrue(set1.isSubset(set2));
    }

    @Test
    void testIsSubset_EqualSubset() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("1112");
        assertTrue(set1.isSubset(set2));
    }

    @Test
    void testIsSubset_ParamIsBigger() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("1112");
        set2.put("11112");
        assertFalse(set1.isSubset(set2));
    }

    @Test
    void testIsSubset_NotSubset() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("11112");
        assertFalse(set1.isSubset(set2));
    }

    @Test
    void testEquals_Positive() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("1112");
        assertTrue(set1.equals(set2));
    }

    @Test
    void testEquals_Negative() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("11112");
        assertFalse(set1.equals(set2));
    }

    @Test
    void testCartesianProduct() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        set1.put("l1");
        set1.put("l2");
        set1.put("l3");

        set2.put("r1");
        set2.put("r2");
        set2.put("r3");
        List<List<String>> result = set1.cartesianProduct(set2);
        assertEquals(9, result.size());
        assertEquals("l1", result.get(0).get(0));
        assertEquals("r1", result.get(0).get(1));
        assertEquals("l1", result.get(1).get(0));
        assertEquals("r2", result.get(1).get(1));
        assertEquals("l1", result.get(2).get(0));
        assertEquals("r3", result.get(2).get(1));
        assertEquals("l2", result.get(3).get(0));
        assertEquals("r1", result.get(3).get(1));
        assertEquals("l2", result.get(4).get(0));
        assertEquals("r2", result.get(4).get(1));
        assertEquals("l2", result.get(5).get(0));
        assertEquals("r3", result.get(5).get(1));
        assertEquals("l3", result.get(6).get(0));
        assertEquals("r1", result.get(6).get(1));
        assertEquals("l3", result.get(7).get(0));
        assertEquals("r2", result.get(7).get(1));
        assertEquals("l3", result.get(8).get(0));
        assertEquals("r3", result.get(8).get(1));
    }

    @Test
    void testSpeed() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        for (int i = 0; i < 20000; i++) {
            set1.put(String.valueOf(i));
        }
        for (int i = 20000; i < 40000; i++) {
            set2.put(String.valueOf(i));
        }
        long intersectionStartTime = System.currentTimeMillis();
        PowerSet result1 = set1.intersection(set2);
        long intersectionCost =System.currentTimeMillis()-intersectionStartTime;
        //System.out.println("for intersection = "+ intersectionCost);
        assertTrue(intersectionCost < 2000);

        long unionStartTime = System.currentTimeMillis();
        result1 = set1.union(set2);
        long unionCost =System.currentTimeMillis()-unionStartTime;
        //System.out.println("for unionCost = "+ unionCost);
        assertTrue(unionCost < 2000);

        long differenceStartTime = System.currentTimeMillis();
        result1 = set1.difference(set2);
        long differenceCost =System.currentTimeMillis()-differenceStartTime;
        //System.out.println("for differenceCost = "+ differenceCost);
        assertTrue(differenceCost < 2000);

        long isSubsetStartTime = System.currentTimeMillis();
        boolean result = set1.isSubset(set2);
        long isSubsetCost =System.currentTimeMillis()-isSubsetStartTime;
        //System.out.println("for isSubsetCost = "+ isSubsetCost);
        assertTrue(isSubsetCost < 2000);

        long equalsStartTime = System.currentTimeMillis();
        result = set1.equals(set2);
        long equalsCost =System.currentTimeMillis()-equalsStartTime;
        //System.out.println("for equalsCost = "+ equalsCost);
        assertTrue(equalsCost < 2000);
    }
}