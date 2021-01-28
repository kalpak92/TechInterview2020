package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 *
 * You need to return the number of important reverse pairs in the given array.
 *
 * Example1:
 * Input: [1,3,2,3,1]
 * Output: 2
 *
 * Example2:
 * Input: [2,4,3,5,1]
 * Output: 3
 *
 * Note:
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 *
 */

public class ReversePairs {
    public static int reversePairs(int[] nums) {
        int result = divideAndCountReversePairs(nums, 0, nums.length -1);
        return result;
    }

    private static int divideAndCountReversePairs(int[] nums, int left, int right) {
        if(left >= right)
            return 0;

        int mid = left + (right - left)/2;

        int count = divideAndCountReversePairs(nums, left, mid) + divideAndCountReversePairs(nums, mid + 1, right);

        // we use two pointers to travel left and right parts.
        // For each leftPart[i], if j<=right && nums[i]/2.0 > nums[j],
        // we just continue to move j to the end, to increase rightPart[j], until it is no longer valid.

        for(int i = left, j = mid + 1; i <= mid; i++) {
            while(j <= right && nums[i]/2.0 > nums[j])
                j++;

            count += j - (mid + 1);
        }

        Arrays.sort(nums, left, right + 1);
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,4,3,5,1};
        System.out.println(reversePairs(nums));
    }
}
