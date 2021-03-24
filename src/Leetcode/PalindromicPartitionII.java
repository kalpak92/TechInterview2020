package Leetcode;

/**
 * @author kalpak
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 *
 * Example 1:
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 *
 * Example 2:
 * Input: s = "a"
 * Output: 0
 *
 *
 * Example 3:
 * Input: s = "ab"
 * Output: 1
 *
 * Constraints:
 * 1 <= s.length <= 2000
 * s consists of lower-case English letters only.
 *
 */

public class PalindromicPartitionII {
    public static int minCut(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int[] dp = new int[s.length()];

        // For a string with n characters s[0, n-1], it needs at most n-1 cut.
        // Therefore, the 'cut' array is initialized as cut[i] = i
        for(int i = 0; i < s.length(); i++)
            dp[i] = i;

        // The external loop variable 'mid' represents the center of the palindrome.
        // The internal loop variable 'j' represents the 'radius' of the palindrome. Apparently, j <= i is a must.

        // This palindrome can then be represented as s[i-j, i+j].
        // If this string is indeed a palindrome, then one possible value of cut[i+j] is cut[i-j] + 1, where cut[i-j] corresponds to s[0, i-j-1] and 1 correspond to the palindrome s[i-j, i+j];

        for(int mid = 1; mid < s.length(); mid++) {
            // Case 1: Odd length Palindrome
            for(int start = mid, end = mid; start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }

            // Case 2: Even length Palindrome
            for(int start = mid - 1, end = mid; start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(minCut("aab"));
    }
}
