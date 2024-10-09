package org.skillsmart.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaltHashTableTest {

    @Test
    void testPut_NoSalt() {
        SaltHashTable tab = new SaltHashTable(19, 3, false);
        assertEquals(2, tab.seekSlot("10"));
        assertEquals(12, tab.seekSlot("5On1"));
        assertEquals(12, tab.seekSlot("r26Y"));
        assertEquals(12, tab.seekSlot("ZWtD"));
        assertEquals(2, tab.put("10"));
        assertEquals(12, tab.put("5On1"));
        assertEquals(15, tab.put("r26Y"));
        assertEquals(18, tab.put("ZWtD"));
        assertEquals(3, tab.collisionCount);
    }

    @Test
    void testFind_NoSalt() {
        SaltHashTable tab = new SaltHashTable(19, 3, false);
        assertEquals(2, tab.put("10"));
        assertEquals(12, tab.put("5On1"));
        assertEquals(15, tab.put("r26Y"));
        assertEquals(18, tab.put("ZWtD"));
        assertEquals(2, tab.find("10"));
        assertEquals(12, tab.find("5On1"));
        assertEquals(15, tab.find("r26Y"));
        assertEquals(18, tab.find("ZWtD"));
        assertEquals(-1, tab.find("011"));
        assertEquals(3, tab.collisionCount);
    }

    @Test
    void testFind_Salt() {
        SaltHashTable tab = new SaltHashTable(19, 3, true);
        tab.put("10");
        tab.put("5On1");
        tab.put("r26Y");
        tab.put("ZWtD");
        assertEquals(0, tab.collisionCount);
        assertNotEquals(-1, tab.find("10"));
        assertNotEquals(-1, tab.find("5On1"));
        assertNotEquals(-1, tab.find("r26Y"));
        assertNotEquals(-1, tab.find("ZWtD"));
    }
}