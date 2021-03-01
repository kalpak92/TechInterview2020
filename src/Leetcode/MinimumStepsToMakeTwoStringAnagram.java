package Leetcode;

/**
 * @author kalpak
 *
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
 * Return the minimum number of steps to make t an anagram of s.
 *
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 *
 *
 * Example 1:
 * Input: s = "bab", t = "aba"
 * Output: 1
 * Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 *
 *
 * Example 2:
 * Input: s = "leetcode", t = "practice"
 * Output: 5
 * Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 *
 *
 * Example 3:
 * Input: s = "anagram", t = "mangaar"
 * Output: 0
 * Explanation: "anagram" and "mangaar" are anagrams.
 *
 *
 * Example 4:
 * Input: s = "xxyyzz", t = "xxyyzz"
 * Output: 0
 *
 * Example 5:
 * Input: s = "friend", t = "family"
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 50000
 * s.length == t.length
 * s and t contain lower-case English letters only.
 *
 */

public class MinimumStepsToMakeTwoStringAnagram {
    public static int minSteps(String s, String t) {
        int[] charFrquency = new int[26];
        int result = 0;

        // To optimize, convert to string array
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        for(int i = 0; i < sArray.length; i++) {
            charFrquency[sArray[i] - 'a']++;
        }

        for(int i = 0; i < tArray.length; i++) {
            charFrquency[tArray[i] - 'a']--;

            if(charFrquency[tArray[i] - 'a'] < 0)
                result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minSteps("friend", "family"));
    }
}
