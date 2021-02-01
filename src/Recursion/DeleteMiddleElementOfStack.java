package Recursion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Given a stack with push(), pop(), empty() operations, delete middle of it without using any additional data structure.
 *
 * Input  : Stack[] = [1, 2, 3, 4, 5]
 * Output : Stack[] = [1, 2, 4, 5]
 *
 * Input  : Stack[] = [1, 2, 3, 4, 5, 6]
 * Output : Stack[] = [1, 2, 4, 5, 6]
 *
 */

public class DeleteMiddleElementOfStack {
    public static Deque<Integer> deleteMiddle(Deque<Integer> stack, int size, int current) {
        if(stack.isEmpty() || current == size)
            return stack;

        int temp = stack.poll();

        stack = deleteMiddle(stack, size, current+1);

        if(current != size/2)
            stack.push(temp);
        return stack;
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(9);
        stack.push(2);
        stack.push(7);
        stack.push(4);
        stack.push(6);

        stack = deleteMiddle(stack, stack.size(), 0);   // [6, 4, 7, 2, 9]
        System.out.println(stack);
    }
}
