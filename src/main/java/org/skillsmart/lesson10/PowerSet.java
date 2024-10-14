package org.skillsmart.lesson10;

import java.util.*;

public class PowerSet
{
    public Map<String, Object> innerSet;

    public PowerSet()
    {
        innerSet = new HashMap<>(20000);
    }

    public int size()
    {
        return innerSet.size();
    }


    public void put(String value)
    {
        innerSet.put(value, 0);
    }

    public boolean get(String value)
    {
        return innerSet.containsKey(value);
    }

    public boolean remove(String value)
    {
        boolean result = innerSet.containsKey(value);
        innerSet.remove(value);
        return result;
    }

    public PowerSet intersection(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String val: set2.innerSet.keySet()) {
            if (innerSet.containsKey(val)) {
                result.put(val);
            }
        }
        return result;
    }

    public PowerSet union(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String val: innerSet.keySet()) {
            result.put(val);
        }
        for (String val: set2.innerSet.keySet()) {
            result.put(val);
        }
        return result;
    }

    public PowerSet difference(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String val: innerSet.keySet()) {
            if (!set2.innerSet.containsKey(val)) {
                result.put(val);
            }
        }
        return result;
    }

    public boolean isSubset(PowerSet set2)
    {
        PowerSet diff = set2.difference(this);
        return diff.size() == 0;
    }

    public boolean equals(PowerSet set2)
    {
        if (innerSet.size() != set2.size()) {
            return false;
        }
        PowerSet diff = set2.difference(this);
        return diff.size() == 0;
    }

    /**
     * Не получилось однозначно решить как представить результат произведения.
     * Вообще, по-хорошему, в итоге должно быть Set<List<String>> (а по факту вообще Set<List<T>>). Но шаблон класса не использует генерики...
     * В данном классе реализовал результат как List<List<String>>
     * Отдельно попробовал сделать метод в классе GenericPowerSet {@link GenericPowerSet}
     */
    public List<List<String>> cartesianProduct(PowerSet set2) {
        List<List<String>> result = new ArrayList<>();
        for (String elem1 :
                this.innerSet.keySet()) {
            for (String elem2 :
                    set2.innerSet.keySet()) {
                result.add(new ArrayList<>(Arrays.asList(elem1, elem2)));
            }
        }
        return result;
    }
}



