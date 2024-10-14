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
}