package com.cy.leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/n-queens/description/
 *
 * @Author chenying
 * @Date 2018/10/23
 */
public class NQueens {
    //static int times = 0;

    @Test
    public void test() {
        List<List<String>> lists = new NQueens().new Solution().solveNQueens(4);
        for (List<String> list : lists) {
            System.out.println(list);
        }
        System.out.println(lists.size());
        //System.out.println(lists.size() + "=======" + times);
    }

    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();
            int[][] arr = new int[n][n];
            if(n == 1){
                List<String> s = new ArrayList<>();
                s.add("Q");
                result.add(s);
                return result;
            }
            if(n == 2 || n == 3){
                return result;
            }
            for (int j = 0; j < n; j++) {
                down(arr, 0, j);
                solve(arr, 1, 1, result);
                up(arr, 0, j);
            }
            return result;
        }


        public void solve(int[][] arr, int m, int num, List<List<String>> result) {
            for (int j = 0; j < arr.length && num < arr.length; j++) {
                if (arr[m][j] == 0) {
                    down(arr, m, j);
                    num++;
                    if (num == arr.length) {
                        List<String> item = parseInfo(arr);
                        if (item != null) {
                            result.add(item);
                        }
                    }
                    solve(arr, m + 1, num, result);
                    up(arr, m, j);
                    num--;

                }
            }
        }

        private List<String> parseInfo(int[][] arr) {
            int num = 0;
            char[] tempArr = new char[arr.length];
            Arrays.fill(tempArr, '.');
            List<String> result = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] == -1) {
                        num++;
                        tempArr[j] = 'Q';
                        String item = new String(tempArr);
                        tempArr[j] = '.';
                        result.add(item);
                    }
                }
            }
            if (num < arr.length) {
                return null;
            }
            return result;
        }

        private void down(int[][] arr, int i, int j) {
            fun(arr, i, j, true);
        }

        private void up(int[][] arr, int i, int j) {
            fun(arr, i, j, false);
        }

        private void fun(int[][] arr, int i, int j, boolean isUse) {
            for (int m = 0; m < arr.length; m++) {
                for (int n = 0; n < arr.length; n++) {
                    if (m == i && n == j) {
                        continue;
                    }
                    if (m == i
                            || n == j
                            || (n - m == j - i)
                            || (m + n == i + j)) {
                        if (isUse) {
                            arr[m][n]++;
                        } else {
                            arr[m][n]--;
                        }
                    }
                }
            }
            if (isUse) {
                arr[i][j] = -1;
            } else {
                arr[i][j] = 0;
            }
        }
    }
}
