package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Sort a given Stack.
 *
 */

public class SortStack {
    public static Deque<Integer> sortStack(Deque<Integer> stack) {
        if(stack.isEmpty())
            return stack;

        int temp = stack.poll();

        stack = sortStack(stack);
        insertElement(stack, temp);
        return stack;
    }

    private static Deque<Integer> insertElement(Deque<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() < element) {
            stack.push(element);
            return stack;
        }

        int top = stack.poll();
        stack = insertElement(stack, element);
        stack.push(top);
        return stack;
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(9);
        stack.push(2);
        stack.push(7);
        stack.push(4);
        stack.push(6);

        stack = sortStack(stack);
        System.out.println(stack);
    }
}
