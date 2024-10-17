package org.skillsmart.lesson10;

import java.util.*;

public class GenericPowerSet<T> {

    //public Map<T, Object> innerSet;
    public List<T> innerSet;

    public GenericPowerSet()
    {
        innerSet = new ArrayList<>();
    }

    public int size()
    {
        return innerSet.size();
    }


    public void put(T value)
    {
        if (!innerSet.contains(value)) innerSet.add(value);
    }

    public boolean get(T value)
    {
        return innerSet.contains(value);
    }

    public boolean remove(T value)
    {
        return innerSet.remove(value);
    }

    //List<T> = список из двух элементов (пара соответствий)
    //а по рекомендации С.И. лучше вообще Map использовать, чтобы сразу понятно было - key из первого множества, value из второго
    public GenericPowerSet<List<T>> cartesianProduct(GenericPowerSet<T> set2) {
        GenericPowerSet<List<T>> result = new GenericPowerSet<>();
        for (T elem1 : this.innerSet) {
            for (T elem2 : set2.innerSet) {
                ArrayList<T> pair = new ArrayList<>(Arrays.asList(elem1, elem2));
                Map<T, T> pairMap = new HashMap<>();
                pairMap.put(elem1, elem2);
                result.put(pair);
            }
        }
        return result;
    }
}
