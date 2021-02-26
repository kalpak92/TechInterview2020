package Leetcode;

/**
 * @author kalpak
 *
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 *
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 *
 * Example 2:
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 *
 * Example 3:
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 *
 * Example 4:
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 *
 *
 * Example 5:
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 *
 */

public class RegularExpressionMatching {
    public static boolean isMatch(String s, String p) {
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        // fill up the first  row with patterns like a*, a*b*, a*b*c*
        for(int i = 1; i < dp[0].length; i++) {
            if(p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 2];
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];

                    if(p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }
}
