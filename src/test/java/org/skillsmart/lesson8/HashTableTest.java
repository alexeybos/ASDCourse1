package org.skillsmart.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void testHashFun() {
        HashTable tab = new HashTable(17, 3);
        assertEquals(12, tab.hashFun("10"));
        assertEquals(12, tab.hashFun("01"));
        assertEquals(2, tab.hashFun("5"));
        assertEquals(12, tab.hashFun("hash"));
    }

    @Test
    void testSeekSlot() {
        HashTable tab = new HashTable(17, 3);
        assertEquals(12, tab.seekSlot("10"));
        assertEquals(2, tab.seekSlot("5"));
        assertEquals(12, tab.seekSlot("01"));
        assertEquals(12, tab.seekSlot("hash"));
        tab.put("10");
        tab.put("5");
        assertEquals(2, tab.count);
        assertEquals(15, tab.seekSlot("01"));
        tab.put("01");
        assertEquals(1, tab.seekSlot("hash"));
        for (int i = 0; i < 15; i++) {
            tab.put("hash");
        }
        assertEquals(17, tab.count);
        assertEquals(-1, tab.seekSlot("123"));
    }

    @Test
    void put() {
        HashTable tab = new HashTable(17, 3);
        tab.put("10");
        tab.put("5");
        assertEquals(2, tab.count);
        assertEquals(15, tab.put("01"));
        assertEquals(1, tab.put("hash"));
        for (int i = 0; i < 14; i++) {
            tab.put("hash");
        }
        assertEquals(17, tab.count);
        assertEquals(-1, tab.put("123"));
    }

    @Test
    void find() {
        HashTable table = new HashTable(17, 3);
        table.put("10");
        table.put("5");
        table.put("01");
        table.put("hash");
        assertEquals(12, table.find("10"));
        assertEquals(15, table.find("01"));
        assertEquals(1, table.find("hash"));
        assertEquals(2, table.find("5"));
        assertEquals(-1, table.find("123"));
    }
}