package com.cy.sort;

import com.cy.util.PrintUtil;
import org.junit.Test;

/**
 * Created by chenying on 2017/10/30.
 */
public class InsertionSortTest {

    private InsertionSort insertionSort = new InsertionSort();

    @Test
    public void sort() {
        int[] arr = {1, 8, 9, 2, 4, 6, 4, 3};
        insertionSort.sort(arr);
        PrintUtil.print(arr);
    }
    @Test
    public void sort2() {
        int[] arr = {1, 8, 9, 2, 4, 6, 4, 3};
        insertionSort.sort2(arr);
        PrintUtil.print(arr);
    }

}
