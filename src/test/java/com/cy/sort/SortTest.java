package com.cy.sort;

import com.cy.util.PrintUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenying on 2017/10/31.
 */
public class SortTest {
    private ISort sort;
    private int[] arr = {1, 8, 9, 2, 4, 6, 4, 3};

    @Test
    public void bubbleSort() {
        sort = new BubbleSort();
        sort.sort(arr);
        PrintUtil.print(arr);
    }

    @Test
    public void insertionSort() {
        sort = new InsertionSort();
        sort.sort(arr);
        PrintUtil.print(arr);
    }

    @Test
    public void quickSort() {
        sort = new QuickSort();
        arr = sort.sort(arr);
        PrintUtil.print(arr);
    }
    @Test
    public void mergeSort() {
        sort = new MergeSort();
        arr = sort.sort(arr);
        PrintUtil.print(arr);
    }
}
