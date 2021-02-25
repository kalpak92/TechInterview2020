package Leetcode;

/**
 * @author kalpak
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 *
 * Given n, calculate F(n).
 *
 * Example 1:
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 *
 *
 * Example 2:
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 *
 *
 * Example 3:
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= n <= 30
 *
 */

public class FibonacciNumber {
    public static int fib(int n) {
        int[] dp = new int[n + 1];

        return computeFibonacciTopDown(dp, n);
    }

    private static int computeFibonacciTopDown(int[] dp, int n) {
        if (n < 2)
            return n;

        if(dp[n] == 0)
            dp[n] = computeFibonacciTopDown(dp, n - 1) + computeFibonacciTopDown(dp, n - 2);

        return dp[n];
    }

    public static int computeFibonacciBottomsUp(int n) {
        if(n < 2)
            return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;


        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int computeFibonacciBottomsUpOptimized(int n) {
        if(n < 2)
            return n;

        int num1 = 0;
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
        System.out.println(fib(12));
        System.out.println(computeFibonacciBottomsUp(12));
        System.out.println(computeFibonacciBottomsUpOptimized(12));
    }
}
