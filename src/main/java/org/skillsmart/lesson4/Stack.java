package org.skillsmart.lesson4;

import java.util.*;

public class Stack<T>
{
    private Double elementSum = 0d;
    private final java.util.Stack<Double> minValues = new java.util.Stack<Double>();
    private int numberCount = 0;

    public ArrayList<T> stack;

    public Stack()
    {
        stack = new ArrayList<>();
    }

    public int size()
    {
        return stack.size();
    }

    public T pop()
    {
        if (stack.size() == 0) {
            return null;
        }
        T popValue = stack.get(stack.size() - 1);
        if (popValue instanceof Number) {
            deleteMinValue(((Number) popValue).doubleValue());
            numberCount--;
            elementSum -= ((Number) popValue).doubleValue();
        }
        return stack.remove(stack.size() - 1);
    }

    public void push(T val)
    {
        stack.add(val);
        if (val instanceof Number) {
            addMinValue(((Number) val).doubleValue());
            numberCount++;
            elementSum += ((Number) val).doubleValue();
        }
    }

    public T peek()
    {
        if (stack.size() == 0) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public Double getMinValue() {
        if (minValues.size() == 0) {
            return null;
        }
        return minValues.peek();
    }

    private void addMinValue(Double val) {
        if (minValues.size() == 0 ||  val <= minValues.peek()) {
            minValues.push(val);
        }
    }

    private void deleteMinValue(Double val) {
        if (Objects.equals(val, minValues.peek())) {
            minValues.pop();
        }
    }

    public Double getAverageValue() {
        if (numberCount == 0) {
            return 0d;
        }
        return elementSum / numberCount;
    }
}


