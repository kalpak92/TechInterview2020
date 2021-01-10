package Leetcode;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [1,1]
 * Output: 1
 *
 * Example 4:
 * Input: nums = [1,1,2]
 * Output: 1
 *
 * Constraints:
 *
 * 2 <= n <= 3 * 104
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 */

public class DuplicateInArray {
    public static int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            while(nums[i] <= nums.length && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i+1)
                return nums[i];
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,3,4,2,2};
        int[] arr2 = new int[]{3,1,3,4,2};
        System.out.println(findDuplicate(arr1));
        System.out.println(findDuplicate(arr2));
    }
}
