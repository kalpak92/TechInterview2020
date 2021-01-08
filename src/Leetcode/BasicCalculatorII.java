package Leetcode;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */

public class BasicCalculatorII {
    public static int calculate(String s) {
        int result = 0;
        int number = 0;
        int tempSum = 0;
        char operator = '+';

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c))
                number = number * 10 + (int)(c - '0');

            if(i == s.length() -1 || (!Character.isDigit(c) && c != ' ')) {
                switch(operator) {
                    case '+':
                        result += tempSum;
                        tempSum = number;
                        break;
                    case '-':
                        result += tempSum;
                        tempSum = -number;
                        break;
                    case '*':
                        tempSum *= number;
                        break;
                    case '/':
                        tempSum /= number;
                        break;
                }
                operator = c;
                number = 0;
            }
        }
        result += tempSum;
        return result;
    }

    public static void main(String[] args) {
        String str = "3 + 5 /2";
        System.out.println(calculate(str));
    }
}
