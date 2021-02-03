package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Constraints:
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */

public class LetterCombinationsOfPhoneNumber {
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if(digits == null || digits.length() == 0)
            return result;

        String[] keyboardMap = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder current = new StringBuilder();

        backtrack(result, digits, current, 0, keyboardMap);
        return result;
    }

    private static void backtrack(List<String> result, String digits, StringBuilder current, int index, String[] keyboardMap) {
        // base condition
        if(index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get the letters corresponding to the digit at given index
        String letters = keyboardMap[digits.charAt(index) - '0'];

        // iterate the these letters and recursively build the necessary combinations
        for(int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));
            backtrack(result, digits, current, index+1, keyboardMap);
            current.setLength(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("78"));
    }
}
