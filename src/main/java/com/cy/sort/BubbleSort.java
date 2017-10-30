package com.cy.sort;

/**
 * Created by chenying on 2017/10/30.
 */
public class BubbleSort implements ISort {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
        return arr;
    }

    /**
     * 实现arr数组，i位置元素与j位置元素交换
     *
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
