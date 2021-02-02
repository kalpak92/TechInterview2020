package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 *
 * Return a list of all possible strings we could create. You can return the output in any order.
 *
 *
 * Example 1:
 * Input: S = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 *
 * Example 2:
 * Input: S = "3z4"
 * Output: ["3z4","3Z4"]
 *
 * Example 3:
 * Input: S = "12345"
 * Output: ["12345"]
 *
 * Example 4:
 * Input: S = "0"
 * Output: ["0"]
 *
 *
 * Constraints:
 *
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 */
public class LetterCasePermutation {
    public static List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        backtrack(result, 0, S.toCharArray());
        return result;
    }

    private static void backtrack(List<String> result, int index, char[] str) {
        if(index == str.length) {
            result.add(new String(str));
            return;
        }

        if(Character.isLetter(str[index])) {
            str[index] = Character.toUpperCase(str[index]);
            backtrack(result, index+1, str);
            str[index] = Character.toLowerCase(str[index]);
            backtrack(result, index+1, str);
        } else {
            backtrack(result, index+1, str);
        }
    }

    public static void main(String[] args) {
        String str = "a1b2";
        System.out.println(letterCasePermutation(str));
    }
}
