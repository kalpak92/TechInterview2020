package Leetcode;

/**
 * @author kalpak
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Follow up:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 */

public class RotateArray {
    public static void rotate(int[] nums, int k) {
        k = k % nums.length; // makes sure that k is less than the length of the array
        // reverse the entire array
        reverse(nums, 0, nums.length - 1);
        // reverse the first k elements
        reverse(nums, 0, k - 1);
        // reverse the last n-k elements
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int i, int j) {
        while(i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    // Time : O(n); Space : O(n)
    public void rotateNaive(int[] nums, int k) {
        int[] temp = new int[nums.length];
        for(int i = 0; i < nums.length; i++)
            temp[(i + k)%nums.length] = nums[i];

        for(int i = 0; i < nums.length; i++)
            nums[i] = temp[i];
    }

    public static void printArray(int[] nums) {
        for(int i : nums)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,4,5,6,7};
        rotate(arr1, 3);
        printArray(arr1);
    }
}
