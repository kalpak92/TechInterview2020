package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 */

public class WordBreak {
    public static boolean wordBreakRecursive(String s, List<String> wordDict) {
        // Check if given string s already present in dictionary
        if(wordDict.contains(s))
            return true;

        // recursively check for each substring
        for(int i = 1; i <= s.length(); i++) {
            String leftWord = s.substring(0, i);
            if(wordDict.contains(leftWord) && wordBreakRecursive(s.substring(i), wordDict))
                return true;
        }
        return false;
    }

    public static boolean wordBreakTopDown(String s, List<String> wordDict) {
        // Check if given string s already present in dictionary
        if(wordDict.contains(s))
            return true;

        Map<String, Boolean> map = new HashMap<>();

        return checkWordBreak(map, s, wordDict);
    }

    private static boolean checkWordBreak(Map<String, Boolean> map, String s, List<String> wordDict) {
        // Check in dictionary
        if(wordDict.contains(s))
            return true;

        // Check if the string s is already computed
        if(map.containsKey(s))
            return map.get(s);

        // recursively check for each substring
        for(int i = 1; i <= s.length(); i++) {
            String leftWord = s.substring(0, i);
            if(wordDict.contains(leftWord) && checkWordBreak(map, s.substring(i), wordDict)) {
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }

    public static boolean wordBreakBottomsUp(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;                           // null string present in the dictionary

        for(int i = 1; i <= s.length(); i++) {
            // i refers to length of substring s(0..i) in consideration
            for(int j = 0; j < i; j++) {
                // j refers to index partitioning s(0..i) into s1(0..j) and s2(j+1..i)
                if(dp[j] && set.contains(s.substring(j, i))) {
                    // if both s1 and s2 fulfils the criteria, mark s(0,i) to true
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(wordBreakRecursive("applepenapple", new ArrayList<>(Arrays.asList("apple", "pen"))));
        System.out.println(wordBreakTopDown("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));
        System.out.println(wordBreakBottomsUp("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));
    }
}
