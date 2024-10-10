package org.skillsmart.lesson8;

import org.skillsmart.lesson3.DynArray;

public class DynHashTable {

    public DynArray<String> slots;
    public int count;
    public int step;
    public int size;

    public DynHashTable(int stp) {
        step = stp;
        slots = new DynArray<>(String.class);
        size = 16; //т.к. массив создается фиксированного начального размера 16
        count = 0;
    }

    public int hashFun(String value)
    {
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return sum%size;
    }

    public int seekSlot(String value)
    {
        int slot = hashFun(value);
        for (int i = 0; i <= step; i++) {
            for (; slot < size; slot += step) {
                if (slots.getItem(slot) == null) return slot;
            }
            slot -= size;
        }
        return -1;
    }

    public int put(String value)
    {
        if (count == size) { //сейчас массив расширится
            String[] tempValue = new String[size + 1];
            slots.append(value);
            for (int i = 0; i < size + 1; i++) {
                tempValue[i] = slots.getItem(i);
                slots.put(null, i);
            }
            //перехешируем
            size = size * 2;
            for (int i = 0; i < count; i++) slots.put(tempValue[i], seekSlot(tempValue[i]));
        }
        int slot = seekSlot(value);
        if (slot == -1) return -1;
        slots.put(value, slot);
        count += 1;
        return slot;
    }
}
