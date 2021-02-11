package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 *
 * Example 2:
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 *
 * Example 3:
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 *
 * Example 4:
 * Input: nums = [1]
 * Output: [1]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */

public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        while(i >= 0 && nums[i]  >= nums[i+1]) {
            i--;
        }

        if(i >= 0) {
            int j = nums.length - 1;
            while(j >= 0 && nums[j] <= nums[i])
                j--;

            swapTwoNumber(nums, i, j);
        }

        swapRange(nums, i + 1, nums.length - 1);
    }

    private static void swapTwoNumber(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void swapRange(int[] nums, int i, int j) {
        while(i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
