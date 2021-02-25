package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words,
 * it is the product of some integer with itself.
 *
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 *
 * Constraints:
 * 1 <= n <= 10^4
 *
 */

public class PerfectSquares {
    public static int numSquares(int n) {
        if (n < 4)
            return n;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        return countWaysToGetNumRecursive(dp, n);
    }

    private static int countWaysToGetNumRecursive(int[] dp, int n) {
        if (n < 4)
            return n;

        if(dp[n] != Integer.MAX_VALUE)
            return dp[n];

        int count = Integer.MAX_VALUE;
        for(int i = 1; i*i <= n; i++)
            count = Math.min(count, countWaysToGetNumRecursive(dp, n - i*i) + 1);

        dp[n] = count;
        return dp[n];
    }

    public static int numSquaresBottomsUp(int n) {
        if(n < 4)
            return n;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i = 4; i <= n; i++) {
            for(int j = 1; j*j <= i; j++) {
                int count1 = Integer.MAX_VALUE;
                int count2 = Integer.MAX_VALUE;

                count1 = dp[i - j*j];
                if(count1 != Integer.MAX_VALUE)
                    count1 += 1;

                count2 = dp[i];

                dp[i] = Math.min(count1, count2);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquaresBottomsUp(50));
    }
}
