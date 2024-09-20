package org.skillsmart.lesson4;


import java.util.Objects;

public class Lesson4Utils {

    public boolean isParenthesesBalanced(String str) {
        char[] chars = str.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (char aChar : chars) {
            if (aChar == '(') {
                stack.push(1);
            } else {
                if (stack.pop() == null) {
                    return false;
                }
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
        for (char aChar : chars) {
            if (aChar == '(' || aChar =='{' || aChar == '[') {
                stack.push(aChar);
            } else {
                Character value = stack.pop();
                char bracketPair;
                //Пары скобок можно реализовать с помощью HashMap, но в условии разрешен только стек
                if (aChar == ')') {
                    bracketPair = '(';
                } else if (aChar == '}') {
                    bracketPair = '{';
                } else {
                    bracketPair = '[';
                }
                if (value == null || value != bracketPair) {
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
            if (Objects.equals(val, "*")) {
                calcStack.push(calcStack.pop() * calcStack.pop());
            } else if (Objects.equals(val, "+")) {
                calcStack.push(calcStack.pop() + calcStack.pop());
            } else if (Objects.equals(val, "=")) {
                return calcStack.peek();
            } else { //число
                calcStack.push(Integer.parseInt(val));
            }
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
