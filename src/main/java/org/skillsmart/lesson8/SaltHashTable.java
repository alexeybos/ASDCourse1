package org.skillsmart.lesson8;

import java.util.Objects;

public class SaltHashTable {


    public int size;
    public int step;
    public String [] slots;
    public int count;
    public int collisionCount;
    boolean saltMode;


    public SaltHashTable(int sz, int stp, boolean _mode) {
        size = sz;
        step = stp;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
        count = 0;
        collisionCount = 0;
        saltMode = _mode;
        //saltPhrase = "saltPhrase" + (new Random()).nextInt(10000);
    }

    public int hashFun(String value)
    {
        //соль статическая, т.к. я не придумал, как эффективно хранить соль для value - получается усложнение
        //структуры, которая "съедает" все преимущества HashTable
        if (saltMode) {
            value = "salt" + value + "Phrase";
        }
        byte[] chars = value.getBytes();
        int sum1 = 0;
        int sum2 = 0;
        int i;
        for (i = 0; i < chars.length / 2; i++) {
            sum1 += chars[i];
        }
        for (; i < chars.length; i++) {
            sum2 += chars[i];
        }

        return ((6*sum1 + 12)%41 + (3*sum2 + 5)%7)%size;
    }

    public int seekSlot(String value)
    {
        int slot = hashFun(value);
        for (int i = 0; i <= step; i++) {
            for (; slot < size; slot += step) {
                if (slots[slot] == null) return slot;
                collisionCount += 1;
            }
            slot -= size;
        }
        return -1;
    }

    public int put(String value)
    {
        if (count == size) {
            return -1;
        }
        int slot = seekSlot(value);
        if (slot == -1) return -1;
        slots[slot] = value;
        count += 1;
        return slot;
    }

    public int find(String value)
    {
        int slot = hashFun(value);
        for (int i = 0; i <= step; i++) {
            for (; slot < size; slot += step) {
                if (Objects.equals(slots[slot], value)) return slot;
                if (slots[slot] == null) return -1;
            }
            slot -= size;
        }
        return -1;
    }
}
