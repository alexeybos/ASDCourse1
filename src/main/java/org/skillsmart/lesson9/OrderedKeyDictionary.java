package org.skillsmart.lesson9;

import org.skillsmart.lesson7.OrderedList;

import java.util.Objects;

public class OrderedKeyDictionary<T> {

    private static final int STEP = 3;
    public int size;
    public String [] slots;
    public T [] values;
    public OrderedList<T> orderedKeys;

    public OrderedKeyDictionary() {
        orderedKeys = new OrderedList<>(true);
    }

    public int hashFun(String key)
    {
        byte[] chars = key.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return sum%size;
    }

    public boolean isKey(String key)
    {
        return !(find(key) < 0);
    }

    public void put(String key, T value)
    {
        int slot = find(key);
        if (slot == -1) {
            int newSlot = seekSlot(key);
            slots[newSlot] = key;
            values[newSlot] = value;
            return;
        }
        values[slot] = value;
    }

    public T get(String key)
    {
        int slot = find(key);
        if (slot == -1) return null;
        return values[slot];
    }

    private int seekSlot(String key)
    {
        return dictCycle(key, null);
    }
    public int find(String key)
    {
        return dictCycle(key, key);
    }

    private int dictCycle(String key, String seekValue) {
        int slot = hashFun(key);
        for (int i = 0; i <= STEP; i++) {
            for (; slot < size; slot += STEP) {
                if (Objects.equals(slots[slot], seekValue)) return slot;
                if (slots[slot] == null) return -1;
            }
            slot -= size;
        }
        return -1;
    }
}
