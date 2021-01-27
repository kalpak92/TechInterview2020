package Leetcode;

/**
 *
 * @author kalpak
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
 *
 * Return the shortest such subarray and output its length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 */

public class ShortestUnsortedContiguousSubarray {
    public static int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;

        boolean flag = false;

        // Scan from left to find the starting point of the unsorted portion
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < nums[i - 1])
                flag = true;

            if (flag) { // find the minimum in the unsorted range
                min = Math.min(min, nums[i]);
            }
        }

        // Reset the flag
        flag = false;

        // Scan from right to find the ending point of the unsorted portion
        for(int i = nums.length - 2; i >= 0; i--) {
            if(nums[i] > nums[i + 1])
                flag = true;

            if(flag) {  // find the maximum in the unsorted range
                max = Math.max(max, nums[i]);
            }
        }

        // Now we need the left and right index from where the sorting criteria is violated
        for(left = 0; left < nums.length; left++) {
            if (nums[left] > min)   // leftmost point found
                break;
        }

        for(right = nums.length - 1; right >= 0; right--) {
            if(nums[right] < max) // rightmost point found
                break;
        }

        return right - left < 0 ? 0: right - left + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 7, 6, 2, 12, 10, 9, 11};
        System.out.println(findUnsortedSubarray(nums));
    }

}
