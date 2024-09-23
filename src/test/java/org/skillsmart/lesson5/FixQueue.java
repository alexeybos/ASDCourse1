package org.skillsmart.lesson5;

import java.lang.reflect.Array;
import java.util.*;

public class FixQueue<T> {

    private static final int DEFAULT_SIZE = 16;
    private final int maxSize;
    private final T [] array;
    private int headIndex;
    private int tailIndex;

    public int size;

    public FixQueue() {
        this(DEFAULT_SIZE);
    }

    public FixQueue(int queueSize) {
        array = (T[]) Array.newInstance(Object.class, queueSize);
        maxSize = queueSize;
        size = 0;
        headIndex = 0;
        tailIndex = 0;
    }

    public void enqueue(T item)
    {
        if (isFull()) {
            throw new ArrayStoreException("Queue is already full"); //Exception("Queue is already full");
        }
        array[tailIndex] = item;
        tailIndex = changeIndex(tailIndex);
        this.size += 1;
    }

    public T dequeue()
    {
        if (this.size == 0) {
            return null;
        }
        T returnValue = array[headIndex];
        headIndex = changeIndex(headIndex);
        this.size -= 1;
        return returnValue;
    }

    public int size()
    {
        return this.size;
    }

    public boolean isFull() {
        return this.size == maxSize;
    }

    private int changeIndex(int _index) {
        if (_index == maxSize - 1) {
            return 0;
        }
        return _index + 1;
    }

}
