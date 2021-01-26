package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given an array of n integers nums and an integer target,
 * find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Follow up: Could you solve it in O(n2) runtime?
 *
 * Example 1:
 * Input: nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 *
 * Example 2:
 * Input: nums = [], target = 0
 * Output: 0
 *
 * Example 3:
 * Input: nums = [0], target = 0
 * Output: 0
 *
 * Constraints:
 *
 * n == nums.length
 * 0 <= n <= 300
 * -100 <= nums[i] <= 100
 * -100 <= target <= 100
 */

public class ThreeSumTripletSmallerThanTarget {
    public static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;

        for(int i = 0; i < nums.length - 2; i++) {
            int left = i+1;
            int right = nums.length - 1;

            while(left < right) {
                if (nums[left] + nums[right] + nums[i] < target) {
                    result += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,0,1,3};
        System.out.println(threeSumSmaller(nums, 2));
    }
}
