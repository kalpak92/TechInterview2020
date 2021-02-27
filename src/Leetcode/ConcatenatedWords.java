package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 * Example 1:
 * Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Example 2:
 * Input: words = ["cat","dog","catdog"]
 * Output: ["catdog"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 10^4
 * 0 <= words[i].length <= 1000
 * words[i] consists of only lowercase English letters.
 * 0 <= sum(words[i].length) <= 6 * 10^5
 *
 */

public class ConcatenatedWords {
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> wordDict = new HashSet<>();     // List of shorter words

        Arrays.sort(words, (a, b) -> (a.length() - b.length()));


        for(int i = 0; i< words.length; i++){
            if(checkWordBreak(new HashMap<>(), words[i], wordDict))
                result.add(words[i]);

            wordDict.add(words[i]);
        }
        return result;

    }

    private static boolean checkWordBreak(Map<String, Boolean> dp, String s, Set<String> wordDict) {
        if(wordDict.isEmpty())
            return false;

        // Check in dictionary
        if(wordDict.contains(s))
            return true;

        // Check if the string s is already computed
        if(dp.containsKey(s))
            return dp.get(s);

        // recursively check for each substring
        for(int i = 1; i <= s.length(); i++) {
            String leftWord = s.substring(0, i);
            if(wordDict.contains(leftWord) && checkWordBreak(dp, s.substring(i), wordDict)) {
                dp.put(s, true);
                return true;
            }
        }
        dp.put(s, false);
        return false;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        System.out.println(findAllConcatenatedWordsInADict(words));
    }
}
