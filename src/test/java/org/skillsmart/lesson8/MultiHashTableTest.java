package org.skillsmart.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiHashTableTest {

    @Test
    void put() {
        MultiHashTable tab = new MultiHashTable(17, 3);
        assertEquals(12, tab.seekSlot("10"));
        assertEquals(2, tab.seekSlot("5"));
        assertEquals(12, tab.seekSlot("01"));
        assertEquals(12, tab.seekSlot("hash"));
        assertEquals(15, tab.hashFun1("01"));
        assertEquals(4, tab.hashFun1("hash"));
        assertEquals(12, tab.put("10"));
        assertEquals(2, tab.put("5"));
        assertEquals(4, tab.put("hash"));
        assertEquals(15, tab.put("01"));
    }

    @Test
    void find() {
        MultiHashTable tab = new MultiHashTable(17, 3);
        assertEquals(12, tab.put("10"));
        assertEquals(2, tab.put("5"));
        assertEquals(4, tab.put("hash"));
        assertEquals(15, tab.put("01"));
        assertEquals(12, tab.find("10"));
        assertEquals(2, tab.find("5"));
        assertEquals(4, tab.find("hash"));
        assertEquals(15, tab.find("01"));
        assertEquals(-1, tab.find("011"));
    }
}