package org.skillsmart.lesson5;

import org.skillsmart.lesson4.Stack;

public class QueueByStacks<T> {

    private final Stack<T> stack1;
    private final Stack<T> stack2;

    public QueueByStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enqueue(T item)
    {
        stack1.push(item);
    }

    public T dequeue()
    {
        if (stack1.size() + stack2.size() == 0) {
            return null;
        }
        if (stack2.size() > 0) {
            return stack2.pop();
        }
        for ( ; stack1.size() > 0; ) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public int size()
    {
        return stack1.size() + stack2.size();
    }
}
