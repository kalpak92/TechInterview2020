package Leetcode;

/**
 * @author kalpak
 *
 * Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: 5 = 5 = 2 + 3
 *
 * Example 2:
 * Input: 9
 * Output: 3
 * Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 *
 * Example 3:
 * Input: 15
 * Output: 4
 *
 * Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * Note: 1 <= N <= 10 ^ 9.
 */

public class ConsecutiveNumbersSum {
    public static int consecutiveNumbersSum(int N) {
        int result = 0;

        int upperLimit = (int)(Math.sqrt(2*N + 0.25) - 0.5);

        for(int k = 1; k <= upperLimit; k++) {
            N -= k;
            if(N % k == 0)
                result++;
        }

        return result;
    }

    public static void main(String[] args) {
        int N = 15;
        System.out.println("The numbers of consecutive sum of positive integers that sum to N: " + consecutiveNumbersSum(N));
    }
}
