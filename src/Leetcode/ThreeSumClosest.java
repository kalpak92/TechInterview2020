package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example 1:
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 *
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */

public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];

        for(int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i+1;
                int right = nums.length - 1;

                while(left < right) {
                    int sum = nums[left] + nums[right] + nums[i];

                    if(sum == target) {
                        // best case solution.
                        return sum;
                    } else if (sum < target) {
                        // Remove duplicate nums[left] and nums[right] elements
                        while(left < right && nums[left] == nums[left+1])
                            left++;
                        left++;
                    } else {
                        while(left < right && nums[right] == nums[right-1])
                            right--;
                        right--;
                    }

                    if(Math.abs(target - sum) < Math.abs(target - result))
                        result = sum;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,2,1,-4};
        System.out.println(threeSumClosest(nums, 1));
    }
}
