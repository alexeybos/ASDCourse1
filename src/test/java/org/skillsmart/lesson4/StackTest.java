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
        assertEquals("1", stack.pop());
        assertEquals(0, stack.pop());
        assertNull(stack.pop());
        assertEquals(0, stack.size());
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

    @Test
    void testGetMinValue() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        assertEquals(10, stack.getMinValue());
        stack.push(4);
        assertEquals(4, stack.getMinValue());
        stack.push(5);
        assertEquals(4, stack.getMinValue());
        stack.push(4);
        assertEquals(4, stack.getMinValue());
        stack.pop();
        assertEquals(4, stack.getMinValue());
        stack.pop();
        assertEquals(4, stack.getMinValue());
        stack.pop();
        assertEquals(10, stack.getMinValue());
        stack.pop();
        assertNull(stack.getMinValue());
    }

    @Test
    void testGetMinValue_DifferentTypes() {
        Stack stack = new Stack<>();
        stack.push(10);
        assertEquals(10, stack.getMinValue());
        stack.push("qwerty10");
        assertEquals(10, stack.getMinValue());
        stack.push(4);
        assertEquals(4, stack.getMinValue());
        stack.push(5);
        assertEquals(4, stack.getMinValue());
        stack.push(4);
        assertEquals(4, stack.getMinValue());
        stack.pop();
        assertEquals(4, stack.getMinValue());
        stack.pop();
        assertEquals(4, stack.getMinValue());
        stack.pop();
        assertEquals(10, stack.getMinValue());
        stack.pop();
        assertEquals(10, stack.getMinValue());
        stack.pop();
        assertNull(stack.getMinValue());
    }

    @Test
    void testGetAverageValue() {
        Stack stack = new Stack<>();
        stack.push(4);
        assertEquals(4, stack.getAverageValue());
        stack.push("qwerty10");
        stack.push(4);
        assertEquals(4, stack.getAverageValue());
        stack.push(7);
        assertEquals(5, stack.getAverageValue());
        stack.pop();
        assertEquals(4, stack.getAverageValue());
        stack.pop();
        assertEquals(4, stack.getAverageValue());
        stack.pop();
        stack.pop();
        assertEquals(0, stack.getAverageValue());
    }

}