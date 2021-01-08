package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s representing an expression, implement a basic calculator to evaluate it.
 *
 * Example 1:
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 */

public class BasicCalculator {
    public static int calculate(String s) {
        int result = 0;
        int tempNum = 0;
        int sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                tempNum = 10 * tempNum + (int)(c - '0');
            } else if (c == '+') {
                result += sign*tempNum;
                tempNum = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign*tempNum;
                tempNum = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                // Reset the sign and value
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign*tempNum;
                tempNum = 0;
                // Accumulate the already cached result
                result *= stack.pop();
                result += stack.pop();
            }
        }
        if(tempNum != 0)
            result += sign*tempNum;

        return result;
    }

    public static void main(String[] args) {
        String str = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(str));
        str = "34";
        System.out.println(calculate(str));
    }
}
