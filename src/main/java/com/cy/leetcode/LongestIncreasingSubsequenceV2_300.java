package com.cy.leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/description/
 *
 * @Author chenying
 * @Date 2018/7/23
 */
public class LongestIncreasingSubsequenceV2_300 {
    @Test
    public void test() {
        int[] arr = {3,5,1,2,4,6,6,6,8};
        int result = new Solution().lengthOfLIS(arr);
        System.out.println(result);
    }


private static final class Solution {

    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0){
            return 0;
        }

        int[] dp = new int [nums.length];
        int len = 0;

        for(int n : nums){
            int idx = Arrays.binarySearch(dp, 0, len, n);
            if(idx<0){
                idx = -(idx+1);
            }

            dp[idx] = n;

            if(idx==len){
                len++;
            }
        }

        return len;
    }


 }
}
