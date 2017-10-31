package com.cy.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速排序
 * Created by chenying on 2017/10/31.
 */
public class QuickSort implements ISort {
    @Override
    public int[] sort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int n = 0;
        List<Integer> left = new ArrayList();
        List<Integer> right = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[n]) {
                left.add(arr[i]);
            }
            if (arr[i] > arr[n]) {
                right.add(arr[i]);
            }
            if (arr[i] == arr[n]) {
                mid.add(arr[i]);
            }
        }
        return link(link(sort(toArray(left)), toArray(mid)), sort(toArray(right)));
    }

    /**
     * 本来打算用数组，但是可变长度不好控制，这部改为列表
     * @param list
     * @return
     */
    private static int[] toArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private int[] link(int[] left, int[] right) {
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
