package Leetcode;

/**
 * @author kalpak
 *
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */

public class PartitionEqualSubsetSum {
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i : nums)
            sum += i;

        if(sum % 2 != 0)
            return false;
        else
            return findSubsetSum(nums, sum/2);
    }

    private static boolean findSubsetSum(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length][target + 1];

        // Now the first column of the memoization table will be to true since we can achieve a target of 0 with empty set
        for(int i = 0; i < nums.length; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is
        // equal to its value
        for (int s = 1; s <= target; s++) {
            dp[0][s] = (nums[0] == s ? true : false);
        }

        // process all subsets for all sums
        for (int i = 1; i < nums.length; i++) {
            for (int s = 1; s <= target; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if (dp[i - 1][s]) {
                    dp[i][s] = dp[i - 1][s];
                } else if (nums[i] < s) {
                    // else include the number and see if we can find a subset to get the remaining
                    // sum
                    dp[i][s] = dp[i - 1][s - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }
}
