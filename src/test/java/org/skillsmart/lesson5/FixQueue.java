package org.skillsmart.lesson5;

import java.util.*;

public class FixQueue<T> {

    private static final int DEFAULT_SIZE = 16;
    private ArrayList<T> array;
    private int beginIndex;
    private int endIndex;
    private int maxSize;

    public int size;

    public FixQueue() {
        array = new ArrayList<>(DEFAULT_SIZE);
        maxSize = DEFAULT_SIZE;
        size = 0;
        beginIndex = 0;
        endIndex = 0;
    }

    public FixQueue(int queueSize) {
        array = new ArrayList<>(queueSize);
        maxSize = queueSize;
        size = 0;
        beginIndex = 0;
        endIndex = 0;
    }

    public void enqueue(T item)
    {
        if (isFull()) {
            throw new ArrayStoreException("Queue is already full"); //Exception("Queue is already full");
        }
        // вставка в хвост
        // смещаем индекс хвоста
        this.size += 1;
    }

    public T dequeue()
    {
        if (this.size == 0) {
            return null;
        }
        //смещаем индекс головы
        this.size -= 1;
        return array.get(beginIndex);
    }

    public int size()
    {
        return this.size; // размер очереди
    }

    public boolean isFull() {
        return this.size == maxSize;
    }


}
