package com.cy.sort;

/**
 * Created by chenying on 2017/10/30.
 */
public class InsertionSort implements ISort {
    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //在数组arr在[0,i-1]区间内有序，查找arr[i]的插入点
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    inset(arr, i, j + 1);
                    break;
                }
            }
        }
        return arr;
    }

    //二分查找优化
    public int[] sort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int head = 0;
            int tail = i - 1;
            if (tail < 0) {
                continue;
            }
            int mid = (head + tail) / 2;
            while (head <= tail) {
                if (arr[i] > arr[mid]) {
                    head = mid + 1;
                    mid = (head + tail) / 2;
                } else {
                    tail = mid - 1;
                    mid = (head + tail) / 2;
                }
            }
            inset(arr, i, mid+1);
        }
        return arr;
    }

    /**
     * 将数组index位置元素移至插入position位置
     * index至position之间的元素位置不变
     *
     * @param arr
     * @param index
     * @param position
     */
    private void inset(int[] arr, int index, int position) {
        int temp = arr[index];
        for (int i = index; i >= position; i--) {
            arr[i] = arr[i - 1];
        }
        arr[position] = temp;
    }

}
