package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 *
 * Example 1:
 *
 * Input: String="araaci", K=2
 * Output: 4
 * Explanation: The longest substring with no more than '2' distinct characters is "araa".
 * Example 2:
 *
 * Input: String="araaci", K=1
 * Output: 2
 * Explanation: The longest substring with no more than '1' distinct characters is "aa".
 * Example 3:
 *
 * Input: String="cbbebi", K=3
 * Output: 5
 * Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
 *
 */

public class LongestSubstringWithKDistinctCharacters {
    public static int longestSubstringWithKDistinctCharacters(String str, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int result = Integer.MIN_VALUE;
        int i =0;
        int j = 0;

        while(j < str.length()) {
            map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);
            if(map.size() < k) {
                j++;
            } else  if(map.size() == k) {
                result = Math.max(result, j - i + 1);
                j++;
            } else if(map.size() > k) {
                while(map.size() > k) {
                    map.put(str.charAt(i), map.get(str.charAt(i)) - 1);
                    if(map.get(str.charAt(i)) == 0)
                        map.remove(str.charAt(i));
                    i++;
                }
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String str1 = "cbbebi";
        System.out.println("The longest substring with distinct letters is : " + longestSubstringWithKDistinctCharacters(str1, 3));
        String str2 = "aabacbebebe";
        System.out.println("The longest substring with distinct letters is : " + longestSubstringWithKDistinctCharacters(str2, 3));
    }
}
