package Leetcode;

/**
 * @author kalpak
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 *
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Constraints:
 *
 * 1 <= n <= 45
 *
 */

public class ClimbStairs {
    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return countNumberOfStairsTopDown(dp, n);
    }

    private static int countNumberOfStairsTopDown(int[] dp, int n) {
        if(n < 2)
            return 1;

        if (dp[n] != 0)
            return dp[n];

        dp[n] = countNumberOfStairsTopDown(dp, n - 1) + countNumberOfStairsTopDown(dp, n - 2);
        return dp[n];
    }

    public static int countNumberOfStairsBottomsUp(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int countNumberOfStairsBottomsUpSpaceOptimized(int n) {
        if (n < 2)
            return 1;

        int num1 = 1;
        int num2 = 1;
        int temp = 0;

        for(int i = 2; i <= n; i++) {
            temp = num1 + num2;
            num1 = num2;
            num2 = temp;
        }

        return temp;
    }

    public static void main(String[] args) {
        System.out.println(countNumberOfStairsBottomsUpSpaceOptimized(13));
    }
}
