package org.skillsmart.lesson6;

import org.skillsmart.lesson4.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Lesson6Utils {

    public boolean isPalindrome(String str) {
        char[] chars = str.replaceAll("[^a-zA-Zа-яА-ЯЁё]", "").toLowerCase().toCharArray();
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

    /**
     *
     * Решения лучше, чем через стек не нашел...
     */
    public boolean isBracketBalanced(String expr) {
        char[] chars = expr.toCharArray();
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put('(', ')');
        bracketPairs.put('{', '}');
        bracketPairs.put('[', ']');
        for (char aChar : chars) {
            if (bracketPairs.containsKey(aChar)) {
                stack.push(aChar);
            } else if (bracketPairs.containsValue(aChar)) {
                if (stack.size() == 0 || aChar != bracketPairs.get(stack.pop())) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
