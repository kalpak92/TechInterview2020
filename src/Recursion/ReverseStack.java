package Recursion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Reverse a stack without using Extra memory.
 *
 */

public class ReverseStack {
    public static Deque<Integer> reverseStack(Deque<Integer> stack) {
        if(stack.isEmpty())
            return stack;

        int temp = stack.poll();
        stack = reverseStack(stack);
        insertElement(stack, temp);

        return stack;
    }

    private static Deque<Integer> insertElement(Deque<Integer> stack, int element) {
        if (stack.isEmpty()) {
            stack.push(element);
            return stack;
        }

        // Recursively store the top element and try to insert the element in the remaining stack
        int top = stack.poll();
        stack = insertElement(stack, element);
        stack.push(top);
        return stack;
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Before Reverse: " + stack);

        stack = reverseStack(stack);
        System.out.println("After Reverse: " +stack);
    }
}
