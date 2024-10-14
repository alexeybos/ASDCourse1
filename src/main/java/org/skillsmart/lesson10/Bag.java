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

}
