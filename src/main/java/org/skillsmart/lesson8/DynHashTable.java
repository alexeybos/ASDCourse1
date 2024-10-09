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
        slots = new DynArray<>(String.class, LOAD_RATIO, this::copyFunction);
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
        int slot = seekSlot(value);
        if (slot == -1) return -1;
        slots.put(value, slot);
        count += 1;
        return slot;
    }

    private void copyFunction(String[] oldArray) {
        count = 0;
        for (String s : oldArray) {
            if (s != null) put(s);
        }
    }
}
