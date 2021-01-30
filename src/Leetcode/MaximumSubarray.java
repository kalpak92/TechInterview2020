package Leetcode;

/**
 * @author kalpak
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 *
 *
 * Example 3:
 * Input: nums = [0]
 * Output: 0
 *
 *
 * Example 4:
 * Input: nums = [-1]
 * Output: -1
 *
 *
 * Example 5:
 * Input: nums = [-100000]
 * Output: -100000
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }

    private static int maxSubArray(int[] nums, int left, int right) {
        if(left > right)
            return Integer.MIN_VALUE;

        int mid = left + (right - left)/2;

        // Finding maximum of subarrays ranging from nums[low, mid - 1] and nums[mid+1, high]
        int leftMax = maxSubArray(nums, left, mid - 1);
        int rightMax = maxSubArray(nums, mid + 1, right);

        int runningSum = 0;
        int leftSum = 0;
        int rightSum = 0;

        for(int i = mid - 1; i >= 0; i--) {
            runningSum += nums[i];
            leftSum = Math.max(leftSum, runningSum);
        }

        runningSum = 0;
        for(int i = mid + 1; i <= right; i++) {
            runningSum += nums[i];
            rightSum = Math.max(rightSum, runningSum);
        }

        // Return the maximum of the lefthalf, righthalf or the sum spanning across both the halves.
        return Math.max(Math.max(leftMax, rightMax), leftSum + rightSum + nums[mid]);
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
