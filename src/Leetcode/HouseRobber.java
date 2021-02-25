package Leetcode;

/**
 * @author kalpak
 *
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 */

public class HouseRobber {
    public static int rob(int[] nums) {
        Integer[] dp = new Integer[nums.length];
        return stealMaximumMoneyTopDown(nums, dp, 0);
    }

    private static int stealMaximumMoneyTopDown(int[] nums, Integer[] dp, int currentIndex) {
        if(currentIndex >= nums.length)
            return 0;

        if(dp[currentIndex] != null)
            return dp[currentIndex];

        // steal from current house and skip one to steal from the next house
        int way1 = nums[currentIndex] + stealMaximumMoneyTopDown(nums, dp, currentIndex + 2);
        // skip current house to steel from the adjacent house
        int way2 = stealMaximumMoneyTopDown(nums, dp, currentIndex + 1);

        dp[currentIndex] = Math.max(way1, way2);
        return dp[currentIndex];
    }

    public static int robHousesBottomsUp(int[] nums) {
        if(nums.length == 0)
            return 0;

        int dp[] = new int[nums.length + 1];    // '+1' to handle the zero house

        dp[0] = 0;          // if there are no houses, the thief can't steal anything
        dp[1] = nums[0];    // only one house, so the thief have to steal from it

        // please note that dp[] has one extra element to handle zero house
        for(int i = 1; i < nums.length; i++)
            dp[i + 1] = Math.max(nums[i] + dp[i - 1], dp[i]);

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(rob(nums));
        System.out.println(robHousesBottomsUp(nums));
    }
}
