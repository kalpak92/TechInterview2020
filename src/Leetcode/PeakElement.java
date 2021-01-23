package Leetcode;

/**
 * @author kalpak
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums[i] != nums[i + 1] for all valid i.
 *
 *
 * Follow up: Could you implement a solution with logarithmic complexity?
 */

public class PeakElement {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(mid > 0 && mid < nums.length - 1) {
                if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                    return mid;
                else if (nums[mid - 1] > nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (mid == 0) {                  // element on the left edge
                if(nums[mid] > nums[mid + 1])
                    return 0;
                else
                    return 1;
            } else if (mid == nums.length - 1) {    // element on the right edge
                if(nums[mid] > nums[mid - 1])
                    return nums.length - 1;
                else
                    return nums.length - 2;
            }
        }
        return -1;
    }
}
