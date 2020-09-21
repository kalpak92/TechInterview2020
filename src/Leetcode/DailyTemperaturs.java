package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperaturs {
    public static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        if(T.length == 0)
            return result;

        // Store index of temperatures whose value is in descreding order
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < T.length; i++) {
            // if the current temperate is greater than the top of stack,
            // it means we find a warmer temperature.
            // Pop the element from the stack and update result
            while(!stack.isEmpty() && T[i] > T[stack.peekFirst()]) {
                int topIndex = stack.peek();
                stack.pollFirst();
                result[topIndex] = i - topIndex;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = new int[]{73,74,75,71,69,72,76,73};
        int[] result = dailyTemperatures(temperatures);

        for(int i : result)
            System.out.print(i + " ");
        System.out.println();
    }
}
