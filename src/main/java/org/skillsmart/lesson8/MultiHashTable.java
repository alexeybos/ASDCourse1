package org.skillsmart.lesson8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class MultiHashTable {

    private final List<Function<String, Integer>> hashFunctions = new ArrayList<>(3);
    public int size;
    public int step;
    public String [] slots;

    public int count;


    public MultiHashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
        count = 0;
        hashFunctions.add(this::hashFun0);
        hashFunctions.add(this::hashFun1);
        hashFunctions.add(this::hashFun2);
    }

    public int hashFun0(String value)
    {
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return sum%size;
    }

    public int hashFun1(String value)
    {
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return ((12 * sum + 29)%43) % size;
    }

    public int hashFun2(String value)
    {
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return ((3 * sum + 5)%7) % size;
    }

    public int seekSlot(String value)
    {
        int slot = -1;
        //пробуем разными хэшами
        for (int i = 0; i < hashFunctions.size(); i++) {
            slot = hashFunctions.get(i).apply(value);
            if (slots[slot] == null) return slot;
        }
        //после третьего шагаем по таблице?
        for (int i = 0; i <= step; i++) {
            for (; slot < size; slot += step) {
                if (slots[slot] == null) return slot;
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
        int slot = -1;
        for (int i = 0; i < hashFunctions.size(); i++) {
            slot = hashFunctions.get(i).apply(value);
            if (Objects.equals(slots[slot], value)) return slot;
        }
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
