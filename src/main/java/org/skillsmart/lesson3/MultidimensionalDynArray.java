package org.skillsmart.lesson3;

import java.lang.reflect.Array;

public class MultidimensionalDynArray<T> {

    private static final int APPEND_MULTIPLIER = 2;
    private static final double REDUCE_MULTIPLIER = 1.5;
    private static final double EMPTY_RATIO = 0.5;

    //public T[] array;
    public Object[] array;
    public int count;
    public int capacity;
    public int dimensions;
    public int[] capacities;
    public int[] counts;
    Class clazz;

    public MultidimensionalDynArray(Class clz)
    {
        clazz = clz;
        count = 0;
        //makeArray(1, INITIAL_CAPACITY);
    }

    public MultidimensionalDynArray(Class clz, int... initialCapacities)
    {
        clazz = clz;
        dimensions = initialCapacities.length;
        array = makeArray(initialCapacities, 0);
        capacities = initialCapacities;
        //array = (T[]) makeArray(initialCapacities, 0);
    }

    public Object[] makeArray(int[] initialCapacities, int dimension) {
        if (dimension == initialCapacities.length - 1) {
            T[] lastDimension = (T[]) Array.newInstance(clazz, initialCapacities[dimension]);
            return lastDimension;
        }
        Object[] currentDimension = (Object[]) Array.newInstance(Object.class, initialCapacities[dimension]);
        //Object[] currentDimension = (Object[]) Array.newInstance(SimpleDynArray.class, initialCapacities[dimension]);
        for (int i = 0; i < initialCapacities[dimension]; i++) {
            currentDimension[i] = makeArray(initialCapacities, dimension + 1);
        }
        return currentDimension;
    }

    public T getItem(int... indices)
    {
        /*if (index.length < count) {
            throw new ArrayIndexOutOfBoundsException();
        }*/
        return (T) getValue(array, 0, indices);
    }

    private Object getValue(Object[] currentArray, int level, int[] indices) {
        if (level == indices.length - 1) {
            return currentArray[indices[level]];
        }
        return getValue((Object[]) currentArray[level + 1], level + 1, indices);
    }

    public void setItem(T value, int... indices) {
        //надо в цикле найти последовательно конечную точку в массиве массивов массивов и т.д...
        setValue(array, value, 0, indices);
        count += 1;
    }

    private void setValue(Object[] currentArray, T value, int level, int[] indices) {
        if (level == indices.length - 1) {
            currentArray[indices[level]] = value;
        } else {
            setValue((Object[]) currentArray[indices[level]], value, level + 1, indices);
        }
    }

}

class SimpleDynArray<T>
{
    private static final int APPEND_MULTIPLIER = 2;
    private static final double REDUCE_MULTIPLIER = 1.5;
    private static final double EMPTY_RATIO = 0.5;
    public T [] array;
    public int count;
    public int capacity;
    public int initialCapacity;
    Class clazz;

    public SimpleDynArray(Class clz, int new_capacity)
    {
        clazz = clz;
        count = 0;
        initialCapacity = new_capacity;
        makeArray(new_capacity);
    }

    public void makeArray(int new_capacity)
    {
        T[] oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        if (count > 0) {
            System.arraycopy(oldArray, 0, array, 0, count);
        }
        capacity = new_capacity;
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
        if (count == capacity) {
            makeArray(capacity * APPEND_MULTIPLIER);
        }
        array[count] = itm;
        count += 1;
    }

    public void insert(T itm, int index)
    {
        if (index < 0 || index > count) {
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
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        shrinkIfNeed();
        for (int i = index; i < count; i++) {
            array[i] = array[i + 1];
        }
        count -= 1;
    }

    private void shrinkIfNeed() {
        if (capacity == initialCapacity) {
            return;
        }
        if (count - 1 < (int) (capacity * EMPTY_RATIO)) {
            makeArray(Math.max((int) (capacity / REDUCE_MULTIPLIER), initialCapacity));
        }
    }

}
