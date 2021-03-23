package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 * Example 1:
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 *
 */

public class LongestPalindromicSubsequence {
    public static int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return longestPalindromeSubseqTopDown(s, dp, 0, s.length() - 1);
    }

    private static int longestPalindromeSubseq(String s, int start, int end) {
        if(start > end)
            return 0;

        // every sequence with one element is a palindrome of length 1
        if(start == end)
            return 1;

        // case 1: elements at the beginning and the end are the same
        if(s.charAt(start) == s.charAt(end))
            return 2 + longestPalindromeSubseq(s, start + 1, end - 1);

        // case 2: skip one element either from the beginning or the end
        int c1 = longestPalindromeSubseq(s, start + 1, end);
        int c2 = longestPalindromeSubseq(s, start, end - 1);

        return Math.max(c1, c2);
    }

    private static int longestPalindromeSubseqTopDown(String s, int[][] dp, int start, int end) {
        if(start > end)
            return 0;

        if(start == end)
            return 1;

        // Every sequence with one element is a palindrome of length 1
        if(dp[start][end] == -1) {
            // Case 1: elements at the beginning and the end are the same
            if(s.charAt(start) == s.charAt(end))
                dp[start][end] = 2 + longestPalindromeSubseqTopDown(s, dp, start + 1, end - 1);
            else {
                // Case 2: skip one element either from the beginning or the end
                int c1 = longestPalindromeSubseqTopDown(s, dp, start + 1, end);
                int c2 = longestPalindromeSubseqTopDown(s, dp, start, end - 1);
                dp[start][end] = Math.max(c1, c2);
            }
        }

        return dp[start][end];
    }

    public static void main(String[] args) {
        System.out.println("The length of the longest palindromic subsequence is : " + longestPalindromeSubseq("bbbab"));
    }
}
