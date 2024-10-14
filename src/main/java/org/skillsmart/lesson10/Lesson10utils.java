package org.skillsmart.lesson10;

import java.util.List;

public class Lesson10utils {

    public PowerSet setsIntersection(List<PowerSet> sets) {
        if (sets.isEmpty()) return new PowerSet();
        PowerSet result = sets.get(0);
        for (int i = 1; i < sets.size() && result.size() > 0; i++) {
            result = result.intersection(sets.get(i));
        }
        return result;
    }
}
