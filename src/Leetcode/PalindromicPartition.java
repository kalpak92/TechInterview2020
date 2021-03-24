package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 *
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 *
 */

public class PalindromicPartition {
    public static List<List<String>> partition(String s) {
        // Edge case
        if(s == null || s.length() == 0)
            return new ArrayList<>();

        List<List<String>> result = new ArrayList<>();
        getPalindromePartitions(s, result, new ArrayList<>());

        return result;
    }

    private static void getPalindromePartitions(String s, List<List<String>> result, List<String> current) {
        // base case
        if(s == null || s.length() == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for(int i = 1; i <= s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);

            if(!isPalindrome(left))
                continue;

            current.add(left);  // choose
            getPalindromePartitions(right, result, current);    // explore
            current.remove(current.size() - 1);     // unchoose
        }
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
        System.out.println(partition("aab"));
    }
}
