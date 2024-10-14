package org.skillsmart.lesson10;

import java.util.*;

public class GenericPowerSet<T> {

    public Map<T, Object> innerSet;

    public GenericPowerSet()
    {
        innerSet = new HashMap<>(20000);
    }

    public int size()
    {
        return innerSet.size();
    }


    public void put(T value)
    {
        innerSet.put(value, 0);
    }

    public boolean get(T value)
    {
        return innerSet.containsKey(value);
    }

    public boolean remove(T value)
    {
        return innerSet.remove(value) != null;
    }

    public GenericPowerSet<List<T>> cartesianProduct(GenericPowerSet<T> set2) {
        GenericPowerSet<List<T>> result = new GenericPowerSet<>();
        for (T elem1 :
                this.innerSet.keySet()) {
            for (T elem2 :
                    set2.innerSet.keySet()) {
                result.put(new ArrayList<>(Arrays.asList(elem1, elem2)));
            }
        }
        return result;
    }

    public GenericPowerSet<T> intersection(GenericPowerSet<T> set2)
    {
        // пересечение текущего множества и set2
        return null;
    }

    public GenericPowerSet<T> union(GenericPowerSet<T> set2)
    {
        // объединение текущего множества и set2
        return null;
    }

    public GenericPowerSet<T> difference(GenericPowerSet<T> set2)
    {
        // разница текущего множества и set2
        return null;
    }

    public boolean isSubset(GenericPowerSet<T> set2)
    {
        // возвращает true, если set2 есть
        // подмножество текущего множества,
        // иначе false
        return false;
    }

    public boolean equals(GenericPowerSet<T> set2)
    {
        // возвращает true,
        // если set2 равно текущему множеству,
        // иначе false
        return false;
    }
}
