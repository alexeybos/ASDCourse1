package org.skillsmart.lesson9;

import org.skillsmart.lesson7.OrderedList;

import java.util.Objects;

/**
 * Если в OrderedList храним только key (а его index постоянно меняется из-за добавления/удаления элементов),
 * то хранить value по индексу получается накладно (размещаем key, получаем его индекс, сдвигаем все values "выше" индекса).
 * Остановился на варианте со структурой key-value и расширил компаратор OrderedList на класс KeyValuePair
 * @param <T>
 */
public class OrderedPairDictionary<T> {

    public int size;
    public OrderedList<KeyValuePair<T>> orderedPairs;

    public OrderedPairDictionary(int sz) {
        size = sz;
        orderedPairs = new OrderedList<>(true);
    }

    public boolean isKey(String key)
    {
        return !(find(key) < 0);
    }

    public void put(String key, T value)
    {
        KeyValuePair<T> entry = new KeyValuePair<>(key, value);
        if (orderedPairs.find(entry) == null) {
            orderedPairs.find(entry);
        }
        orderedPairs.delete(entry);
        orderedPairs.add(new KeyValuePair<>(key, value));
        int slot = find(key);
        if (slot == -1) {

            return;
        }
        //values[slot] = value;
    }

    public T get(String key)
    {
        int slot = find(key);
        if (slot == -1) return null;
        //return values[slot];
        return null;
    }

    public void delete(String key) {
        int slot = find(key);
        if (slot == -1) return;
        //values[slot] = null;
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
        /*int slot = hashFun(key);
        for (int i = 0; i <= STEP; i++) {
            for (; slot < size; slot += STEP) {
                if (Objects.equals(slots[slot], seekValue)) return slot;
                if (slots[slot] == null && !wasFilled[slot]) return -1;
            }
            slot -= size;
        }*/
        return -1;
    }
}


