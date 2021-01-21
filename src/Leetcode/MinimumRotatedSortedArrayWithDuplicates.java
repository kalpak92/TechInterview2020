package Leetcode;

/**
 * @author kalpak
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 *
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */

public class MinimumRotatedSortedArrayWithDuplicates {
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int result = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left)/2;

            if (nums[mid] < nums[right]) {
                // Sorted on right, check left
                result = Math.min(result, nums[mid]);
                right = mid - 1;
            }
            else if (nums[mid] > nums[right]) {
                // Pivot on right side, check right
                result = Math.min(result, nums[right]);
                left = mid + 1;
            }
            else {
                // Either on left or right, reduce high till not matching mid
                result = Math.min(result, nums[right]);
                while (right >= 0 && nums[right] == nums[mid]) {
                    right --;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 1, 10, 10, 10};
        System.out.println(findMin(nums));
    }
}
