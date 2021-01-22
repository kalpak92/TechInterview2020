package Leetcode;

/**
 * @author kalpak
 *
 * You are given an integer array nums sorted in ascending order (not necessarily distinct values), and an integer target.
 *
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,4,4,5,6,6,7] might become [4,5,6,6,7,0,1,2,4,4]).
 *
 * If target is found in the array return its index, otherwise, return -1.
 *
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 *
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums is guaranteed to be rotated at some pivot.
 * -10^4 <= target <= 10^4
 */

public class SearchInRotatedSortedArrayWithDuplicates {
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(nums[mid] == target)
                return true;

            if ((nums[left] == nums[mid]) && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if(nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if(nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,5,6,0,0,1,2};
        System.out.println(search(nums, 0));

        nums = new int[]{10, 1, 10, 10, 10, 10};
        System.out.println(search(nums, 1));
    }
}
