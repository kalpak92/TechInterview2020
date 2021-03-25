package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 *
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 *
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 *
 */

public class MaximumLengthOfPairChain {
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];

        Arrays.fill(dp, 1);

        for(int i = 0; i < pairs.length; i++) {
            for(int j = 0; j < i; j++) {
                if(pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[pairs.length - 1];
    }

    public static int findLongestChainGreedy(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        int result = 0;
        int current = Integer.MIN_VALUE;

        for(int[] pair : pairs) {
            if(pair[0] > current) {
                current = pair[1];
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] pairs = new int[][]{{1,2}, {2,3}, {3,4}};
        System.out.println("The length of the longest pair chain is : " + findLongestChain(pairs));

    }
}
