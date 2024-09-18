package org.skillsmart.lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void testSize_EmptyStack() {
        Stack stack = new Stack<>();
        assertEquals(0, stack.size());
    }

    @Test
    void testSize() {
        Stack stack = new Stack<>();
        stack.push(0);
        assertEquals(1, stack.size());
        stack.push(1);
        assertEquals(2, stack.size());
    }

    @Test
    void testPop_EmptyStack() {
        Stack stack = new Stack<>();
        assertNull(stack.pop());
    }

    @Test
    void testPop_ToEmptyStack() {
        Stack stack = new Stack<>();
        stack.push(0);
        assertEquals(1, stack.size());
        assertEquals(0, stack.pop());
        assertEquals(0, stack.size());
        assertNull(stack.pop());
    }

    @Test
    void testPop() {
        Stack stack = new Stack<>();
        stack.push(0);
        assertEquals(1, stack.size());
        stack.push("1");
        assertEquals(2, stack.size());
        assertEquals(0, stack.pop());
        assertEquals("1", stack.pop());
    }

    @Test
    void testPush_ToEmptyStack() {
        Stack stack = new Stack<>();
        stack.push(0);
        assertEquals(1, stack.size());
    }

    @Test
    void testPush() {
        Stack stack = new Stack<>();
        stack.push(0);
        stack.push("1");
        stack.push(0.5);
        assertEquals(3, stack.size());
    }

    @Test
    void testPeek_FromEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assertNull(stack.peek());
        assertEquals(0, stack.size());
    }

    @Test
    void testPeek_FromOneElementStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        assertEquals(2, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void testPeek() {
        Stack stack = new Stack<>();
        stack.push(0);
        stack.push("1");
        stack.push(0.5);
        assertEquals(3, stack.size());
        assertEquals(0.5, stack.peek());
        assertEquals(3, stack.size());
    }
}