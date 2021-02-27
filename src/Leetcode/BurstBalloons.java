package Leetcode;

/**
 * @author kalpak
 *
 * You are given n balloons, indexed from 0 to n - 1.
 * Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
 * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 * Example 1:
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 * Example 2:
 * Input: nums = [1,5]
 * Output: 10
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 */

public class BurstBalloons {
    public static int maxCoins(int[] nums) {
        int[] paddedBalloons = new int[nums.length + 2];
        int[][] dp = new int[paddedBalloons.length][paddedBalloons.length];

        paddedBalloons[0] = paddedBalloons[paddedBalloons.length - 1] = 1;

        for(int i = 0; i< nums.length; i++) {
            paddedBalloons[i + 1] = nums[i];
        }

        return getMaximumCoins(paddedBalloons, dp, 0, paddedBalloons.length - 1);
    }

    private static int getMaximumCoins(int[] paddedBallons, int[][] dp, int left, int right) {
        if(left + 1 == right)
            return 0;


        if(dp[left][right] > 0)
            return dp[left][right];

        int maxCoins = 0;

        for(int i = left + 1; i < right; i++) {
            int score = paddedBallons[left] * paddedBallons[i] * paddedBallons[right];
            maxCoins = Math.max(maxCoins, score + getMaximumCoins(paddedBallons, dp, left, i) + getMaximumCoins(paddedBallons, dp, i, right));
        }

        dp[left][right] = maxCoins;
        return maxCoins;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println(maxCoins(nums));
    }
}
