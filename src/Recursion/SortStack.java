package Recursion;

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

        int temp = stack.poll(); // store the top element

        stack = sortStack(stack);  // recursively call the sort function on the remaining stack
        insertElement(stack, temp); // insert the element at the correct position

        return stack;
    }

    private static Deque<Integer> insertElement(Deque<Integer> stack, int element) {
        // push the element if the top is smaller than the element to be inserted
        if (stack.isEmpty() || stack.peek() < element) {
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
        stack.push(9);
        stack.push(2);
        stack.push(7);
        stack.push(4);
        stack.push(6);

        System.out.println("Before Sorting: " + stack);
        stack = sortStack(stack);
        System.out.println("After Sorting: " + stack);
    }
}
