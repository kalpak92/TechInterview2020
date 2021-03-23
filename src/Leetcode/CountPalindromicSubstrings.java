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

    public static void main(String[] args) {
        System.out.println(countSubstringsNaive("abcba"));
    }
}
