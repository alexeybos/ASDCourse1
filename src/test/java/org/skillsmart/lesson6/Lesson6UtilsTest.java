package org.skillsmart.lesson6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson6UtilsTest {

    private final Lesson6Utils utils = new Lesson6Utils();

    @Test
    void isPalindrome() {
        assertTrue(utils.isPalindrome("А роза упала на лапу Азора"));
        assertFalse(utils.isPalindrome("А роза не упала на лапу Азора"));
        assertTrue(utils.isPalindrome(""));
        assertTrue(utils.isPalindrome("Оголи жопу пожилого"));
    }
}