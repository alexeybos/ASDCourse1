package org.skillsmart.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynHashTableTest {

    @Test
    void put() {
        DynHashTable tab = new DynHashTable(3);
        for (int i = 0; i < 13; i++) {
            tab.put(Integer.toString(i));
        }
        assertEquals(13, tab.count);
        assertEquals(3, tab.slots.getBufferSize());
        tab.put("new");
        assertEquals(14, tab.count);
        assertEquals(18, tab.slots.getBufferSize());
    }
}