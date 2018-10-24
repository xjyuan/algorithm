package com.cy.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author chenying
 * @Date 2018/10/23
 */
public class NQueens {
    public static void main(String[] args) {
        List<List<String>> lists = new NQueens().new Solution().solveNQueens(8);
        System.out.println(lists);
    }

    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> list = new ArrayList<>();
            Stack<Data> stack = new Stack<>();
            int[][] arr = new int[n][n];
            int num = 0;
            while (num < n) {
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length; j++) {
                        if (arr[i][j] == 0) {
                            boolean b = queenDown(arr, i, j);
                            if (b) {
                                arr[i][j] = -1;
                                stack.add(new Data(i,j));
                                num++;
                            }
                        }

                    }
                    if(i == arr.length -1){
                        Data peek = stack.peek();
                        i=peek.i;
                        int j=peek.j;
                        arr[i][j] = 0;
                        queenUp(arr, i,j);
                    }
                }
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            return list;
        }

        private boolean queenDown(int[][] arr, int i, int j) {
            for (int k = 0; k < arr.length; k++) {
                if (arr[i][k] < 0) {
                    return false;
                }
                arr[i][k] = arr[i][k] + 1;
                if (arr[k][j] < 0) {
                    return false;
                }
                arr[k][j] = arr[k][j] + 1;
            }
            int i1 = i;
            int j1 = j;
            while (i1 >= 1 && j1 >= 1) {
                i1--;
                j1--;
                if (arr[i1][j1] < 0) {
                    return false;
                }
                arr[i1][j1] = arr[i1][j1] + 1;
            }

            int i2 = i;
            int j2 = j;
            while (i2 < arr.length-1 && j2 < arr.length-1) {
                i2++;
                j2++;
                if (arr[i2][j2] < 0) {
                    return false;
                }
                arr[i2][j2] = arr[i2][j2] + 1;
            }

            int i3 = i;
            int j3 = j;
            while (i3 < arr.length-1 && j3 >= 1) {
                i3++;
                j3--;
                if (arr[i3][j3] < 0) {
                    return false;
                }
                arr[i3][j3] = arr[i3][j3] + 1;
            }

            int i4 = i;
            int j4 = j;
            while (i4 >= 1 && j4 < arr.length ) {
                i4--;
                j4--;
                if (arr[i4][j4] < 0) {
                    return false;
                }
                arr[i4][j4] = arr[i4][j4] + 1;
            }
            return true;
        }


        private boolean queenUp(int[][] arr, int i, int j) {
            for (int k = 0; k < arr.length; k++) {
                if (arr[i][k] < 0) {
                    return false;
                }
                arr[i][k] = arr[i][k] - 1;
                if (arr[k][j] < 0) {
                    return false;
                }
                arr[k][j] = arr[k][j] - 1;
            }
            int i1 = i;
            int j1 = j;
            while (i1 >= 0 && j1 >= 0) {
                i1--;
                j1--;
                if (arr[i1][j1] < 0) {
                    return false;
                }
                arr[i1][j1] = arr[i1][j1] - 1;
            }

            int i2 = i;
            int j2 = j;
            while (i2 < arr.length && j2 < arr.length) {
                i2++;
                j2++;
                if (arr[i2][j2] < 0) {
                    return false;
                }
                arr[i2][j2] = arr[i2][j2] - 1;
            }

            int i3 = i;
            int j3 = j;
            while (i3 < arr.length && j3 >= 0) {
                i3++;
                j3--;
                if (arr[i3][j3] < 0) {
                    return false;
                }
                arr[i3][j3] = arr[i3][j3] - 1;
            }

            int i4 = i;
            int j4 = j;
            while (i4 >= 0 && j4 < arr.length) {
                i4--;
                j4--;
                if (arr[i4][j4] < 0) {
                    return false;
                }
                arr[i4][j4] = arr[i4][j4] - 1;
            }
            return true;
        }
    }

    static class Data{
        int i;
        int j;

        Data(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}
