package org.skillsmart.lesson9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NativeDictionaryTest {

    @Test
    void isKey() {
        NativeDictionary<String> dict = new NativeDictionary<>(17, String.class);
        dict.put("01", "value1"); //12
        dict.put("5", "value2"); //2
        dict.put("hash", "newHashValue"); //12
        assertTrue(dict.isKey("01"));
        assertTrue(dict.isKey("5"));
        assertTrue(dict.isKey("hash"));
        assertFalse(dict.isKey("noKey"));
    }

    @Test
    void testPut() {
        NativeDictionary<String> dict = new NativeDictionary<>(17, String.class);
        dict.put("01", "value1"); //12
        assertEquals("01", dict.slots[12]);
        assertEquals("value1", dict.values[12]);
        dict.put("5", "value2"); //2
        assertEquals("5", dict.slots[2]);
        assertEquals("value2", dict.values[2]);
        dict.put("hash", "valueHash"); //12
        assertEquals("hash", dict.slots[15]);
        assertEquals("valueHash", dict.values[15]);
        dict.put("hash", "newHashValue");
        assertEquals("hash", dict.slots[15]);
        assertEquals("newHashValue", dict.values[15]);
    }

    @Test
    void testPutInteger() {
        NativeDictionary<Integer> dict = new NativeDictionary<>(17, Integer.class);
        dict.put("01", 100); //12
        assertEquals("01", dict.slots[12]);
        assertEquals(100, dict.values[12]);
        dict.put("5", 200); //2
        assertEquals("5", dict.slots[2]);
        assertEquals(200, dict.values[2]);
    }

    @Test
    void testPutInteger_OverHead() {
        NativeDictionary<Integer> dict = new NativeDictionary<>(5, Integer.class);
        dict.put("01", 100); //12
        dict.put("5", 200); //2
        dict.put("6", 200); //2
        dict.put("7", 200); //2
        dict.put("8", 200); //2
        dict.put("9", 200); //2
    }

    @Test
    void get() {
        NativeDictionary<String> dict = new NativeDictionary<>(17, String.class);
        dict.put("01", "value1"); //12
        dict.put("5", "value2"); //2
        dict.put("hash", "newHashValue"); //12
        assertEquals("value1", dict.get("01"));
        assertEquals("value2", dict.get("5"));
        assertEquals("newHashValue", dict.get("hash"));
        assertNull(dict.get("noKey"));
    }
}