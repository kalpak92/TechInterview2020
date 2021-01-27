package Leetcode;

/**
 * @author kalpak
 *
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Example 3:
 * Input: nums = [0]
 * Output: [0]
 *
 * Example 4:
 * Input: nums = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is 0, 1, or 2.
 *
 *
 * Follow up:
 *
 * Could you solve this problem without using the library's sort function?
 * Could you come up with a one-pass algorithm using only O(1) constant space?
 */

public class SortColors {
    public static void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int current = 0;

        while(current <= right) {
            if(nums[current] == 0) {
                int temp = nums[left];
                nums[left] = nums[current];
                nums[current] = temp;

                current++;
                left++;
            } else if (nums[current] == 2) {
                int temp = nums[right];
                nums[right] = nums[current];
                nums[current] = temp;

                right--;
            } else
                current++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors(nums);
        for(int i : nums) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
