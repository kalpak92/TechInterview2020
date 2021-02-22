package Leetcode;

/**
 * @author kalpak
 *
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 *
 * Constraints:
 *
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

public class TargetSum {
    public static int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int i : nums)
            sum += i;

        if(sum < S)
            return 0;

        if ((sum + S) % 2 != 0)
            return 0;

        return countOfSubsetsWithGivenSum(nums, (sum + S) / 2);
    }

    public static int countOfSubsetsWithGivenSum(int[] nums, int target) {
        int[][] dp = new int[nums.length][target + 1];


        // Now the first column of the memoization table will be to true since we can achieve a target of 0 with empty set
        for(int i = 0; i < nums.length; i++)
            dp[i][0] = 1;

        // with only one number, we can form a subset only when the required sum is
        // equal to its value
        for (int s = 1; s <= target; s++) {
            dp[0][s] = (nums[0] == s ? 1 : 0);
        }

        // process all subsets for all sums
        for (int i = 1; i < nums.length; i++) {
            for (int s = 1; s <= target; s++) {
                if (nums[i] <= s) {
                    // include the number and see if we can find a subset to get the remaining sum
                    dp[i][s] = dp[i - 1][s - nums[i]] + dp[i - 1][s];
                }
                else
                    dp[i][s]  = dp[i - 1][s];    // if we can get the sum 's' without the number at index 'i'
            }
        }
        return dp[nums.length - 1][target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1};
        System.out.println(findTargetSumWays(nums, 3));
    }
}
