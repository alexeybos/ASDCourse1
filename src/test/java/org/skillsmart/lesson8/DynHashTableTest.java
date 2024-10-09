package org.skillsmart.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynHashTableTest {

    @Test
    void testMakeArray() {
        DynHashTable tab = new DynHashTable(17, 3);
        for (int i = 0; i < 13; i++) {
            tab.put(Integer.toString(i));
        }
        assertEquals(13, tab.count);
        assertEquals(16, tab.slots.capacity);
        tab.put("new");
        assertEquals(14, tab.count);
        assertEquals(32, tab.slots.capacity);
    }
}