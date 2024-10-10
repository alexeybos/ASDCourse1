package org.skillsmart.lesson3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {

    @Test
    void testMakeArray_ByDefault() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        /*assertEquals(0, array.count);
        assertEquals(16, array.capacity);*/
    }

    @Test
    void testMakeArray_ForExtendsCurrentArray() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.makeArray(20);
        /*assertEquals(0, array.count);
        assertEquals(20, array.capacity);*/
    }

    @Test
    void testGetItem_OutBound() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        ArrayIndexOutOfBoundsException e = assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.getItem(17));
    }

    @Test
    void testGetItem_OutBoundInEmptyArray() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        ArrayIndexOutOfBoundsException e = assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.getItem(17));
    }

    @Test
    void testGetItem() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        assertEquals(1,array.getItem(0));
    }

    @Test
    void testGetItem_SeveralValues() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        array.append(3);
        assertEquals(2, array.getItem(1));
    }

    @Test
    void testAppend() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        /*assertEquals(2, array.count);
        assertEquals(16, array.capacity);*/
        assertEquals(1, array.getItem(0));
        assertEquals(2, array.getItem(1));
    }
    @Test
    void testAppend_WithReallocation() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        array.append(16);
        /*assertEquals(17, array.count);
        assertEquals(32, array.capacity);*/
        assertEquals(16, array.getItem(16));
    }

    @Test
    void testInsert_ToEmptyArrayNoBufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.insert(1, 0);
        /*assertEquals(1, array.count);
        assertEquals(16, array.capacity);*/
        assertEquals(1, array.getItem(0));
    }

    @Test
    void testInsert_NoBufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(3);
        array.append(4);
        array.insert(2, 1);
        /*assertEquals(4, array.count);
        assertEquals(16, array.capacity);*/
        assertEquals(1, array.getItem(0));
        assertEquals(2, array.getItem(1));
        assertEquals(3, array.getItem(2));
        assertEquals(4, array.getItem(3));
    }

    @Test
    void testInsert_ToEndPositionNoBufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        array.append(3);
        array.insert(4, 3);
        /*assertEquals(4, array.count);
        assertEquals(16, array.capacity);*/
        assertEquals(1, array.getItem(0));
        assertEquals(2, array.getItem(1));
        assertEquals(3, array.getItem(2));
        assertEquals(4, array.getItem(3));
    }

    @Test
    void testInsert_BufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        array.insert(16, 5);
        /*assertEquals(17, array.count);
        assertEquals(32, array.capacity);*/
        assertEquals(16, array.getItem(5));
        assertEquals(15, array.getItem(16));
    }

    @Test
    void testInsert_ToEndPositionBufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        array.insert(16, 16);
        /*assertEquals(17, array.count);
        assertEquals(32, array.capacity);*/
        assertEquals(16, array.getItem(16));
    }

    @Test
    void testInsert_OutOfBoundInEmptyArray() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        ArrayIndexOutOfBoundsException e = assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.insert(4, 17));
    }

    @Test
    void testInsert_OutOfBound() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        ArrayIndexOutOfBoundsException e = assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.insert(4, 17));
    }

    //-- удаление элемента, когда в результате размер буфера остаётся прежним (проверьте также размер буфера);
    //-- удаление элемента, когда в результате понижается размер буфера (проверьте также корректное изменение размера буфера);
    //-- попытка удаления элемента в недопустимой позиции.
    @Test
    void testRemove_NoBufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        array.append(3);
        array.remove(1);
        /*assertEquals(2, array.count);
        assertEquals(16, array.capacity);*/
        assertEquals(1, array.getItem(0));
        assertEquals(3, array.getItem(1));
    }

    @Test
    void testRemove_FromFirstPositionNoBufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        array.append(3);
        array.remove(0);
        /*assertEquals(2, array.count);
        assertEquals(16, array.capacity);*/
        assertEquals(2, array.getItem(0));
        assertEquals(3, array.getItem(1));
    }

    @Test
    void testRemove_FromLastPositionNoBufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        array.append(3);
        array.remove(2);
        /*assertEquals(2, array.count);
        assertEquals(16, array.capacity);*/
        assertEquals(1, array.getItem(0));
        assertEquals(2, array.getItem(1));
    }

    @Test
    void testRemove_BufferResize() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 17; i++) {
            array.append(i);
        }
        /*assertEquals(32, array.capacity);
        assertEquals(17, array.count);*/
        array.remove(16);
        /*assertEquals(32, array.capacity);
        assertEquals(16, array.count);*/
        array.remove(15);
        /*assertEquals(21, array.capacity);
        assertEquals(15, array.count);*/
    }

    @Test
    void testRemove_OutOfBound() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        array.append(1);
        array.append(2);
        ArrayIndexOutOfBoundsException e = assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.remove(17));
    }

    @Test
    void testRemove_OutOfBoundEmptyArray() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        ArrayIndexOutOfBoundsException e = assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> array.remove(17));
    }

    @Test
    void testMultyBufferChange() {
        DynArray<Integer> array = new DynArray<>(Integer.class);
        for (int i = 0; i < 17; i++) {
            array.append(i);
        }
        /*assertEquals(32, array.capacity);
        assertEquals(17, array.count);*/
        array.remove(16);
        /*assertEquals(32, array.capacity);
        assertEquals(16, array.count);*/
        array.remove(15);
        /*assertEquals(21, array.capacity);
        assertEquals(15, array.count);*/
        for (int i = 0; i < 7; i++) {
            array.append(i);
        }
        /*assertEquals(42, array.capacity);
        assertEquals(22, array.count);*/
    }

}