package org.skillsmart.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson6UtilsTest {

    private final Lesson6Utils utils = new Lesson6Utils();

    @Test
    void testIsPalindrome() {
        assertTrue(utils.isPalindrome("А роза упала на лапу Азора"));
        assertFalse(utils.isPalindrome("А роза не упала на лапу Азора"));
        assertTrue(utils.isPalindrome(""));
        assertTrue(utils.isPalindrome("Лёша на полке клопа нашёл"));
        assertTrue(utils.isPalindrome("Anna"));
        assertTrue(utils.isPalindrome("Madam, I’m Adam"));
    }

    @Test
    void testIsBracketBalance() {
        assertTrue(utils.isBracketBalanced("(2 + 3)*(abs[1] - 8)"));
        assertTrue(utils.isBracketBalanced("(2 + {3})*{(abs[1] - 8)}"));
        assertFalse(utils.isBracketBalanced("(2 + [{3})]*{(abs[1] - 8)}"));
    }
}