package org.skillsmart.recursion;

import org.junit.jupiter.api.Test;
import org.skillsmart.lesson4.Stack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecursionFunctionsTest {

    private RecursionFunctions tasks = new RecursionFunctions();

    @Test
    void testPower() {
        assertEquals(1, tasks.power(2,0));
        assertEquals(2, tasks.power(2,1));
        assertEquals(4, tasks.power(2,2));
        assertEquals(8, tasks.power(2,3));

        assertEquals(1, tasks.power(3,0));
        assertEquals(3, tasks.power(3,1));
        assertEquals(9, tasks.power(3,2));
        assertEquals(27, tasks.power(3,3));
    }

    @Test
    void testDigitSum() {
        assertEquals(0, tasks.digitSum(0));
        assertEquals(5, tasks.digitSum(5));
        assertEquals(8, tasks.digitSum(53));
        assertEquals(15, tasks.digitSum(159));
        assertEquals(11, tasks.digitSum(1235));
        assertEquals(15, tasks.digitSum(12345));
        assertEquals(20, tasks.digitSum(519023));
    }

    @Test
    void testListLength() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertEquals(5, tasks.listLength(stack));
        Stack<Integer> emptyStack = new Stack<>();
        assertEquals(0, tasks.listLength(emptyStack));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(tasks.isPalindrome("А роза упала на лапу Азора"));
        assertFalse(tasks.isPalindrome("А роза не упала на лапу Азора"));
        assertTrue(tasks.isPalindrome("Лёша на полке клопа нашёл"));
        assertTrue(tasks.isPalindrome("Anna"));
        assertTrue(tasks.isPalindrome("Ansna"));
        assertTrue(tasks.isPalindrome("Madam, I’m Adam"));
    }

    @Test
    void testPrintOddValues() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }
        tasks.printOddValues(list);
    }

    @Test
    void testPrintValuesWithOddIndexes() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            list.add(i);
        }
        tasks.printValuesWithOddIndexes(list);
    }

    @Test
    void testFindSecondMax() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(4);
        list.add(2);
        list.add(5);
        list.add(1);
        assertEquals(5, tasks.findSecondMax(list));
    }

    @Test
    void testFindSecondMaxEquals() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(8);
        list.add(4);
        list.add(8);
        list.add(4);
        assertEquals(8, tasks.findSecondMax(list));
    }

    @Test
    void testFindAllFiles() {
        //tasks.findAllFiles(new File("C:\\Work\\Tmp\\ver13"));
        List<File> list = tasks.findAllFiles(new File("C:\\\\Work\\\\Tmp\\\\install\\\\9.4.1\\\\inventory"));
        for (File file: list) {
            System.out.println(file.getName());
        }
    }

    /*@Test
    void testFindAllFiles() {
        tasks.findAllFiles("C:\\Work\\Tmp\\install\\9.4.1\\inventory");
    }*/

    @Test
    void testGenerateParenthesisSequenceByCycle() {
        //tasks.generateParenthesisSequenceByCycle(2);
        tasks.generateParenthesisSequence(3);
    }

}