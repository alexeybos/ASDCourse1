package org.skillsmart.lesson6;

import org.skillsmart.lesson4.Stack;

public class MinDeque<T> extends Deque<T> {

    public Stack<Double> minStackFront;
    public Stack<Double> minStackTail;

    public MinDeque() {
        super();
        minStackFront = new Stack<>();
        minStackTail = new Stack<>();
    }

    @Override
    public void addFront(T item) {
        super.addFront(item);
        if (item instanceof Number) {
            minStackFront.push(((Number) item).doubleValue());
        }
    }

    @Override
    public void addTail(T item) {
        super.addTail(item);
        if (item instanceof Number) {
            minStackTail.push(((Number) item).doubleValue());
        }
    }

    @Override
    public T removeFront() {
        T item = super.removeFront();
        if (item instanceof Number) {
            removeFromMinStacks(minStackFront, minStackTail);
        }
        return item;
    }

    @Override
    public T removeTail() {
        T item = super.removeTail();
        if (item instanceof Number) {
            removeFromMinStacks(minStackTail, minStackFront);
        }
        return item;
    }

    private void removeFromMinStacks(Stack<Double> mainStack, Stack<Double> secondStack) {
        if (mainStack.size() > 0) {
            mainStack.pop();
            return;
        }
        for (; secondStack.size() > 0;) {
            mainStack.push(secondStack.pop());
        }
        mainStack.pop();
    }

    public Double getMinValue() {
        if (minStackFront.size() == 0 && minStackTail.size() == 0) {
            return null;
        }
        if (minStackFront.size() == 0) {
            return minStackTail.getMinValue();
        }
        if (minStackTail.size() == 0) {
            return minStackFront.getMinValue();
        }
        return Math.min(minStackFront.getMinValue(), minStackTail.getMinValue());
    }
}
