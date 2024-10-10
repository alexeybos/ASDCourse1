package org.skillsmart.lesson8;

import org.skillsmart.lesson3.DynArray;

public class DynHashTable {

    public DynArray<String> slots;
    public int count;
    public int step;

    public DynHashTable(int stp) {
        step = stp;
        slots = new DynArray<>(String.class);
        count = 0;
    }

    public int hashFun(String value)
    {
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return sum%getSize();
    }

    public int seekSlot(String value)
    {
        int slot = hashFun(value);
        for (int i = 0; i <= step; i++) {
            for (; slot < getSize(); slot += step) {
                if (slots.getItem(slot) == null) return slot;
            }
            slot -= getSize();
        }
        return -1;
    }

    public int put(String value)
    {
        if ((double) slots.getBufferSize() / (count + slots.getBufferSize()) < 0.2) { //надо расширять и пересчитывать хеши
            slots.expandArray();
            String[] tempValue = new String[count];
            for (int i = 0; i < count; i++) {
                tempValue[i] = slots.getItem(i);
                slots.put(null, i);
            }
            //перехешируем
            for (int i = 0; i < count; i++) slots.put(tempValue[i], seekSlot(tempValue[i]));
        }
        int slot = seekSlot(value);
        if (slot == -1) return -1;
        slots.put(value, slot);
        count += 1;
        return slot;
    }

    public int getSize() {
        return count + slots.getBufferSize();
    }
}
