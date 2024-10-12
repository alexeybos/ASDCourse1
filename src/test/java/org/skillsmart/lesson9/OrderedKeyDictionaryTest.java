package org.skillsmart.lesson9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedKeyDictionaryTest {

    @Test
    void testPut() {
        OrderedKeyDictionary<String> dict = new OrderedKeyDictionary<>(4, String.class);
        dict.put("key1", "val1");
        assertEquals(1, dict.orderedKeys.count());
        dict.put("key2", "val2");
        assertEquals(2, dict.orderedKeys.count());
        dict.put("key10", "val10");
        assertEquals(3, dict.orderedKeys.count());
        dict.put("key1", "val111");
        assertEquals(3, dict.orderedKeys.count());
        dict.put("key11", "val11");
        assertEquals(4, dict.orderedKeys.count());
        assertEquals("val111", dict.values[0]);
        assertEquals("val10", dict.values[1]);
        assertEquals("val11", dict.values[2]);
        assertEquals("val2", dict.values[3]);
    }

    @Test
    void testGet() {
        OrderedKeyDictionary<String> dict = new OrderedKeyDictionary<>(4, String.class);
        dict.put("key1", "val1");
        dict.put("key2", "val2");
        dict.put("key10", "val10");
        dict.put("key1", "val111");
        dict.put("key11", "val11");
        assertEquals(4, dict.orderedKeys.count());
        assertEquals("val111", dict.get("key1"));
        assertEquals("val10", dict.get("key10"));
        assertEquals("val11", dict.get("key11"));
        assertEquals("val2", dict.get("key2"));
        assertNull(dict.get("key222"));
    }

    @Test
    void delete() {
        OrderedKeyDictionary<String> dict = new OrderedKeyDictionary<>(4, String.class);
        dict.put("key1", "val1");
        dict.put("key2", "val2");
        dict.put("key10", "val10");
        dict.put("key1", "val111");
        dict.put("key11", "val11");
        assertEquals(4, dict.orderedKeys.count());
        dict.delete("key11");
        assertEquals(3, dict.orderedKeys.count());
        assertEquals("val111", dict.get("key1"));
        assertEquals("val10", dict.get("key10"));
        assertEquals("val2", dict.get("key2"));
        assertNull(dict.get("key11"));
        dict.delete("key2");
        assertEquals(2, dict.orderedKeys.count());
        assertEquals("val111", dict.get("key1"));
        assertEquals("val10", dict.get("key10"));
        assertNull(dict.get("key2"));
        dict.delete("key2");
        assertEquals(2, dict.orderedKeys.count());
        assertEquals("val111", dict.get("key1"));
        assertEquals("val10", dict.get("key10"));
        assertNull(dict.get("key2"));
        dict.delete("key1");
        assertEquals(1, dict.orderedKeys.count());
        assertEquals("val10", dict.get("key10"));
        assertNull(dict.get("key1"));
        dict.delete("key222");
    }
}