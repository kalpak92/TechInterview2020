package Leetcode;

/**
 * @author kalpak
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 3:
 * Input: nums = [0]
 * Output: 0
 *
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */

public class HouseRobberII {
    public static int rob(int[] nums) {
        if(nums.length == 0)
            return 0;

        if(nums.length == 1)
            return nums[0];

        return Math.max(robHouses(nums, 0, nums.length - 1), robHouses(nums, 1, nums.length));
    }

    private static int robHouses(int[] nums, int startIndex, int endIndex) {
        int timeOneDay = 0;
        int timeTwoDays = 0;

        for(int i = startIndex; i < endIndex; i++){
            int temp = timeOneDay;
            timeOneDay = Math.max(nums[i] + timeTwoDays, timeOneDay);

            timeTwoDays = temp;
        }
        return timeOneDay;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(rob(nums));
    }
}
