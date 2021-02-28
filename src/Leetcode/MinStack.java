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

class MinStack {
    private Node head;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if(head == null)
            head = new Node(x, x);
        else
            head = new Node(x, Math.min(head.minVal, x), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.minVal;
    }

    class Node {
        int val;
        int minVal;
        Node next;

        public Node(int val, int minVal) {
            this.val = val;
            this.minVal = minVal;
            next = null;
        }

        public Node(int val, int minVal, Node next) {
            this.val = val;
            this.minVal = minVal;
            this.next = next;
        }
    }
}
