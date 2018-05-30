package com.cy.leetcode;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * Created by chenying on 2018/5/30.
 */
public class MyAtoi_8 {
    @Test
    public void test() {
        String str = "42";
        int result = new Solution().myAtoi(str);
        Assert.isTrue(str.equals(result + ""));
    }

    @Test
    public void test1() {
        String str = "-42";
        int result = new Solution().myAtoi(str);
        Assert.isTrue(str.equals(result + ""));
    }

    @Test
    public void test2() {
        String str = "-+42";
        int result = new Solution().myAtoi(str);
        Assert.isTrue(result == 0);
    }

    @Test
    public void test3() {
        String str = "  42";
        int result = new Solution().myAtoi(str);
        Assert.isTrue(str.trim().equals(result + ""));
    }

    @Test
    public void test4() {
        String str = "   ";
        int result = new Solution().myAtoi(str);
        Assert.isTrue(result == 0);
    }

    @Test
    public void test5() {
        String str = "455145642115454124";
        int result = new Solution().myAtoi(str);
        Assert.isTrue(result == Integer.MAX_VALUE);
    }

    @Test
    public void test6() {
        String str = "-454541245411245212";
        int result = new Solution().myAtoi(str);
        Assert.isTrue(result == Integer.MIN_VALUE);
    }
}

class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int index = 0;
        while ((str.charAt(index)) == ' ') {
            index++;
            if (index >= str.length()) {
                return 0;
            }
        }
        boolean isNegative = false;
        if (str.charAt(index) == '-') {
            index++;
            if (index >= str.length()) {
                return 0;
            }
            isNegative = true;
        } else if (str.charAt(index) == '+') {
            index++;
            if (index >= str.length()) {
                return 0;
            }
        }
        char firstNum = str.charAt(index);
        if (!Character.isDigit(firstNum)) {
            return 0;
        }

        int result = 0;
        while (index < str.length() && Character.isDigit(str.charAt(index))) {
            int thisNum = Character.getNumericValue(str.charAt(index));
            if (isNegative) {
                thisNum = -thisNum;
            }
            int temp = result;
            result = result * 10 + thisNum;
            if (temp != 0 && Math.abs(temp) > Math.abs(Integer.MIN_VALUE) / 10 && !(result + "").startsWith(temp + "")){
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            index++;
        }
        return result;
    }
}
