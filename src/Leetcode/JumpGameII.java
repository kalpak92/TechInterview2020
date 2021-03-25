package Leetcode;

/**
 * @author kalpak
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^5
 *
 */

public class JumpGameII {
    public static int jump(int[] nums) {
        if (nums.length < 2)
            return 0;

        int maxDistanceReachable = nums[0];
        int maxSteps = nums[0];

        int minJumps = 1;

        for(int i = 1; i < nums.length; i++) {
            if(maxSteps < i) {
                minJumps++;                         // we need one more jump
                maxSteps = maxDistanceReachable;    // Greedy: Choose the max possible jump
            }

            maxDistanceReachable = Math.max(maxDistanceReachable, i + nums[i]);
        }
        return minJumps;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println("The minimum number of jumps required to reach the end : " + jump(nums));
    }
}
