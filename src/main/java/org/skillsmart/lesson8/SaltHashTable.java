package org.skillsmart.lesson8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SaltHashTable {

    private static class Salt {
        public String value;
        public String salt;
        public Salt(String _val, String _salt) {
            value = _val;
            salt = _salt;
        }
    }

    private final List<Salt> salt;
    public String [] slots;
    public int size;
    public int step;

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
        salt = new ArrayList<>(size);
    }

    public int hashFun(String value)
    {
        /*byte[] chars = value.getBytes();
        int sum1 = 0;
        int sum2 = 0;
        int i;
        for (i = 0; i < chars.length / 2; i++) {
            sum1 += chars[i];
        }
        for (; i < chars.length; i++) {
            sum2 += chars[i];
        }

        return ((6*sum1 + 12)%41 + (3*sum2 + 5)%7)%size;*/
        byte[] chars = value.getBytes();
        int sum = 0;
        for (byte aChar : chars) {
            sum += aChar;
        }
        return sum%size;
    }

    private String generateSalt(String value) {
        Random rn = new Random();
        String randomSalt = "saltPhrase" + rn.nextInt(10000);
        salt.add(new Salt(value, randomSalt));
        return value + randomSalt;
    }

    public int put(String value)
    {
        if (count == size) {
            return -1;
        }
        if (saltMode) {
            value = generateSalt(value);
        }
        int slot = seekSlot(value);
        if (slot == -1) return -1;
        slots[slot] = value;
        count += 1;
        return slot;
    }

    public int seekSlot(String value)
    {
        return slotCycle(value, null);
    }

    public int find(String value)
    {
        if (saltMode) {
            value = value + findSalt(value);
        }
        return slotCycle(value, value);
    }

    private int slotCycle(String value, String seekValue) {
        int slot = hashFun(value);
        for (int i = 0; i <= step; i++) {
            for (; slot < size; slot += step) {
                if (Objects.equals(slots[slot], seekValue)) return slot;
                if (slots[slot] == null) return -1;
                if (seekValue == null) collisionCount += 1;
            }
            slot -= size;
        }
        return -1;
    }

    private String findSalt(String value) {
        for (Salt item : salt) {
            if (Objects.equals(item.value, value)) {
                return item.salt;
            }
        }
        return null;
    }
}
