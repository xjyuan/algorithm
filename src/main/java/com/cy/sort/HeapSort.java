package com.cy.sort;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by chenying on 2017/11/1.
 */
public class HeapSort implements ISort {
    @Override
    public int[] sort(int[] arr) {
        BigHeap bigHeap = BigHeap.build(arr);
        BigHeap.adjustment(bigHeap);
        int[] sortArr = new int[arr.length];
        for (int i = sortArr.length - 1; i >= 0; i--) {
            sortArr[i] = BigHeap.getBigValue(bigHeap);
            BigHeap.adjustment(bigHeap);
        }
        return sortArr;
    }


}

/**
 * 大顶堆为完全二叉树
 * 根节点父节点为null，叶子节点子节点为null
 */
class BigHeap {
    BigHeap parent;
    int value;
    BigHeap left;
    BigHeap right;

    /**
     * 获取层序遍历的最后一个节点
     *
     * @param bigHeap
     * @return
     */
    public static int getBigValue(BigHeap bigHeap) {
        //保存层序遍历的结果
        Queue<BigHeap> queue = new LinkedList();
        BigHeap parent = bigHeap;
        queue.add(bigHeap);
        while (queue.size() > 0) {
            parent = queue.remove();
            if (parent.left != null) {
                queue.add(parent.left);
            }
            if (parent.right != null) {
                queue.add(parent.right);
            }
        }
        //交换根节点和层序遍历的最后一个节点，然后最后一个接单脱离大顶堆
        swapValue(bigHeap, parent);
        if (parent.parent == null) {
            return parent.value;
        }
        if (parent.parent.right == null) {
            parent.parent.left = null;
        } else {
            parent.parent.right = null;
        }
        return parent.value;
    }


    public static BigHeap build(int[] arr) {
        Queue<BigHeap> queue = new LinkedList();
        BigHeap root = new BigHeap();
        if (arr.length <= 0) {
            return root;
        }
        root.value = arr[0];
        queue.add(root);
        for (int i = 1; i < arr.length; i = i + 2) {
            BigHeap parent = queue.remove();
            if (parent != null) {
                BigHeap left = new BigHeap();
                left.value = arr[i];
                left.parent = parent;
                parent.left = left;
                queue.add(left);
                if (i + 1 < arr.length) {
                    BigHeap right = new BigHeap();
                    right.value = arr[i + 1];
                    right.parent = parent;
                    parent.right = right;
                    queue.add(right);
                }
            }
        }
        return root;
    }

    /**
     * 调整大顶堆
     */
    public static void adjustment(BigHeap bigHeap) {
        BigHeap mostLeft = bigHeap.left;
        if (mostLeft == null) {
            return;
        }
        while (mostLeft.left != null) {
            mostLeft = mostLeft.left;
        }
        BigHeap root = mostLeft.parent;

        while (root != null) {
            BigHeap right = root.right;
            BigHeap left = root.left;
            if (root.value < left.value) {
                swapValue(root, left);
                adjustment(left);
            }
            if (right != null && root.value < right.value) {
                swapValue(root, right);
                adjustment(right);
            }
            root = root.parent;
        }
    }

    private static void swapValue(BigHeap o1, BigHeap o2) {
        int temp = o1.value;
        o1.value = o2.value;
        o2.value = temp;
    }
}
