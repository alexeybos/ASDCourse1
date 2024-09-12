package org.skillsmart.lesson3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {

    @BeforeEach
    void setUp() {
        /*DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        array.append(3);*/
    }

    @Test
    void testMakeArray_ByDefault() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        assertEquals(0, array.count);
        assertEquals(16, array.capacity);
    }

    @Test
    void testMakeArray_ForExtendsCurrentArray() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.makeArray(20);
        assertEquals(0, array.count);
        assertEquals(20, array.capacity);
    }

    @Test
    void testGetItem_OutBound() {

    }

    @Test
    void testGetItem() {

    }

    @Test
    void testAppend() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        assertEquals(2, array.count);
        assertEquals(16, array.capacity);
    }
    @Test
    void testAppend_WithReallocation() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        array.append(16);
        assertEquals(17, array.count);
        assertEquals(32, array.capacity);
    }

    @Test
    void insert() {
    }

    @Test
    void remove() {
    }

    //TODO нужен тест на увеличение(*2), потом уменьшение(/1.5), потом увеличение(*2) - корректность capacyty
}