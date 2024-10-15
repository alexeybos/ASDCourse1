package org.skillsmart.lesson10;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    void testPut() {
        Bag bag = new Bag();
        bag.put("123");
        bag.put("123456");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123456");
        assertEquals(7, bag.values.size());
        assertTrue(bag.values.contains("123"));
        assertTrue(bag.values.contains("123456"));
    }

    @Test
    void testDelete() {
        Bag bag = new Bag();
        bag.put("123");
        bag.put("123456");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123456");
        assertEquals(7, bag.values.size());
        assertTrue(bag.values.contains("123"));
        assertTrue(bag.values.contains("123456"));
        bag.delete("123456");
        assertEquals(6, bag.values.size());
        assertTrue(bag.values.contains("123"));
        assertTrue(bag.values.contains("123456"));
        bag.delete("123456");
        assertEquals(5, bag.values.size());
        assertTrue(bag.values.contains("123"));
        assertFalse(bag.values.contains("123456"));
    }

    @Test
    void elemFrequency() {
        Bag bag = new Bag();
        bag.put("123");
        bag.put("123456");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123456");
        Map<String, Integer> frequencies = bag.elemFrequency();
        assertEquals(5, frequencies.get("123"));
        assertEquals(2, frequencies.get("123456"));
    }

    @Test
    void testGet() {
        Bag bag = new Bag();
        bag.put("123");
        bag.put("123456");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123");
        bag.put("123456");
        assertTrue(bag.get("123"));
        assertTrue(bag.get("123456"));
        assertFalse(bag.get("1234"));
    }

    @Test
    void testIntersection() {
        Bag set1 = new Bag();
        Bag set2 = new Bag();
        set1.put("12");
        set1.put("112");
        set1.put("112");
        set1.put("1112");

        set2.put("1211");
        set2.put("112");
        set2.put("121");
        set2.put("12111");
        Bag set3 = set1.intersection(set2);
        assertEquals(2, set3.size());
        assertTrue(set3.get("112"));
    }

    @Test
    void testUnion() {
        Bag set1 = new Bag();
        Bag set2 = new Bag();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("121");
        Bag set3 = set1.union(set2);
        assertEquals(6, set3.size());
        assertTrue(set3.get("12"));
        assertTrue(set3.get("112"));
        assertTrue(set3.get("1112"));
        assertTrue(set3.get("121"));
    }

    @Test
    void testDifference() {
        Bag set1 = new Bag();
        Bag set2 = new Bag();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("121");
        Bag set3 = set1.difference(set2);
        assertEquals(1, set3.size());
        assertTrue(set3.get("1112"));
    }

    @Test
    void testIsSubset_Subset() {
        Bag set1 = new Bag();
        Bag set2 = new Bag();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        assertTrue(set1.isSubset(set2));
    }

    @Test
    void testEquals_Positive() {
        Bag set1 = new Bag();
        Bag set2 = new Bag();
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
        Bag set1 = new Bag();
        Bag set2 = new Bag();
        set1.put("12");
        set1.put("112");
        set1.put("1112");

        set2.put("12");
        set2.put("112");
        set2.put("11112");
        assertFalse(set1.equals(set2));
    }
}