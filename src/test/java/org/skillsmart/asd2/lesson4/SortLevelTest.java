package org.skillsmart.asd2.lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortLevelTest {

    @Test
    void arrayChunk() {
        // 0. В переменную N заносим опорное значение, которое хранится в массиве по индексу, равному (длина массива / 2),
        // где / -- целочисленное деление (7/2=3). Запоминаем также индекс опорного элемента.
        //1. В переменную i1 заносим 0 (индекс первого элемента), а в переменную i2 - длину массива M минус один (индекс последнего элемента).
        //2. Пока значение M[ i1 ] меньше N , увеличиваем i1 на 1.
        //3. Пока значение M[ i2 ] больше N , уменьшаем i2 на 1.
        //4. Если i1 = i2 - 1 и M[i1] > M[i2], то меняем M[i1] и M[i2] местами, и возвращаемся к п. 0.
        //5. Если i1 = i2 или (i1 = i2 - 1 и M[i1] < M[i2]), то возвращаем индекс финальной позиции опорного элемента, и прекращаем работу.
        //6. К данному пункту M[i1] хранит элемент, больший или равный опорному, а M[i2] хранит элемент, меньший или равный опорному.
        // Меняем их местами (если один из них -- опорный элемент, надо обновить его новый индекс), и переходим к п.2.
        //[7,5,4,3,1,2]
        //N=3, Ni=3
        //i1=0, i2=5
        //a[0]>N. i1=0
        //a[5]<N. i2=5
        //a[0] <=> a[5] => [2,5,4,3,1,7]
        //i1 => 1
        //i2 => 4
        //a[1] <=> a[4] => [2,1,4,3,5,7]
        //i1 => 2
        //i2 => 3
        // i1 == i2 -1; a[2]=4 > a[3]=3 => a[2] <=> a[3] => [2,1,3,4,5,7]
        // N=4, Ni=3
        //i1=0, i2=5
        //i1 => 3
        //i2 => 3
        // return 3

        //[7,5,6,4,3,1,2]
        //N=4, Ni=3
        //i1=0, i2=6
        //a[0]>N. i1=0
        //a[6]<N. i2=6
        //4. no
        //5. no
        //a[0] <=> a[6] => [2,5,6,4,3,1,7]
        //i1 => 1
        //i2 => 5
        //a[1] <=> a[5] => [2,1,6,4,3,5,7]
        //i1 => 2
        //i2 => 4
        //a[2] <=> a[4] => [2,1,3,4,6,5,7]
        //i1 => 3
        //i2 => 3
        //return 3
        int[] array1 = {7,5,6,4,3,1,2};
        int res = SortLevel.ArrayChunk(array1);
        assertEquals(3, res);
        assertEquals(2, array1[0]);
        assertEquals(1, array1[1]);
        assertEquals(3, array1[2]);
        assertEquals(4, array1[3]);
        assertEquals(6, array1[4]);
        assertEquals(5, array1[5]);
        assertEquals(7, array1[6]);

        int[] array2 = {7,5,4,3,1,2};
        res = SortLevel.ArrayChunk(array2);
        assertEquals(3, res);
        assertEquals(2, array2[0]);
        assertEquals(1, array2[1]);
        assertEquals(3, array2[2]);
        assertEquals(4, array2[3]);
        assertEquals(5, array2[4]);
        assertEquals(7, array2[5]);
    }
}