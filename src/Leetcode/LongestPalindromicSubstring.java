package Leetcode;

/**
 * @author kalpak
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 *
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ac"
 * Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters (lower-case and/or upper-case),
 *
 */

public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];

        int maxLength = 1;
        int startIndex = 0;

        for(int j = 1 ; j < s.length() ; j ++) {
            for(int i = 0; i <= j ; i ++) {
                if(s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    int len = j - i + 1;

                    if(len <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                    if(dp[i][j] && len > maxLength) {
                        maxLength = len;
                        startIndex = i;
                    }
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLength);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babadoe"));
    }
}
