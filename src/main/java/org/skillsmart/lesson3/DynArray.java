package org.skillsmart.lesson3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynArray<T>
{
    private static final int appendMultiplier = 2;
    private static final double reduceMultiplier = 1.5;
    public T [] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz)
    {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity)
    {
        T[] oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        //TODO Надо ли проверять что new_capacity > count? в случае reduce Иначе тут поплохеет, метод-то публичный...
        if (count > 0) {
            System.arraycopy(oldArray, 0, array, 0, count);
        }
        capacity = new_capacity;
    }

    public T getItem(int index)
    {
        if (index < 0 && index >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    public void append(T itm)
    {
        if (count == capacity) {
            makeArray(capacity * appendMultiplier);
        }
        array[count] = itm;
        count += 1;
    }

    public void insert(T itm, int index)
    {
        if (index < 0 && index > count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == capacity) {
            makeArray(capacity * appendMultiplier);
            array[index] = itm;
        }
        for (int i = index; i < count; i++) {
            array[i+1] = array[i];
        }
        array[index] = itm;
        count += 1;
    }

    public void remove(int index)
    {
        // ваш код
    }

}



