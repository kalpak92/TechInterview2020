package Leetcode;

/**
 * @author kalpak
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message,
 * all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways).
 *
 * For example, "11106" can be mapped into:
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 *
 * Input: s = "0"
 * Output: 0
 * Explanation: There is no character that is mapped to a number starting with 0.
 * The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
 * Hence, there are no valid ways to decode this since all digits need to be mapped.
 * Example 4:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */

public class DecodeWays {
    public static int numDecodings(String s) {
        Integer[] dp = new Integer[s.length()];
        return decodedStringRecursive(s, dp, 0);
    }

    private static int decodedStringRecursive(String s, Integer[] dp, int currentIdx) {
        if(currentIdx >= s.length())
            return 1;
        if(s.charAt(currentIdx) == '0') // if 0 is in the middle number
            return 0;

        if(dp[currentIdx] != null)
            return dp[currentIdx];

        // A character may be decoded alone or by pairing with the next char.

        int ways1 = decodedStringRecursive(s, dp, currentIdx + 1);
        int ways2= 0;

        if(currentIdx + 2 <= s.length() && Integer.parseInt(s.substring(currentIdx, currentIdx + 2)) <= 26)
            ways2 = decodedStringRecursive(s, dp, currentIdx + 2);

        dp[currentIdx] = ways1 + ways2;

        return dp[currentIdx];
    }

    public static int decodeStringBottomsUp(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int[] dp = new int[s.length() + 1];

        dp[0] = 1;                          // empty string
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // 1st character in string

        for(int i = 2; i <= s.length(); i++) {
            if(s.charAt(i - 1) != '0')
                dp[i] += dp[i - 1];         // just append the character

            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if(twoDigit >= 10 && twoDigit <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(decodeStringBottomsUp("226"));
    }
}
