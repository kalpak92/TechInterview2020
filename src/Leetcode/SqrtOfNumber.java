package Leetcode;

/**
 * @author kalpak
 *
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 *
 *
 * Example 1:
 * Input: x = 4
 * Output: 2
 *
 *
 * Example 2:
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 *
 * Constraints:
 *
 * 0 <= x <= 2^31 - 1
 */

public class SqrtOfNumber {
    public static int mySqrt(int x) {
        if(x < 2) {
            return x;
        }

        int left = 1;
        int right = x;

        int result = 0;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(mid > x/mid) {
                // this means mid^2 > x, so reduce the search range.
                // avoid squaring mid to avoid overflow.
                right = mid - 1;
            }
            else {
                left = mid + 1;
                result = mid;   // possible candidate. But can be better.
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(26));
    }
}
