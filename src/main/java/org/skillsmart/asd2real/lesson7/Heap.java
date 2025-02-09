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

    private Heap(Heap heap) {
        HeapArray = new int [heap.HeapArray.length];
        System.arraycopy(heap.HeapArray, 0, this.HeapArray, 0, heap.HeapArray.length);
        lastInHeap = heap.getLastInHeap();
    }

    public int getLastInHeap() {
        return lastInHeap;
    }

    public Heap copy() {
        return new Heap(this);
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
        int childToCheckInd = (2 * i + 1);
        if (childToCheckInd > lastInHeap) return;
        if (childToCheckInd + 1 <= lastInHeap && HeapArray[childToCheckInd] < HeapArray[childToCheckInd + 1]) {
            childToCheckInd++;
        }
        if (HeapArray[childToCheckInd] > HeapArray[i]) {
            swap(childToCheckInd, i);
            checkChildren(childToCheckInd);
        }
    }

    public boolean Add(int key)
    {
        if (lastInHeap + 1 == HeapArray.length) return false;
        lastInHeap++;
        HeapArray[lastInHeap] = key;
        for (int i = lastInHeap; i > 0; i = (i - 1) / 2) {
            int parentIndex = (i - 1) / 2;
            int leftChildIndex = (2 * i + 1);
            if (HeapArray[parentIndex] >= HeapArray[i] && isChildGreater(leftChildIndex, key)) {
                swap(leftChildIndex, i);
                return true;
            }
            int rightChildIndex = leftChildIndex + 1;
            if (HeapArray[parentIndex] >= HeapArray[i] && isChildGreater(rightChildIndex, key)) {
                swap(rightChildIndex, i);
                return true;
            }
            if (HeapArray[parentIndex] >= HeapArray[i]) return true;
            swap(parentIndex, i);
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
        if (lastInHeap < 1) return true;
        return isChildrenCorrect(0);
    }

    private boolean isChildrenCorrect(int i) {
        if (i > lastInHeap) return true;
        int leftChildInd = i * 2 + 1;
        int rightChildInd = leftChildInd + 1;
        if (isChildGreater(leftChildInd, HeapArray[i]) || isChildGreater(rightChildInd, HeapArray[i])) return false;

        if (isChildrenCorrect(leftChildInd)) return isChildrenCorrect(rightChildInd);
        return false;
    }

    private boolean isChildGreater(int childInd, int key) {
        if (childInd > lastInHeap) return false;
        return HeapArray[childInd] > key;
    }

    //Сложность: time O(n), память O(1)
    public Integer findMaxInRange(int start, int end) {
        if (lastInHeap == -1 || HeapArray[0] < start) return null;
        Integer max = null;
        for (int i = 0; i <= lastInHeap; i++) {
            int el = HeapArray[i];
            if (el >= start && el <= end && (max == null || el > max)) max = (el);
        }
        return max;
    }

    public ArrayList<Integer> findMaxInRangeWithOutNulls(int start, int end) {
        Stack<Integer> max = new Stack<>();
        if (lastInHeap == -1 || HeapArray[0] < start) return new ArrayList<>();
        for (int i = 0; i <= lastInHeap; i++) {
            int el = HeapArray[i];
            if (el >= start && el <= end && (max.empty() || el > max.peek())) max.push(el);
        }
        if (max.empty()) return new ArrayList<>();
        return new ArrayList<>(Collections.singletonList(max.pop()));
    }

    public ArrayList<Integer> findMaxInRangeWithOutNullsByArr(int start, int end) {
            ArrayList<Integer> max = new ArrayList<>();
            if (lastInHeap == -1 || HeapArray[0] < start) return new ArrayList<>();
            for (int i = 0; i <= lastInHeap; i++) {
                int el = HeapArray[i];
                if (el >= start && el <= end && (max.isEmpty() || el > max.getFirst())) max.addFirst(el);
            }
            if (max.isEmpty()) return max;
            return new ArrayList<>(max.subList(0, 1));
    }

    //Сложность: time O(n*logn), память O(depth)
    public void union(Heap heap) {
        Heap tmpHeap = heap.copy();
        for (int el = tmpHeap.GetMax(); el != -1; el = tmpHeap.GetMax()) {
            Add(el);
        }
    }

    public int peek() {
        if (lastInHeap == -1) return -1;
        return HeapArray[0];
    }
}



