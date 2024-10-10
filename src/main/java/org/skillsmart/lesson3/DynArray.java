package org.skillsmart.lesson3;

import java.lang.reflect.Array;
import java.util.function.Consumer;

public class DynArray<T>
{
    private static final int INITIAL_CAPACITY = 16;
    private static final int APPEND_MULTIPLIER = 2;
    private static final double REDUCE_MULTIPLIER = 1.5;
    private static final double EMPTY_RATIO = 0.5;
    private double loadRatio;
    private Consumer<T[]> copyFunction;
    private T [] array;
    private int count;
    private int capacity;

    Class clazz;

    public DynArray(Class clz)
    {
        clazz = clz;
        count = 0;
        loadRatio = 0;
        copyFunction = this::defaultCopyFunction;
        makeArray(INITIAL_CAPACITY);
    }

    public DynArray(Class clz, double _loadRatio, Consumer<T[]> cpFunction)
    {
        clazz = clz;
        count = 0;
        loadRatio = _loadRatio;
        copyFunction = cpFunction;
        makeArray(INITIAL_CAPACITY);
    }

    public void makeArray(int new_capacity)
    {
        T[] oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        if (count > 0) {
            copyFunction.accept(oldArray);
        }
        capacity = new_capacity;
    }

    private void defaultCopyFunction(T[] oldArray) {
        System.arraycopy(oldArray, 0, array, 0, count);
    }

    public T getItem(int index)
    {
        if (index < 0 || index >= capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    public void append(T itm)
    {
        if (count == capacity) {
            makeArray(capacity * APPEND_MULTIPLIER);
        }
        array[count] = itm;
        count += 1;
    }

    public void put(T itm, int index) {
        if (index < 0 || index > capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == capacity || count == capacity) {
            makeArray(capacity * APPEND_MULTIPLIER);
        }
        if (loadRatio > 0 && (double) count / capacity > loadRatio) makeArray(capacity * APPEND_MULTIPLIER);
        if (array[index] == null && itm != null) count += 1;
        if (array[index] != null && itm == null) count -= 1;
        array[index] = itm;
    }

    public void insert(T itm, int index)
    {
        if (index < 0 || index > capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == capacity || count == capacity) {
            makeArray(capacity * APPEND_MULTIPLIER);
        }
        for (int i = count; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = itm;
        count += 1;
    }

    public void remove(int index)
    {
        if (index < 0 || index >= capacity) {
            throw new ArrayIndexOutOfBoundsException();
        }
        shrinkIfNeed();
        for (int i = index; i < count; i++) {
            array[i] = array[i + 1];
        }
        count -= 1;
    }

    public void removeLast()
    {
        if (count == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        shrinkIfNeed();
        count -= 1;
    }

    public int getBufferSize() {
        return capacity - count;
    }

    public void expandArray() {
        makeArray(capacity * APPEND_MULTIPLIER);
    }

    private void shrinkIfNeed() {
        if (capacity == INITIAL_CAPACITY) {
            return;
        }
        if (count - 1 < (int) (capacity * EMPTY_RATIO)) {
            makeArray(Math.max((int) (capacity / REDUCE_MULTIPLIER), INITIAL_CAPACITY));
        }
    }

}



