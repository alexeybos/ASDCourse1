package org.skillsmart.lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeadStackTest {

    @Test
    void testSize_EmptyStack() {
        HeadStack stack = new HeadStack<>();
        assertEquals(0, stack.size());
    }

    @Test
    void testSize() {
        HeadStack stack = new HeadStack<>();
        stack.push(0);
        assertEquals(1, stack.size());
        stack.push(1);
        assertEquals(2, stack.size());
    }

    @Test
    void testPop_EmptyStack() {
        HeadStack stack = new HeadStack<>();
        assertNull(stack.pop());
    }

    @Test
    void testPop_ToEmptyStack() {
        HeadStack stack = new HeadStack<>();
        stack.push(0);
        assertEquals(1, stack.size());
        assertEquals(0, stack.pop());
        assertEquals(0, stack.size());
        assertNull(stack.pop());
    }

    @Test
    void testPop() {
        HeadStack stack = new HeadStack<>();
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
        HeadStack stack = new HeadStack<>();
        stack.push(0);
        assertEquals(1, stack.size());
    }

    @Test
    void testPush() {
        HeadStack stack = new HeadStack<>();
        stack.push(0);
        stack.push("1");
        stack.push(0.5);
        assertEquals(3, stack.size());
    }

    @Test
    void testPeek_FromEmptyStack() {
        HeadStack<Integer> stack = new HeadStack<>();
        assertNull(stack.peek());
        assertEquals(0, stack.size());
    }

    @Test
    void testPeek_FromOneElementStack() {
        HeadStack<Integer> stack = new HeadStack<>();
        stack.push(2);
        assertEquals(2, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void testPeek() {
        HeadStack stack = new HeadStack<>();
        stack.push(0);
        stack.push("1");
        stack.push(0.5);
        assertEquals(3, stack.size());
        assertEquals(0.5, stack.peek());
        assertEquals(3, stack.size());
    }
}