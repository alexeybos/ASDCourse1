package org.skillsmart.lesson4;

import java.util.*;

public class Stack<T>
{

    public LinkedList<T> stack;

    public Stack()
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
            return null;  // если стек пустой
        }
        return stack.pop();
    }

    public void push(T val)
    {
        // ваш код
    }

    public T peek()
    {
        // ваш код
        return null; // если стек пустой
    }
}
