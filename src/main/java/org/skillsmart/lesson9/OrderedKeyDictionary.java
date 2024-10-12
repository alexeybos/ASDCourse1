package org.skillsmart.lesson9;

import org.skillsmart.lesson7.OrderedList;

import java.lang.reflect.Array;

public class OrderedKeyDictionary<T> {

    public int size;
    public OrderedList<String> orderedKeys;
    public T[] values;

    public OrderedKeyDictionary(int sz, Class clazz) {
        size = sz;
        orderedKeys = new OrderedList<>(true);
        values = (T[]) Array.newInstance(clazz, sz);
    }

    public boolean isKey(String key)
    {
        return orderedKeys.getIndex(key) != -1;
    }

    public void put(String key, T value)
    {
        int slot = orderedKeys.getIndex(key);
        if (slot != -1) { //key уже есть, просто меняем value
            values[slot] = value;
            return;
        }
        //(размещаем key, получаем его индекс, сдвигаем все values "выше" индекса).
        orderedKeys.add(key);
        slot = orderedKeys.getIndex(key);
        for (int i = slot; i < orderedKeys.count(); i++) {
            values[slot + 1] = values[slot];
        }
        values[slot] = value;
    }

    public T get(String key)
    {
        int slot = orderedKeys.getIndex(key);
        if (slot == -1) return null;
        return values[slot];
    }

    public void delete(String key) {
        //находим индекс, штатно удаляем key, сдвигаем все values "вниз"
        int slot = orderedKeys.getIndex(key);
        if (slot == -1) return;
        orderedKeys.delete(key);
        T descentValue = null;
        for (int i = orderedKeys.count(); i >= slot; i--) {
            T temp = values[i];
            values[i] = descentValue;
            descentValue = temp;
        }
    }
}

