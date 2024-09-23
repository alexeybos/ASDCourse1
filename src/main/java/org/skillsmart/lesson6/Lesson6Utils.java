package org.skillsmart.lesson6;

import java.util.Objects;

public class Lesson6Utils {

    public boolean isPalindrome(String str) {
        //String[] parts = str.toLowerCase().split(" ");
        char[] chars = str.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase().toCharArray();
        Deque<Character> deque = new Deque<>();
        for (char _char : chars) {
            deque.addTail(_char);
        }
        for (; deque.size() > 1;) {
            if (!Objects.equals(deque.removeFront(), deque.removeTail())) {
                return false;
            }
        }
        return true;
    }
}
