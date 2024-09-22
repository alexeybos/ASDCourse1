package org.skillsmart.lesson5;

import org.skillsmart.lesson4.Stack;

public class Lesson5Utils {

    public void rotateQueue(Queue _queue, int rotateCount) {
        for (int i = 0; i < rotateCount; i++) {
            _queue.enqueue(_queue.dequeue());
        }
    }

    public void turnQueue(Queue _queue) {
        Stack stack = new Stack<>();
        for (; _queue.size() > 0; ) {
            stack.push(_queue.dequeue());
        }
        for ( ; stack.size() > 0; ) {
            _queue.enqueue(stack.pop());
        }
    }
}
