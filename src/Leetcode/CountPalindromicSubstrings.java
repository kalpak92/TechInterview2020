package Leetcode;

/**
 * @author kalpak
 *
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 *
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *
 * Example 2:
 *
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 * Note:
 *
 * The input string length won't exceed 1000.
 *
 */

public class CountPalindromicSubstrings {
    // Brute Force
    public static int countSubstringsNaive(String s) {
        int count = 0;
        if(s == null || s.length() == 0)
            return count;

        int length = s.length();

        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                String currentStr = s.substring(i, j + 1);
                if(isPalindrome(currentStr))
                    count++;
            }
        }
        return count;
    }

    private static boolean isPalindrome(String s) {
        if(s.length() == 0 || s == null)
            return false;

        int left = 0;
        int right = s.length() - 1;

        while(left < right) {
            if(s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;

    }

    public static int countSubstringsUsingExpandAroundCenter(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int length = s.length();
        int count = 0;

        for(int i = 0; i < s.length(); i++) {
            count += countPalindromeAroundCenter(s, i, i);  // Odd length palindrome. Single character at center
            count += countPalindromeAroundCenter(s, i, i + 1);  // Even length palindrome. Consecutive character at centre
        }

        return count;
    }


    private static int countPalindromeAroundCenter(String s, int left, int right) {
        int result = 0;

        while(left >= 0 && right < s.length()) {
            if(s.charAt(left) != s.charAt(right))
                break;

            // Expand around center
            left--;
            right++;

            result++;
        }
        return result;
    }

    public static int countSubstringsBottomsUp(String s) {
        if(s.length() == 0 || s == null)
            return 0;

        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        // Base case: single letter substrings
        for(int i  = 0 ; i < s.length(); i++) {
            dp[i][i] = true;
            count++;
        }

        // Base case: double letter substrings
        for(int i  = 0 ; i < s.length() - 1; i++) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i+ 1));

            count += (dp[i][i + 1]) ? 1 : 0;
        }

        for(int len = 3; len <= s.length(); len++) {
            for(int i = 0, j = i + len - 1; j < s.length(); i++, j++) {
                if(dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countSubstringsNaive("abcba"));
        System.out.println(countSubstringsUsingExpandAroundCenter("abcba"));
        System.out.println(countSubstringsBottomsUp("abcba"));
    }
}
