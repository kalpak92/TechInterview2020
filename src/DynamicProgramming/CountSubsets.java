package DynamicProgramming;

/**
 * @author kalpak
 *
 * Given an array arr[] of length N and an integer X, the task is to find the number of subsets with sum equal to X.
 * Examples:
 *
 * Input: arr[] = {1, 2, 3, 3}, X = 6
 * Output: 3
 *
 *  All the possible subsets are {1, 2, 3},
 * {1, 2, 3} and {3, 3}
 *
 * Input: arr[] = {1, 1, 1, 1}, X = 1
 * Output: 4
 */

public class CountSubsets {
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
        int[] nums = new int[]{3, 3, 3, 3};
        System.out.println(countOfSubsetsWithGivenSum(nums, 6));

        nums = new int[]{1, 2, 3, 3};
        System.out.println(countOfSubsetsWithGivenSum(nums, 6));

        nums = new int[]{2, 3, 7, 1, 4, 5};
        System.out.println(countOfSubsetsWithGivenSum(nums, 7));
    }
}
