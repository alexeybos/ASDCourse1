package org.skillsmart.lesson4;

import java.util.LinkedList;

public class HeadStack<T> {

    public LinkedList<T> stack;

    public HeadStack()
    {
        stack = new LinkedList<>();
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
        return stack.removeFirst();
    }

    public void push(T val)
    {
        stack.addFirst(val);
    }

    public T peek()
    {
        if (stack.size() == 0) {
            return null;
        }
        return stack.getFirst();
    }
}
