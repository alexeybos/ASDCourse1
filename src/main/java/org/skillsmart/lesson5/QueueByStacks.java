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
        for ( ; stack1.size() > 0; ) {
            stack2.push(stack1.pop());
        }
        stack1.push(item);
        for ( ; stack2.size() > 0; ) {
            stack1.push(stack2.pop());
        }
    }

    public T dequeue()
    {
        if (stack1.size() == 0) {
            return null;
        }
        return stack1.pop();
    }

    public int size()
    {
        return stack1.size();
    }
}
