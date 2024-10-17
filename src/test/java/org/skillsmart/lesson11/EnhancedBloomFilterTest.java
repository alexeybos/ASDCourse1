package org.skillsmart.lesson11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedBloomFilterTest {

    @Test
    void testDelete() {
        EnhancedBloomFilter filter = new EnhancedBloomFilter(32);
        assertEquals(0, filter.bloom);
        filter.add("0123456789");
        assertEquals(8224, filter.bloom);
        filter.add("0123456789");
        assertEquals(8224, filter.bloom);
        filter.delete("0123456789");
        assertEquals(8224, filter.bloom);
        filter.delete("0123456789");
        assertEquals(0, filter.bloom);
    }
}