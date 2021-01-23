package BinarySearch;

import Leetcode.MaximumFrequencyStack;

/**
 * @author kalpak
 *
 * Given a sorted array, find the element in the array which has minimum difference with the given number.
 *
 */
public class MinimumDifferenceElement {
    public static int minimumDifference(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        if (nums[nums.length-1] <= target) return nums[nums.length-1];

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(nums[mid] == target)
                return nums[mid];
            else if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        if (nums[left] - target < target-nums[right])
            return nums[left];
        return nums[right];
    }

    public static void main(String[] args) {
        System.out.println(minimumDifference(new int[] { 4, 6, 10 }, 7));
        System.out.println(minimumDifference(new int[] { 4, 6, 10 }, 4));
        System.out.println(minimumDifference(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(minimumDifference(new int[] { 4, 6, 10 }, 17));
    }
}
