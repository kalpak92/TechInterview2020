package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 *
 *
 * Example 1:
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 *
 *
 * Example 2:
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 */

public class MaxScoreFromPerformingMultiplicationOperation {
    public static int maximumScore(int[] nums, int[] multipliers) {
        int[][] dp = new int[1000][1000];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return maximumScore(nums, multipliers, dp, 0, 0, nums.length - 1);
    }

    private static int maximumScore(int[] nums, int[] multipliers, int[][] dp, int index, int l, int r) {
        if (index == multipliers.length)
            return 0;

        if(dp[l][index] != -1)
            return dp[l][index];

        int start = nums[l] * multipliers[index] + maximumScore(nums, multipliers, dp, index + 1, l + 1, r);
        int end = nums[r] * multipliers[index] + maximumScore(nums, multipliers, dp, index + 1, l, r - 1);

        // keep on updating the smaller sub-solutions which is the maximum of choosing answer from leftmost or rightmost in nums vector.
        dp[l][index] = Math.max(start, end);

        return dp[l][index];
    }

    public static void main(String[] args) {
        System.out.println(maximumScore(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
    }
}
