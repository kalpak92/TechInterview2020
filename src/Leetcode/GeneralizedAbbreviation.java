package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * A word's generalized abbreviation can be constructed by taking any number of non-overlapping substrings and replacing them with their respective lengths.
 * For example, "abcde" can be abbreviated into "a3e" ("bcd" turned into "3"), "1bcd1" ("a" and "e" both turned into "1"),
 * and "23" ("ab" turned into "2" and "cde" turned into "3").
 *
 * Given a string word, return a list of all the possible generalized abbreviations of word. Return the answer in any order.
 *
 * Example 1:
 * Input: word = "word"
 * Output: ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
 *
 *
 * Example 2:
 * Input: word = "a"
 * Output: ["1","a"]
 *
 * Constraints:
 *
 * 1 <= word.length <= 15
 * word consists of only lowercase English letters.
 */

public class GeneralizedAbbreviation {
    public static List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();

        backtrack(result, 0, word, new StringBuilder(), 0);
        return result;
    }

    private static void backtrack(List<String> result, int position, String word, StringBuilder current, int count) {
        if (position == word.length()) {
            // end o word
            result.add(current.toString() + (count==0?"":count));
            return;
        }

        // for backtrack purpose
        int len = current.length();

        // abbreviate current character: word[position]
        backtrack(result, position + 1, word, current, count + 1);

        // keep current character: word[position]
        if (count != 0)
            current.append(count);

        backtrack(result, position + 1, word, current.append(word.charAt(position)), 0);

        current.setLength(len);
    }

    public static void main(String[] args) {
        System.out.println(generateAbbreviations("word"));
    }
}
