package org.skillsmart.lesson10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lesson10utilsTest {

    Lesson10utils utils = new Lesson10utils();

    @Test
    void setsIntersection() {
        List<PowerSet> list = new ArrayList<>();
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        PowerSet set3 = new PowerSet();
        PowerSet set4 = new PowerSet();
        set1.put("1234");
        set1.put("12345");
        set1.put("12346");
        set1.put("12347");
        set1.put("12348");

        set2.put("2234");
        set2.put("12345");
        set2.put("22346");
        set2.put("12347");
        set2.put("12348");

        set3.put("1234");
        set3.put("32345");
        set3.put("22346");
        set3.put("12347");
        set3.put("12348");

        set4.put("4234");
        set4.put("12345");
        set4.put("12346");
        set4.put("42347");
        set4.put("12348");
        list.add(set1);
        list.add(set2);
        list.add(set3);
        list.add(set4);
        PowerSet result = utils.setsIntersection(list);
        assertEquals(1, result.size());
        assertTrue(result.get("12348"));
    }
}