package org.skillsmart.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDynArrayTest {

    @Test
    void testAppend_WithReallocation() {
        BankDynArray<Integer> array = new BankDynArray<>(Integer.class);
        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        array.append(16);
        assertEquals(17, array.count);
        assertEquals(32, array.capacity);
    }

    @Test
    void testMultyBufferChange() {
        BankDynArray<Integer> array = new BankDynArray<>(Integer.class);
        for (int i = 0; i < 17; i++) {
            array.append(i);
        }
        assertEquals(32, array.capacity);
        assertEquals(17, array.count);
        array.remove(16);
        assertEquals(32, array.capacity);
        assertEquals(16, array.count);
        for (int i = 0; i < 4; i++) {
            array.append(i);
        }
        array.remove(15);
        array.remove(14);
        array.remove(13);
        array.remove(12);
        array.remove(11);
        assertEquals(21, array.capacity);
        assertEquals(15, array.count);
        for (int i = 0; i < 7; i++) {
            array.append(i);
        }
        assertEquals(42, array.capacity);
        assertEquals(22, array.count);
    }
}