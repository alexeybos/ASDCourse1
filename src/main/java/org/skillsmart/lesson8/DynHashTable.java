package org.skillsmart.lesson8;

import org.skillsmart.lesson3.DynArray;

public class DynHashTable {

    public DynArray<String> slots;
    public int count;
    public int step;

    public DynHashTable(int stp) {
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
        if (count == slots.capacity) { //сейчас массив расширится
            String[] tempValue = slots.array;
            slots.append(value);
            for (int i = 0; i < count + 1; i++) slots.array[i] = null;
            slots.count = 0;
            //перехешируем
            for (int i = 0; i < count; i++) slots.put(tempValue[i], seekSlot(tempValue[i]));
        }
        int slot = seekSlot(value);
        if (slot == -1) return -1;
        slots.put(value, slot);
        count += 1;
        return slot;
    }
}
