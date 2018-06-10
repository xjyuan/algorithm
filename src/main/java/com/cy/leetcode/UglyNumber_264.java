package com.cy.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/ugly-number-ii/description/
 * created by chenying
 * on 2018/6/10 17:26
 **/
public class UglyNumber_264 {

    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(12));
    }

    static class Solution {
        public int nthUglyNumber(int n) {

            int index2 = 0, index3 = 0, index5 = 0;

            int[] result = new int[n];
            int size = 1;
            result[0] = 1;
            while (size < n) {
                int num2 = 2 * result[index2];
                int num3 = 3 * result[index3];
                int num5 = 5 * result[index5];
                int topResult = result[size - 1];
                if (num2 <= num3 && num2 <= num5 && num2 >= topResult) {
                    index2++;
                    result[size] = num2;
                    size++;
                    if (num2 == num3) {
                        index3++;
                    }
                    if (num2 == num5) {
                        index5++;
                    }
                    continue;
                }
                if (num3 <= num2 && num3 <= num5 && num3 >= topResult) {
                    index3++;
                    result[size] = num3;
                    size++;
                    if (num3 == num5) {
                        index5++;
                    }
                    continue;
                }
                if (num5 <= num2 && num5 <= num3 && num5 >= topResult) {
                    index5++;
                    result[size] = num5;
                    size++;
                }
            }
            return result[n - 1];
        }
    }
}
