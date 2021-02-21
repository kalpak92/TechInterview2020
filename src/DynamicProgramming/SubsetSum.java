package DynamicProgramming;

/**
 * @author kalpak
 *
 * Given a set of positive numbers, determine if there exists a subset whose sum is equal to a given number ‘S’.
 *
 * Example 1: #
 *
 * Input: {1, 2, 3, 7}, S=6
 * Output: True
 * The given set has a subset whose sum is '6': {1, 2, 3}
 *
 */

public class SubsetSum {
    public static boolean canPartition(int[] nums, int target) {
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
        int[] num = { 1, 2, 3, 7 };
        System.out.println(canPartition(num, 6));

        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(canPartition(num, 10));

        num = new int[] { 1, 3, 4, 8 };
        System.out.println(canPartition(num, 6));
    }
}
