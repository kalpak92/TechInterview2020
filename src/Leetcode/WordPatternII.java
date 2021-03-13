package Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author kalpak
 *
 * Given a pattern and a string s, return true if s matches the pattern.
 *
 * A string s matches a pattern if there is some bijective mapping of single characters to strings such that
 * if each character in pattern is replaced by the string it maps to, then the resulting string is s.
 *
 * A bijective mapping means that no two characters map to the same string, and no character maps to two different strings.
 *
 *
 * Example 1:
 * Input: pattern = "abab", s = "redblueredblue"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "red"
 * 'b' -> "blue"
 *
 * Example 2:
 * Input: pattern = "aaaa", s = "asdasdasdasd"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "asd"
 *
 * Example 3:
 * Input: pattern = "abab", s = "asdasdasdasd"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "a"
 * 'b' -> "sdasd"
 * Note that 'a' and 'b' cannot both map to "asd" since the mapping is a bijection.
 *
 * Example 4:
 * Input: pattern = "aabb", s = "xyzabcxzyabc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length, s.length <= 20
 * pattern and s consist of only lower-case English letters.
 *
 */

public class WordPatternII {
    public static boolean wordPatternMatch(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return findMatchingPattern(pattern, s, map, set, 0, 0);
    }

    private static boolean findMatchingPattern(
            String pattern, String s, Map<Character, String> map, Set<String> set, int i, int j) {
        // base case
        if (i == s.length() && j == pattern.length())
            return true;

        if (i == s.length() || j == pattern.length())
            return false;

        // get current pattern character
        char c = pattern.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String pat = map.get(c);

            // then check if we can use it to match str[i...i + pat.length()]
            if (!s.startsWith(pat, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return findMatchingPattern(pattern, s, map, set, i + pat.length(), j + 1);
        }

        // pattern character does not exist in the map
        for (int k = i; k < s.length(); k++) {
            String p = s.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (findMatchingPattern(pattern, s, map, set, k + 1, j + 1)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(wordPatternMatch("abab", "redblueredblue"));
    }
}
