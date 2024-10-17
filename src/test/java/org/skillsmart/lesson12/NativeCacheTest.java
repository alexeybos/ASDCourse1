package org.skillsmart.lesson12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NativeCacheTest {

    @Test
    void testPut() {
        NativeCache<String> cache = new NativeCache<>(5, String.class);
        cache.put("1", "value1");
        assertEquals("value1", cache.values[4]);
        cache.put("2", "value2");
        assertEquals("value2", cache.values[0]);
        cache.put("3", "value3");
        assertEquals("value3", cache.values[1]);
        cache.put("4", "value4");
        assertEquals("value4", cache.values[2]);
        cache.put("5", "value5");
        assertEquals("value5", cache.values[3]);
        assertEquals("value1", cache.get("1"));
        assertEquals("value2", cache.get("2"));
        assertEquals("value3", cache.get("3"));
        assertEquals("value4", cache.get("4"));
        assertEquals("value5", cache.get("5"));
        for (int i = 0; i < cache.size; i++) {
            assertEquals(1, cache.hits[i]);
        }
        assertEquals("value2", cache.get("2"));
        assertEquals("value3", cache.get("3"));
        assertEquals("value4", cache.get("4"));
        assertEquals("value5", cache.get("5"));

        assertEquals("value3", cache.get("3"));
        assertEquals("value4", cache.get("4"));
        assertEquals("value5", cache.get("5"));

        assertEquals("value4", cache.get("4"));
        assertEquals("value5", cache.get("5"));

        assertEquals("value5", cache.get("5"));
        assertEquals(1, cache.hits[4]);
        assertEquals(2, cache.hits[0]);
        assertEquals(3, cache.hits[1]);
        assertEquals(4, cache.hits[2]);
        assertEquals(5, cache.hits[3]);
        cache.put("1", "value11");
        assertEquals("value11", cache.get("1"));
        assertEquals(3, cache.hits[4]);

        cache.put("6", "value6");
        assertEquals(0, cache.hits[0]);
        assertNull(cache.get("2"));
        assertEquals("value6", cache.get("6"));
        assertEquals(1, cache.hits[0]);
    }
}