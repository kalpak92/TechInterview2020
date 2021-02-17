package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kalpak
 *
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 *
 * Example 2:
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 *
 * Example 3:
 * Input: ")("
 * Output: [""]
 *
 */

public class RemoveInvalidParenthesis {
    public static List<String> removeInvalidParentheses(String s) {
        Set<String> result = new HashSet<>();
        int left = 0;
        int right = 0;
        int count = 0;

        // Limit max removal left and right for backtracking boundary.
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left > 0)
                    left--;
                else
                    right++;
            }
        }


        // Find how many min. no. of changes are required
        count = left + right;

        findValidParenthesisDFS(s, 0, result, new StringBuilder(""), count);

        return new ArrayList<String>(result);
    }

    private static void findValidParenthesisDFS(String s, int index, Set<String> result, StringBuilder sb, int count) {
        if (count < 0) {
            return;
        }

        if (index == s.length()) {
            if (count == 0) {
                if (isValid(sb.toString())) {
                    result.add(sb.toString());
                }
            }
            return;
        }

        char ch = s.charAt(index);
        int len = sb.length();

        if (ch == '(' || ch == ')') {
            findValidParenthesisDFS(s, index + 1, result, sb, count - 1);		        // not use ')''('
            findValidParenthesisDFS(s, index + 1, result, sb.append(ch), count);        // use
        } else {
            findValidParenthesisDFS(s, index + 1, result, sb.append(ch), count);	    // just append it
        }

        sb.setLength(len);     // backtrack
    }

    private static boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0)
                return false;
        }

        return count == 0;
    }

    public static void main(String[] args) {
        String str1 = "()())()";
        System.out.println(removeInvalidParentheses(str1));

        String str2 = "(a)())()";
        System.out.println(removeInvalidParentheses(str2));
    }
}
