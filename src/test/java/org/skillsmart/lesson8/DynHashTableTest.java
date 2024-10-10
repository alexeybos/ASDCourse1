package org.skillsmart.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynHashTableTest {

    @Test
    void put() {
        DynHashTable tab = new DynHashTable(3);
        for (int i = 0; i < 16; i++) {
            tab.put(Integer.toString(i));
        }
        assertEquals(16, tab.count);
        //assertEquals(16, tab.slots.capacity);
        tab.put("new");
        assertEquals(17, tab.count);
        //assertEquals(32, tab.slots.capacity);
    }
}