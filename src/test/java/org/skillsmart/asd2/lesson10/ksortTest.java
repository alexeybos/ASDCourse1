package org.skillsmart.asd2.lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ksortTest {

    @Test
    void testIndex() {
        ksort ksortObject = new ksort();
        assertEquals(0, ksortObject.index("a00"));
        assertEquals(793, ksortObject.index("h93"));
        assertEquals(111, ksortObject.index("b11"));
        assertEquals(317, ksortObject.index("d17"));
        assertEquals(-1, ksortObject.index("k00"));
    }

    @Test
    void add() {
        ksort ksortObject = new ksort();
        assertTrue(ksortObject.add("a00"));
        assertTrue(ksortObject.add("h93"));
        assertTrue(ksortObject.add("b11"));
        assertTrue(ksortObject.add("d17"));
        assertFalse(ksortObject.add("k00"));
    }
}