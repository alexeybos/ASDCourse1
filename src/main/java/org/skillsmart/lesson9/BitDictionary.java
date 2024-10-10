package org.skillsmart.lesson9;

import java.lang.reflect.Array;
import java.util.Objects;

class BitDictionary<T>
{
    private static final int STEP = 3;
    public int size;
    public int keySize;
    public String [] slots;
    public T [] values;

    public BitDictionary(int sz, int keySz, Class clazz)
    {
        size = sz;
        keySize =keySz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key)
    {
        int iKey = Integer.parseInt(key, 2);
        return iKey & (size- 1);
    }

    public boolean isKey(String key)
    {
        return !(find(key) < 0);
    }

    public void put(String key, T value)
    {
        if (isKeySizeNotValid(key)) {
            throw new IllegalArgumentException("Key size should be " + keySize);
        }
        int slot = find(key);
        if (slot == -1) {
            int newSlot = seekSlot(key);
            slots[newSlot] = key;
            values[newSlot] = value;
            return;
        }
        values[slot] = value;
    }

    private boolean isKeySizeNotValid(String key) {
        return key.length() != keySize;
    }

    public T get(String key)
    {
        if (isKeySizeNotValid(key)) {
            return null;
        }
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



