package Leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kalpak
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * Example 1:
 *
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Example 2:
 *
 * Input: s = "a"
 * Output: 1
 *
 * Example 3:
 *
 * Input: s = "bb"
 * Output: 2
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 2000
 *     s consits of lower-case and/or upper-case English letters only.
 */

public class LongestPalindrome {
    public static int longestPalindrome(String s) {
        Set<Character> oddFreqCharacters = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            if(oddFreqCharacters.contains(s.charAt(i)))
                oddFreqCharacters.remove(s.charAt(i));
            else
                oddFreqCharacters.add(s.charAt(i));
        }
        return (oddFreqCharacters.size() > 0) ?  s.length() - oddFreqCharacters.size() + 1 : s.length();
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
    }
}
