package org.skillsmart.lesson8;

import org.skillsmart.lesson3.DynArray;

public class DynHashTable {

    private static final double LOAD_RATIO = 0.75;
    public DynArray<String> slots;
    public int count;
    public int step;
    public int size;

    public DynHashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new DynArray<>(String.class);
        for(int i=0; i < slots.capacity; i++) slots.array[i] = null;
        count = 0;
    }

    public int hashFun(String value)
    {
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return sum%slots.capacity;
    }

    public int seekSlot(String value)
    {
        int slot = hashFun(value);
        for (int i = 0; i <= step; i++) {
            for (; slot < slots.capacity; slot += step) {
                if (slots.array[slot] == null) return slot;
            }
            slot -= slots.capacity;
        }
        return -1;
    }

    public int put(String value)
    {
        if ((double) count / slots.capacity > LOAD_RATIO) {
            expandArray();
        }
        int slot = seekSlot(value);
        if (slot == -1) return -1;
        slots.array[slot] = value;
        slots.count += 1;
        count += 1;
        return slot;
    }

    private void expandArray() {
        String [] tempArray = slots.array;
        slots.makeArray(slots.capacity * 2, true);
        count = 0;
        for (String s : tempArray) {
            if (s != null) put(s);
        }
    }
}
