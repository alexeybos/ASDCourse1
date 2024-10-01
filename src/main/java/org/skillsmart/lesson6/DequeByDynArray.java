package org.skillsmart.lesson6;

import org.skillsmart.lesson3.DynArray;

import java.lang.reflect.Array;

public class DequeByDynArray<T> {

    DynArray<T> array;
    public int size;
    public int headIndex;
    public int tailIndex;

    public DequeByDynArray() {
        array = new DynArray<>(Object.class);
        headIndex = 0;
        tailIndex = 0;
    }

    public void addFront(T item)
    {
        if (size == 0) { //будет первый элемент
            addIntoEmptyArray(item);
            return;
        }
        size += 1;
        if (headIndex + 1 == tailIndex) {
            prepareToExpandArray();
            headIndex = array.capacity;
            tailIndex = 0;
            array.append(item);
            return;
        }
        headIndex += 1;
        if (headIndex == array.capacity) {
            array.append(item);
            return;
        }
        array.array[headIndex] = item;
        array.count += 1;
    }

    public void addTail(T item)
    {
        if (size == 0) { //будет первый элемент
            addIntoEmptyArray(item);
            return;
        }
        size += 1;
        if (headIndex == tailIndex - 1) {
            prepareToExpandArray();
            headIndex = array.capacity;
            tailIndex = 0;
            //"раздвигаем" массив штатным образом.
            array.insert(item, 0);
            return;
        }
        tailIndex -= 1;
        if (tailIndex < 0) {
            tailIndex = array.capacity - 1;
        }
        array.array[tailIndex] = item;
        array.count += 1;
    }

    private void addIntoEmptyArray(T item) {
        headIndex = 0;
        tailIndex = 0;
        array.array[headIndex] = item;
        array.count += 1;
        size += 1;
    }

    private void prepareToExpandArray() {
        //надо "распрямить" кольцо
        if (headIndex < tailIndex) {
            T [] tempArray = (T[]) Array.newInstance(Object.class, array.capacity);
            for (int i = 0; i < array.capacity; i++) {
                int realTailIndex = tailIndex + i;
                if (realTailIndex == array.capacity) {
                    realTailIndex = 0;
                }
                tempArray[i] = array.array[realTailIndex];
            }
            System.arraycopy(tempArray, 0, array.array, 0, array.capacity);
        }
    }

    public T removeFront()
    {
        if (size == 0) {
            return null;
        }
        T item = array.getItem(headIndex);
        size -= 1;
        if (size != 0) {
            headIndex -= 1;
            if (headIndex < 0) {
                headIndex = array.capacity - 1;
            }
        }
        return item;
    }

    public T removeTail()
    {
        if (size == 0) {
            return null;
        }
        T item = array.getItem(tailIndex);
        size -= 1;
        if (size != 0) {
            tailIndex += 1;
            if (tailIndex == array.capacity) {
                tailIndex = 0;
            }
        }
        return item;
    }

    public int size()
    {
        return this.size;
    }
}
