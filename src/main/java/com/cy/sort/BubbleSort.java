package com.cy.sort;

/**
 * 冒泡排序
 * Created by chenying on 2017/10/30.
 */
public class BubbleSort implements ISort {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int swapNum = 0;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapNum++;
                    swap(arr, j, j + 1);
                }
            }
            if (swapNum == 0) {
                break;
            }
        }
        return arr;
    }

    public int[] sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
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
