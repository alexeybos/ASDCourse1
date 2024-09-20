package org.skillsmart.lesson3;

import java.lang.reflect.Array;

public class MultidimensionalDynArray<T> {

    /*
        Как-то усложнено, идея, что у нас есть интерфейс добавления элемента в некоторую позицию (i,j,k,...),
        а внутри это может быть обычный одномерный динамический массив. Надо просто корректно реализовать операцию
        добавления элемента (отображение многомерной координаты в линию).
     */

    private static final int APPEND_MULTIPLIER = 2;

    public Object[] array;
    public int count;
    public int dimensions;
    public int[] capacities;
    Class clazz;

    public MultidimensionalDynArray(Class clz, int... initialCapacities)
    {
        clazz = clz;
        dimensions = initialCapacities.length;
        array = makeArray(initialCapacities, 0);
        capacities = initialCapacities;
    }

    public Object[] makeArray(int[] initialCapacities, int dimension) {
        if (dimension == initialCapacities.length - 1) {
            T[] lastDimension = (T[]) Array.newInstance(clazz, initialCapacities[dimension]);
            return lastDimension;
        }
        Object[] currentDimension = (Object[]) Array.newInstance(Object.class, initialCapacities[dimension]);
        for (int i = 0; i < initialCapacities[dimension]; i++) {
            currentDimension[i] = makeArray(initialCapacities, dimension + 1);
        }
        return currentDimension;
    }

    public T getItem(int... indices)
    {
        if (indices.length < dimensions) {
            throw new ArrayStoreException();
        }
        return (T) getValue(array, 0, indices);
    }

    private Object getValue(Object[] currentArray, int level, int[] indices) {
        if (level == indices.length - 1) {
            return currentArray[indices[level]];
        }
        return getValue((Object[]) currentArray[indices[level]], level + 1, indices);
    }

    public void setItem(T value, int... indices) {
        if (indices.length < dimensions) {
            throw new ArrayStoreException();
        }
        resizeArrayIfNeed(indices);
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

    public void resizeArrayIfNeed(int[] indices) {
        boolean isNeedResize = false;
        int[] oldCapacities = capacities.clone();
        for (int i = 0; i < dimensions; i++) {
            if (capacities[i] <= indices[i]) {
                capacities[i] = Math.max(indices[i] + 1, capacities[i] * APPEND_MULTIPLIER);
                isNeedResize = true;
            }
        }
        if (!isNeedResize) {
            return;
        }
        Object[] oldArray = array;
        array = makeArray(capacities, 0);
        copyArray(oldArray, array, 0, oldCapacities);
    }

    private void copyArray(Object[] oldArray, Object[] currentArray, int level, int[] _capacities) {
        if (level == _capacities.length - 1) {
            System.arraycopy(oldArray, 0, currentArray, 0, oldArray.length);
            return;
        }
        copyArray((Object[]) oldArray[_capacities[level] - 1], (Object[]) currentArray[_capacities[level] - 1], level + 1, _capacities);
    }
}

