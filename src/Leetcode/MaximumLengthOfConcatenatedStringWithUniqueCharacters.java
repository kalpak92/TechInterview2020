package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 *
 */

public class MaximumLengthOfConcatenatedStringWithUniqueCharacters {
    public static int maxLength(List<String> arr) {
        int[] result = new int[1];

        findMaxUniqueLength(arr, 0, "", result);
        return result[0];
    }

    private static void findMaxUniqueLength(List<String> arr, int index, String current, int[] result) {
        if(index == arr.size() && getUniqueCharCount(current) > result[0]) {
            result[0] = current.length();
            return;
        }

        if(index == arr.size())
            return;

        if(getUniqueCharCount(current) == -1)
            return;

        findMaxUniqueLength(arr, index + 1, current, result);
        findMaxUniqueLength(arr, index + 1, current + arr.get(index), result);
    }

    // check if a string made of unique string.
    // If so, then return the length.
    private static int getUniqueCharCount(String s) {
        int[] counts = new int[26];
        for(char c : s.toCharArray()) {
            if(counts[c - 'a'] > 0)
                return -1;

            counts[c - 'a']++;
        }
        return s.length();
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>(Arrays.asList(new String[]{"cha","r","act","ers"}));
        System.out.println(maxLength(arr));
    }
}
