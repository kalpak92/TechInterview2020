package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 *
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 *
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 *
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 *
 *
 */
public class WordBreakII {
    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();

        for(String word: wordDict)
            set.add(word);

        List<String> result = backtrack(set, s, map);
        return result;
    }

    private static List<String> backtrack(Set<String> set, String s, Map<String, List<String>> map) {
        // cache the strings that have been already found corresponding to the startIndex.
        if(map.containsKey(s))
            return map.get(s);

        List<String> result = new ArrayList<>();

        if(set.contains(s))
            result.add(s);

        for(int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            if(set.contains(left)) {
                List<String> subList = backtrack(set, s.substring(i), map);

                for(String nextWord : subList)
                    result.add(left + " " + nextWord);
            }
        }

        map.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("cats");
        wordDict.add("dog");

        System.out.println(wordBreak(s, wordDict));
    }
}
