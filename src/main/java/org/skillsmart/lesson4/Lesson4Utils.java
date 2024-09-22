package org.skillsmart.lesson4;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Lesson4Utils {

    public boolean isParenthesesBalanced(String str) {
        char[] chars = str.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (char aChar : chars) {
            if (aChar == '(') {
                stack.push(1);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.size() == 0;
    }

    /**
     * В данной реализации вариант строки вида "({)}" считаются несогласованными.
     * Чтобы такой вариант считался согласованным, одного стека мне не хватит.
     */
    public boolean isBracketBalanced(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put(')', '(');
        bracketPairs.put('}', '{');
        bracketPairs.put(']', '[');
        for (char aChar : chars) {
            if (bracketPairs.containsValue(aChar)) {
                stack.push(aChar);
            } else {
                if (stack.size() == 0 || stack.pop() != bracketPairs.get(aChar)) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public int postfixExpressionCalculate(Stack expression) {
        Stack<Integer> calcStack = new Stack<Integer>();
        for ( ; expression.size() > 0; ) {
            String val = expression.pop().toString();
            int iterationResult;
            Integer firstArgument = calcStack.pop();
            Integer secondArgument = calcStack.pop();
            if (Objects.equals(val, "*")) {
                iterationResult = firstArgument * secondArgument;
            } else if (Objects.equals(val, "+")) {
                iterationResult = firstArgument + secondArgument;
            } else if (Objects.equals(val, "-")) {
                iterationResult = firstArgument - secondArgument;
            } else if (Objects.equals(val, "/")) {
                iterationResult = firstArgument / secondArgument;
            } else if (Objects.equals(val, "=")) {
                return firstArgument;
            } else { //число
                //возвращаем обратно аргумент, т.к. на этом шаге мы ничего вычислять не будем
                calcStack.push(firstArgument);
                iterationResult = Integer.parseInt(val);
            }
            calcStack.push(iterationResult);
        }
        return calcStack.peek();
    }

    public int postfixExpressionCalculate(String expression) {
        Stack<String> inputStack = new Stack<>();
        String[] parts = expression.split(" ");
        for (int i = parts.length - 1; i >= 0; i--) {
            inputStack.push(parts[i]);
        }
        return postfixExpressionCalculate(inputStack);
    }
}
