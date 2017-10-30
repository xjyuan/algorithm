package com.cy.sort;

import org.junit.Test;
/**
 * Created by chenying on 2017/10/30.
 */

public class BubbleSortTest {

    private BubbleSort bubbleSort = new BubbleSort();


    @Test
    public void test() {
        int[] arr = {1, 8, 9, 2, 4, 6, 4, 3, 7, 1};
        bubbleSort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
