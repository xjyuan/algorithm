package com.cy.leetcode;

/**
 * created by chenying
 * on 2018/5/29 18:39
 **/
public class FindDiagonalOrder_498 {
    public static void main(String[] args) {
        /*int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };*/
        int[][] matrix = {
        };
        int[] diagonalOrder = new FindDiagonalOrder_498().findDiagonalOrder(matrix);
        for (int i = 0; i < diagonalOrder.length; i++) {
            System.out.println(diagonalOrder[i]);
        }
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        int xLen = matrix.length;
        int yLen = matrix[0].length;
        int[] result = new int[xLen * yLen];
        int resultI = 0;
        boolean isBorder = true;
        boolean isUp = true;
        for (int i = 0, j = 0; i < xLen || j < yLen; ) {
            result[resultI] = matrix[i][j];
            resultI++;
            if ((i == 0 || j == yLen - 1) && isUp) {
                isBorder = true;
            }
            if ((j == 0 || i == xLen - 1) && !isUp) {
                isBorder = true;
            }
            if (isBorder & isUp) {
                if (j == yLen - 1) {
                    if (i == xLen - 1) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    j++;
                }
                isUp = !isUp;
                isBorder = false;
                continue;
            }
            if (isBorder & !isUp) {
                if (i == xLen - 1) {
                    if (j == yLen - 1) {
                        break;
                    } else {
                        j++;
                    }
                } else {
                    i++;
                }
                isUp = !isUp;
                isBorder = false;
                continue;
            }
            if (isUp) {
                i--;
                j++;
            } else {
                i++;
                j--;
            }
        }
        return result;
    }
}
