package org.skillsmart.asd2.lesson9;

public class HeapSort {

    public Heap HeapObject;

    public HeapSort(int[] array) {
        HeapObject = new Heap();
        HeapObject.setDepth(array.length);
        for (int key : array) {
            HeapObject.Add(key);
        }
    }

    public int GetNextMax() {
        return HeapObject.GetMax();
    }
}

class Heap
{
    public int [] HeapArray;
    private int depth;
    private int lastInHeap = -1;

    public Heap() { HeapArray = null; }

    public int GetMax()
    {
        if (lastInHeap == -1) {
            return -1;
        }
        int max = HeapArray[0];
        HeapArray[0] = HeapArray[lastInHeap];
        HeapArray[lastInHeap] = 0;
        lastInHeap--;
        checkChildren(0);
        return max;
    }

    private void checkChildren(int i) {
        int childIndex = (2 * i + 1);
        if (childIndex <= lastInHeap && HeapArray[childIndex] > HeapArray[i]) {
            swap(childIndex, i);
            checkChildren(childIndex);
        }
        childIndex++;
        if (childIndex <= lastInHeap && HeapArray[childIndex] > HeapArray[i]) {
            swap(childIndex, i);
            checkChildren(childIndex);
        }
    }

    public boolean Add(int key)
    {
        if (HeapArray == null) HeapArray = new int[depth];
        lastInHeap++;
        if (lastInHeap > depth) return false;

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

    public void setDepth(int depth) {
        this.depth = depth;
    }

    private void swap(int index1, int index2) {
        int tmpForSwap = HeapArray[index2];
        HeapArray[index2] = HeapArray[index1];
        HeapArray[index1] = tmpForSwap;
    }
}



