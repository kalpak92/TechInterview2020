package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
 *
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 * Example 1:
 * Input: [6,0,8,2,1,5]
 * Output: 4
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 *
 * Example 2:
 * Input: [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 *
 *
 * Note:
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 *
 */

public class MaximumWidthRamp {
    public static int maxWidthRamp(int[] A) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;

        for (int i = 0; i < A.length; i++)
            if (stack.isEmpty() || A[stack.peek()] > A[i])
                stack.push(i);

        for (int i = A.length - 1; i > result; i--) {
            while (!stack.isEmpty() && A[stack.peek()] <= A[i])
                result = Math.max(result, i - stack.pop());
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,0,8,2,1,5};
        System.out.println("The maximum width is : " + maxWidthRamp(arr));
    }
}
