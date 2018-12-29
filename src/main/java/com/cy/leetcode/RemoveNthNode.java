package com.cy.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
 * @Author chenying
 * @Date 2018/10/24
 */
public class RemoveNthNode {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        ListNode listNode1 = new RemoveNthNode().new Solution().removeNthFromEnd(listNode, 1);
        while (listNode1 != null){
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode nNode = head.next;
            for (int m = 0; m < n; m++) {
                if(nNode == null){
                    return head.next;
                }
                nNode = nNode.next;
            }
            ListNode node = head;
            while (nNode != null){
                node = node.next;
                nNode = nNode.next;
            }
            node.next = node.next.next;
            return head;
        }

    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
