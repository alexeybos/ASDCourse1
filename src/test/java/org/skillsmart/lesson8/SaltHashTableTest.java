package org.skillsmart.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaltHashTableTest {

    @Test
    void testPut_NoSalt() {
        SaltHashTable tab = new SaltHashTable(19, 3, false);
        assertEquals(2, tab.seekSlot("10"));
        assertEquals(12, tab.seekSlot("hmbd"));
        assertEquals(12, tab.seekSlot("gdPm"));
        assertEquals(12, tab.seekSlot("LoeB"));
        assertEquals(2, tab.put("10"));
        assertEquals(12, tab.put("hmbd"));
        assertEquals(15, tab.put("gdPm"));
        assertEquals(18, tab.put("LoeB"));
        assertEquals(3, tab.collisionCount);
    }

    @Test
    void testFind_NoSalt() {
        SaltHashTable tab = new SaltHashTable(19, 3, false);
        assertEquals(2, tab.put("10"));
        assertEquals(12, tab.put("hmbd"));
        assertEquals(15, tab.put("gdPm"));
        assertEquals(18, tab.put("LoeB"));
        assertEquals(2, tab.find("10"));
        assertEquals(12, tab.find("hmbd"));
        assertEquals(15, tab.find("gdPm"));
        assertEquals(18, tab.find("LoeB"));
        assertEquals(-1, tab.find("011"));
        assertEquals(3, tab.collisionCount);
    }

    @Test
    void testFind_Salt() {
        SaltHashTable tab = new SaltHashTable(19, 3, true);
        int ind10 = tab.put("10");
        int ind2 = tab.put("hmbd");
        int ind3 = tab.put("gdPm");
        int ind4 = tab.put("LoeB");
        assertEquals(0, tab.collisionCount);
        assertEquals(ind10, tab.find("10"));
        assertEquals(ind2, tab.find("hmbd"));
        assertEquals(ind3, tab.find("gdPm"));
        assertEquals(ind4, tab.find("LoeB"));
    }
}