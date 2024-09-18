package org.skillsmart.lesson3;

import java.lang.reflect.Array;

public class BankDynArray<T> {

    private static final int INITIAL_CAPACITY = 16;
    private static final int APPEND_MULTIPLIER = 2;
    private static final double REDUCE_MULTIPLIER = 1.5;
    private static final double EMPTY_RATIO = 0.5;

    public int savedCost = 0;
    public int increaseCost = 0;
    public int shrinkCost = 0;
    public T [] array;
    public int count;
    public int capacity;

    Class clazz;

    public BankDynArray(Class clz)
    {
        clazz = clz;
        count = 0;
        makeArray(INITIAL_CAPACITY);
    }

    public void makeArray(int new_capacity)
    {
        T[] oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        if (count > 0) {
            System.arraycopy(oldArray, 0, array, 0, count);
        }
        capacity = new_capacity;
        //установка цены следующего изменения массива
        //тут я возможно не совсем понял принцип выбора цены изменения массива
        increaseCost = (int) Math.pow(2, (int) (Math.log(capacity * APPEND_MULTIPLIER) / Math.log(2)));
        shrinkCost = (int) Math.pow(2, (int) (Math.log(capacity / REDUCE_MULTIPLIER) / Math.log(2)));
    }

    public T getItem(int index)
    {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    public void append(T itm)
    {
        if (!increaseIfNeed(false)) {
            savedCost += 2;
        }
        array[count] = itm;
        count += 1;

    }

    public void insert(T itm, int index)
    {
        if (index < 0 || index > count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (!increaseIfNeed(index == capacity)) {
            savedCost += 2;
        }
        for (int i = count; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = itm;
        count += 1;
    }

    private boolean increaseIfNeed(boolean forceIncrease) {
        if (forceIncrease || count == capacity || savedCost >= increaseCost) {
            savedCost -= increaseCost;
            makeArray(capacity * APPEND_MULTIPLIER);
            return true;
        }
        return false;
    }

    public void remove(int index)
    {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i < count; i++) {
            array[i] = array[i + 1];
        }
        count -= 1;
        if (!shrinkIfNeed()) {
            savedCost += 2;
        }
    }

    private boolean shrinkIfNeed() {
        if (capacity == INITIAL_CAPACITY) {
            return false;
        }
        if (count < (int) (capacity * EMPTY_RATIO) && savedCost >= shrinkCost) {
            savedCost -= shrinkCost;
            makeArray(Math.max((int) (capacity / REDUCE_MULTIPLIER), INITIAL_CAPACITY));
            return true;
        }
        return false;
    }
}
