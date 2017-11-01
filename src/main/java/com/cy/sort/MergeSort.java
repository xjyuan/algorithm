package com.cy.sort;

import com.cy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenying on 2017/10/31.
 */
public class MergeSort implements ISort {
    @Override
    public int[] sort(int[] arr) {
        List<int[]> arrList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrList.add(new int[]{arr[i]});
        }
        while (arrList.size() > 1) {
            for (int i = 0; i < arrList.size() - 1; i++) {
                int[] a = arrList.get(i);
                int[] b = arrList.get(i + 1);
                arrList.remove(i);
                arrList.remove(i);
                arrList.add(ArrayUtil.link(a, b));
            }
        }
        return arrList.get(0);
    }

}
