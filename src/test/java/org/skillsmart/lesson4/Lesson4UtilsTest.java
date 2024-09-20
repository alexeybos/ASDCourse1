package org.skillsmart.lesson4;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class Lesson4UtilsTest {

    Lesson4Utils utils = new Lesson4Utils();

    @Test
    void isParenthesesBalanced() {
        assertTrue(utils.isParenthesesBalanced("((())())"));
        assertTrue(utils.isParenthesesBalanced("()"));
        assertTrue(utils.isParenthesesBalanced("(((())))"));
        assertTrue(utils.isParenthesesBalanced("()()()()()"));
        assertFalse(utils.isParenthesesBalanced(")()()("));
        assertFalse(utils.isParenthesesBalanced("())("));
        assertFalse(utils.isParenthesesBalanced("))(("));
        assertFalse(utils.isParenthesesBalanced("((())"));
    }

    @Test
    void isBracketBalanced() {
        assertTrue(utils.isBracketBalanced("((())())"));
        assertTrue(utils.isBracketBalanced("()"));
        assertTrue(utils.isBracketBalanced("(((())))"));
        assertTrue(utils.isBracketBalanced("()()()()()"));
        assertFalse(utils.isBracketBalanced(")()()("));
        assertFalse(utils.isBracketBalanced("())("));
        assertFalse(utils.isBracketBalanced("))(("));
        assertFalse(utils.isBracketBalanced("((())"));

        assertTrue(utils.isBracketBalanced("({[]})"));
        assertTrue(utils.isBracketBalanced("(){}[][]{}()"));
        assertFalse(utils.isParenthesesBalanced("({)}"));
        assertFalse(utils.isBracketBalanced("}{"));
        assertFalse(utils.isBracketBalanced("((}}"));
        assertFalse(utils.isBracketBalanced("{}[]]"));
        assertFalse(utils.isBracketBalanced("[[{}(}]]"));
    }

    @Test
    void testPostfixExpressionCalculate() {
        assertEquals(9, utils.postfixExpressionCalculate("1 2 + 3 *"));
        int res = utils.postfixExpressionCalculate("8 2 + 5 * 9 + =");
        assertEquals(59, res);
        assertEquals(2, utils.postfixExpressionCalculate("3 8 - 6 + 2 * 44 / ="));
    }
}