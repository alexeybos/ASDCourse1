package org.skillsmart.lesson9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitDictionaryTest {

    @Test
    void testGet() {
        BitDictionary<String> dict = new BitDictionary<>(19, 8, String.class);
        dict.put("01010101", "value1");
        dict.put("01011101", "value2");
        dict.put("01010101", "value3");
        assertEquals("value2", dict.get("01011101"));
        assertEquals("value3", dict.get("01010101"));
    }
}