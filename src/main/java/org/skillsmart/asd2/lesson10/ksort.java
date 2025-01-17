package org.skillsmart.asd2.lesson10;

import java.util.*;

public class ksort {
    public String[] items;

    public ksort() {
        items = new String[800];
        Arrays.fill(items, null);
    }

    public int index(String s) {
        if (!isCorrect(s)) return -1;
        char letter = s.charAt(0);
        return (letter - 97) * 100 + Integer.parseInt(s.substring(1));
    }

    public boolean add(String s) {
        int index= index(s);
        if (index == -1) return false;
        items[index(s)] = s;
        return true;
    }

    private boolean isCorrect(String s) {
        if (s == null || s.length() != 3) return false;
        return s.replaceAll("^[a-h]\\d\\d$", "").isEmpty();
    }
}
