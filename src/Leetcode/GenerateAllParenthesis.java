package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class GenerateAllParenthesis {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, String str, int open, int close, int n) {
        if(str.length() == 2*n) {
            result.add(str);
            return;
        }

        if(open < n)
            backtrack(result, str+"(", open+1, close, n);

        if(close < open)
            backtrack(result, str+")", open, close+1, n);
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
