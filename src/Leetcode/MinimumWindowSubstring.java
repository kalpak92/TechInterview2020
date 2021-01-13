package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t.
 * If there is no such window in s that covers all characters in t, return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 105
 * s and t consist of English letters.
 *
 *
 * Follow up: Could you find an algorithm that runs in O(n) time?
 */

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if(t.length() > s.length())
            return "";

        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int counter = 0;
        int length = Integer.MAX_VALUE;
        String result = "";

        for(char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        counter = map.size();

        while(j < s.length()) {
            // Calculation : Remove the character's counter from the map
            char c = s.charAt(j);
            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0)
                    counter--;
            }
            // increase j
            j++;

            while(counter == 0) {   // we have a window with all the characters of t
                // Update the result
                if(j - i < length) {
                    length = j - i;
                    result = s.substring(i, j);
                }

                // Slide the window from the left
                char temp = s.charAt(i);
                if(map.containsKey(temp)) {
                    map.put(temp, map.get(temp) +1);
                    if(map.get(temp) > 0)
                        counter++;
                }
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));

        s = "a";
        t = "a";
        System.out.println(minWindow(s, t));
    }
}
