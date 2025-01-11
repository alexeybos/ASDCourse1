package org.skillsmart.recursion;

import org.skillsmart.lesson4.Stack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursionFunctions {

    public long power(long base, long pow) {
        if (pow == 0) {
            return 1;
        }
        return base * power(base, pow - 1);
    }

    public long digitSum(long number) {
        long remainingDigits = number / 10;
        if (remainingDigits == 0) {
            return number;
        }
        return number % 10 + digitSum(remainingDigits);
    }

    public int listLength(Stack<Integer> list) {
        if (list.pop() == null) {
            return 0;
        }
        return 1 + listLength(list);
    }

    public boolean isPalindrome(String str) {
        char[] chars = str.replaceAll("[^a-zA-Zа-яА-ЯЁё]", "").toLowerCase().toCharArray();
        return isLettersEqual(chars, 0, chars.length - 1);
    }

    private boolean isLettersEqual(char[] chars, int headIndex, int tailIndex) {
        if (headIndex == tailIndex || headIndex + 1 == tailIndex) return chars[headIndex] == chars[tailIndex];
        if (chars[headIndex] != chars[tailIndex]) return false;
        return isLettersEqual(chars, headIndex + 1, tailIndex - 1);
    }

    public void printOddValues(List<Integer> list) {
        printOddValues(list, 0);
    }

    private void printOddValues(List<Integer> list, int index) {
        if (index == list.size()) return;
        if (list.get(index) % 2 == 0) {
            System.out.println(list.get(index));
        }
        printOddValues(list, index + 1);
    }

    public void printValuesWithOddIndexes(List<Integer> list) {
        printValuesWithOddIndexes(list, 0);
    }

    private void printValuesWithOddIndexes(List<Integer> list, int index) {
        if (index >= list.size()) return;
        System.out.println(list.get(index));
        printValuesWithOddIndexes(list, index + 2);
    }

    public int findSecondMax(List<Integer> list) {
        return findSecondMax(list, list.getFirst(), list.getFirst(), 1);
    }

    private int findSecondMax(List<Integer> list, int firstMax, int secondMax, int index) {
        if (index == list.size()) return secondMax;
        secondMax = Math.max(secondMax, list.get(index));
        if (secondMax > firstMax) {
            int tempVal = firstMax;
            firstMax = secondMax;
            secondMax = tempVal;
        }
        return findSecondMax(list, firstMax, secondMax, index + 1);
    }

    /*public void findAllFiles(File dir) {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                findAllFiles(file);
            } else {
                System.out.println(file.getName());
            }
        }
    }*/

    public List<File> findAllFiles(File dir) {
        File[] files = dir.listFiles();
        List<File> result = new ArrayList<>();
        if (files == null) return null;
        for (File file : files) {
            if (file.isDirectory()) {
                result.addAll(findAllFiles(file));
            } else {
                result.add(file);
            }
        }
        return result;
    }

    /*public void findAllFiles(String dirName) {
        File startDir = new File(dirName);
        findAllFiles(startDir.listFiles(), 0);
    }
    private void findAllFiles(File[] files, int index) {
        if (files == null || files.length == index) return;
        if (files[index].isDirectory()) findAllFiles(files[index].listFiles(), 0);
        System.out.println(files[index].getName());
        findAllFiles(files, index + 1);
    }
    */


    public void generateParenthesisSequence(int cnt) {
        char[] br = new char[cnt*2];
        generateParenthesisSequence(br, cnt, 0, 0);
    }

    private void generateParenthesisSequence(char[] brackets, int cnt, int index, int balance) {
        if (index == cnt * 2) {
            System.out.println(brackets);
            return;
        }
        if (balance == cnt * 2 - index) {
            brackets[index] = ')';
            generateParenthesisSequence(brackets, cnt, index + 1, balance - 1);
        } else {
            brackets[index] = '(';
            generateParenthesisSequence(brackets, cnt, index + 1, balance + 1);
            if (balance > 1) {
                brackets[index] = ')';
                generateParenthesisSequence(brackets, cnt, index + 1, balance - 2);
            }
        }
    }
}
