package org.skillsmart.asd2real.lesson7;

import java.util.*;

class Heap
{
    public int [] HeapArray;
    private int lastInHeap;

    public Heap() {
        HeapArray = null;
        lastInHeap = -1;
    }

    public void MakeHeap(int[] a, int depth)
    {
        int heapSize = (int) Math.pow(2, depth + 1) - 1;
        HeapArray = new int[heapSize];
        for (int key : a) {
            Add(key);
        }
    }

    public int GetMax()
    {
        if (lastInHeap == -1) return -1;
        int max = HeapArray[0];
        HeapArray[0] = HeapArray[lastInHeap];
        HeapArray[lastInHeap] = 0;
        lastInHeap--;

        checkChildren(0);
        return max;
    }

    private void checkChildren(int i) {
        int childIndex = (2 * i + 1);
        //выберем макс из детей
        if (childIndex > lastInHeap) return;
        if (childIndex + 1 <= lastInHeap && HeapArray[childIndex] < HeapArray[childIndex + 1]) {
            childIndex++;
        }
        if (HeapArray[childIndex] > HeapArray[i]) {
            swap(childIndex, i);
            checkChildren(childIndex);
        }
    }

    public boolean Add(int key)
    {
        if (lastInHeap + 1 == HeapArray.length) return false;
        lastInHeap++;
        HeapArray[lastInHeap] = key;
        for (int i = lastInHeap; i > 0; i = (i - 1) / 2) {
            int parentIndex = (i - 1) / 2;
            if (HeapArray[parentIndex] < HeapArray[i]) {
                swap(parentIndex, i);
            } else {
                int childIndex = (2 * i + 1);
                if (childIndex <= lastInHeap && HeapArray[childIndex] > key) {
                    swap(childIndex, i);
                    return true;
                }
                childIndex++;
                if (childIndex <= lastInHeap && HeapArray[childIndex] > key) {
                    swap(childIndex, i);
                    return true;
                }
                return true;
            }
        }
        return true;
    }

    private void swap(int index1, int index2) {
        int tmpForSwap = HeapArray[index2];
        HeapArray[index2] = HeapArray[index1];
        HeapArray[index1] = tmpForSwap;
    }

    //TODO дополнительные задания

    //Сложность: time O(n), память O(depth)
    public boolean isCorrect() {
        if (lastInHeap < 2) return true;
        return isChildrenCorrect(0);
    }

    private boolean isChildrenCorrect(int i) {
        if (i > lastInHeap) return true;
        int nextChild = i * 2 + 1;
        if (nextChild > lastInHeap) return true;
        if (HeapArray[nextChild] > HeapArray[i]) return false;
        boolean checkChild = isChildrenCorrect(nextChild);
        nextChild++;
        if (checkChild && nextChild > lastInHeap) return true;
        if (checkChild && HeapArray[nextChild] < HeapArray[i]) return isChildrenCorrect(nextChild);
        return false;
    }

    //Сложность: time O(n), память O(1)
    public int findMaxInRange(int start, int end) {
        if (lastInHeap == -1) return -1;
        if (HeapArray[0] < start) return -1;
        int max = -1;
        for (int i = 0; i <= lastInHeap; i++) {
            if (HeapArray[i] >= start && HeapArray[i] <= end && HeapArray[i] > max) max = HeapArray[i];
        }
        return max;
    }

    //Сложность: time O(n*logn), память O(depth)
    public void union(Heap heap) {
        for (int i = heap.GetMax(); i != -1; i = heap.GetMax()) {
            Add(i);
        }
    }
}



