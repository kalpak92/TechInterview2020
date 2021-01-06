package Leetcode;

/**
 * @author kalpak
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 *
 * Constraints:
 *
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 */

public class MinStack {
    private Node element;
    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if(element == null)
            element = new Node(x, x, null);
        else
            element = new Node(x, Math.min(element.minVal, x), element);
    }

    public void pop() {
        element = element.next;
    }

    public int top() {
        return element.val;
    }

    public int getMin() {
        return element.minVal;
    }

    private class Node {
        private int val;
        private int minVal;
        private Node next;

        private Node(int val, int minVal, Node next) {
            this.val = val;
            this.minVal = minVal;
            this.next = next;
        }
    }
}
