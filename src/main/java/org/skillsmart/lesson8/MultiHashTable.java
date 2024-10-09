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
        return prepareValue(value) % size;
    }

    public int hashFun1(String value)
    {
        return ((12 * prepareValue(value) + 29)%43) % size;
    }

    public int hashFun2(String value)
    {
        return ((3 * prepareValue(value) + 5)%7) % size;
    }

    private int prepareValue(String value) {
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return sum;
    }

    public int seekSlot(String value)
    {
        return slotCycle(value, null);
    }

    public int find(String value)
    {
        return slotCycle(value, value);
    }

    public int slotCycle(String value, String lookingFor) {
        int slot = -1;
        for (Function<String, Integer> hashFunction : hashFunctions) {
            slot = hashFunction.apply(value);
            if (Objects.equals(slots[slot], lookingFor)) return slot;
        }
        for (int i = 0; i <= step; i++) {
            for (; slot < size; slot += step) {
                if (Objects.equals(slots[slot], lookingFor)) return slot;
                if (slots[slot] == null) return -1;
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
}
