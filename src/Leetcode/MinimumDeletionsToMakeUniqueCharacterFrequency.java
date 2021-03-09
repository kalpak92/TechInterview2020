package Leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kalpak
 *
 * A string s is called good if there are no two different characters in s that have the same frequency.
 *
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * The frequency of a character in a string is the number of times it appears in the string.
 *
 * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 *
 * Example 2:
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 *
 * Example 3:
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 *
 */

public class MinimumDeletionsToMakeUniqueCharacterFrequency {
    public static int minDeletions(String s) {
        int[] frequencies = new int[26];
        int result = 0;
        Set<Integer> isUsed = new HashSet<>();

        // Calculate the frequencies if each characters in the string
        for(int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < frequencies.length; i++) {
            int currentFreq = frequencies[i];

            while(currentFreq > 0 && isUsed.contains(currentFreq)) {
                currentFreq--;
                result++;
            }
            isUsed.add(currentFreq);
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "aaabbbcc";
        System.out.println("The minimum number of deletions required: " + minDeletions(s));

        s = "ceabaacb";
        System.out.println("The minimum number of deletions required: " + minDeletions(s));
    }
}
