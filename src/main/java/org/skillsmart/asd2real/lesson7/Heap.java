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
        if (nextChild + 1 > lastInHeap) return true;
        if (HeapArray[nextChild + 1] > HeapArray[i]) return false;
        if (isChildrenCorrect(nextChild)) return isChildrenCorrect(nextChild + 1);
        return false;
    }

    //Сложность: time O(n), память O(1)
    public Integer findMaxInRange(Integer start, Integer end) {
        if (lastInHeap == -1) return null;
        if (end == null) return HeapArray[0];
        if (start != null && HeapArray[0] < start) return null;
        Integer max = null;
        for (int i = 0; i <= lastInHeap; i++) {
            if ((start == null || HeapArray[i] >= start)
                    && HeapArray[i] <= end && (max == null || HeapArray[i] > max)) max = HeapArray[i];
        }
        return max;
    }

    //Сложность c GetMax (с разрушением кучи-параметра): time O(n*logn), память O(depth)
    //Сложность c findMaxInRange (без разрушения): time O(n^2), память O(depth)
    public void union(Heap heap) {
        for (Integer max = heap.peek(); max != null; max = heap.findMaxInRange(null, max - 1)) {
            Add(max);
        }
    }

    public int peek() {
        if (lastInHeap == -1) return -1;
        return HeapArray[0];
    }
}



