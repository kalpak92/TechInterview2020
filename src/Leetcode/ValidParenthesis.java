package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 * Example 4:
 *
 * Input: s = "([)]"
 * Output: false
 * Example 5:
 *
 * Input: s = "{[]}"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */

public class ValidParenthesis {
    public static boolean isValid(String s) {
        if(s.length() % 2 == 1)
            return false;

        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case '(' : stack.push(')');
                    break;
                case '[' : stack.push(']');
                    break;
                case '{' : stack.push('}');
                    break;
                case ')':
                case '}':
                case ']':
                    if(stack.isEmpty() || stack.pop() != s.charAt(i))
                        return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "{[]}";
        System.out.println(isValid(str));
    }
}
