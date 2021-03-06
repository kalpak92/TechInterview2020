package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        if (T.length == 0)
            return result;

        // Store index of temperatures whose value is in descending order
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < T.length; i++) {
            // if the current temperature is greater than the top of stack,
            // it means we find a warmer temperature.
            // Pop the element from the stack and update result
            while (!stack.isEmpty() && T[i] > T[stack.peekFirst()]) {
                int topIndex = stack.peek();
                stack.pollFirst();
                result[topIndex] = i - topIndex;    // output needs the range, i.e the distance to the next greater temperature
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);

        for (int i : result)
            System.out.print(i + " ");
        System.out.println();
    }
}
