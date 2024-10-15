package org.skillsmart.lesson10;

import org.skillsmart.lesson7.OrderedList;

import java.util.*;

public class Bag {

    public List<String> values;

    public Bag() {
        values = new ArrayList<>();
    }

    public void put(String value) {
        values.add(value);
    }

    public void delete(String value) {
        values.remove(value);
    }

    public Map<String, Integer> elemFrequency() {
        Map<String, Integer> result = new HashMap<>();
        for (String value : values) {
            Integer cnt = result.getOrDefault(value, 0);
            result.put(value, cnt + 1);
        }
        return result;
    }

    public int size()
    {
        return values.size();
    }

    public boolean get(String value)
    {
        for (String s : values) {
            if (Objects.equals(s, value)) return true;
        }
        return false;
    }

    public Bag intersection(Bag bag2)
    {
        Bag result = new Bag();
        //тут получается, если в нашем Bag есть два одинаковых значения (и есть одно в bag2), то в результирующем Bag будет два значения
        //если наоборот, в нашем одно, а в bag2 два, то в результирующем будет одно значение.
        for (String val: values) {
            if (bag2.values.contains(val)) {
                result.put(val);
            }
        }
        return result;
    }

    public Bag union(Bag bag2)
    {
        Bag result = new Bag();
        for (String val: values) {
            result.put(val);
        }
        for (String val: bag2.values) {
            result.put(val);
        }
        return result;
    }

    public Bag difference(Bag bag2)
    {
        Bag result = new Bag();
        for (String val: values) {
            if (!bag2.get(val)) {
                result.put(val);
            }
        }
        return result;
    }

    public boolean isSubset(Bag bag2)
    {
        Bag diff = bag2.difference(this);
        return diff.size() == 0;
    }

    public boolean equals(Bag set2)
    {
        if (values.size() != set2.size()) {
            return false;
        }
        Bag diff = set2.difference(this);
        return diff.size() == 0;
    }

}
