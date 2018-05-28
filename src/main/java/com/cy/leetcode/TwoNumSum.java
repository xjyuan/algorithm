package com.cy.leetcode;

public class TwoNumSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] x = new TwoNumSum().twoSum(nums, target);
        System.out.println(x[0] + "," + x[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int searchNum = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == searchNum) {
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }
}