package org.skillsmart.lesson6;

import org.skillsmart.lesson3.DynArray;

public class DequeByTwoDynArray<T> {

    public DynArray<Object> headArray;
    public DynArray<Object> tailArray;

    public int headIndex;
    public int tailIndex;

    public int size;

    public DequeByTwoDynArray() {
        headArray = new DynArray<>(Object.class);
        tailArray = new DynArray<>(Object.class);
        headIndex = -1;
        tailIndex = -1;
        size = 0;
    }

    public void addFront(T item)
    {
        headIndex = addItem(headArray, tailArray, headIndex, item);
    }

    public void addTail(T item)
    {
        tailIndex = addItem(tailArray, headArray, tailIndex, item);
    }

    public T removeFront()
    {
        if (size() == 0) {
            return null;
        }
        T value = removeItem(headArray, tailArray, headIndex);
        headIndex -= 1;
        return value;
    }

    public T removeTail()
    {
        if (size() == 0) {
            return null;
        }
        T value = removeItem(tailArray, headArray, tailIndex);
        tailIndex -= 1;
        return value;
    }

    private int addItem(DynArray<Object> nativeArray, DynArray<Object> alienArray, int index, T item) {
        index += 1;
        if (index < 0) { //находимся в "чужом" массиве, надо вернуть долг
            //alienArray.array[Math.abs(index + 1)] = item;
        } else {
            nativeArray.append(item);
        }
        size += 1;
        return index;
    }

    private T removeItem(DynArray<Object> nativeArray, DynArray<Object> alienArray, int index) {
        size -= 1;
        if (index < 0) { //все что клали в "родной" массив, кончилось, берем с начала "чужого"
            return (T) alienArray.getItem(Math.abs(index + 1));
        }
        T value = (T) nativeArray.getItem(index);
        nativeArray.removeLast();
        return value;
    }

    public int size()
    {
        return this.size;
    }
}
