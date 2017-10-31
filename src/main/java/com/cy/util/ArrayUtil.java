package com.cy.util;

/**
 * Created by chenying on 2017/10/31.
 */
public class ArrayUtil {
    public static int[] link(int[] left, int[] right) {
        int[] arr = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int i = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                arr[i] = left[leftIndex];
                leftIndex++;
            } else {
                arr[i] = right[rightIndex];
                rightIndex++;
            }
            i++;
        }
        if (leftIndex < left.length) {
            for (int m = leftIndex; m < left.length; m++) {
                arr[i] = left[m];
                i++;
            }
        }
        if (rightIndex < right.length) {
            for (int m = rightIndex; m < right.length; m++) {
                arr[i] = right[m];
                i++;
            }
        }
        return arr;
    }
}
