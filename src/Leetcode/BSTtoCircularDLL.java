package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
 * For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 *
 * We want to do the transformation in place.
 * After the transformation, the left pointer of the tree node should point to its predecessor,
 * and the right pointer should point to its successor.
 *
 * You should return the pointer to the smallest element of the linked list.
 *
 *
 * Example 1:
 * Input: root = [4,2,5,1,3]
 * Output: [1,2,3,4,5]
 *
 * Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 *
 *
 * Example 2:
 * Input: root = [2,1,3]
 * Output: [1,2,3]
 *
 *
 * Example 3:
 * Input: root = []
 * Output: []
 * Explanation: Input is an empty tree. Output is also an empty Linked List.
 *
 *
 * Example 4:
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * -1000 <= Node.val <= 1000
 * Node.left.val < Node.val < Node.right.val
 * All values of Node.val are unique.
 * 0 <= Number of Nodes <= 2000
 */

public class BSTtoCircularDLL {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    public static Node treeToDoublyList(Node root) {
        if (root == null)
            return root;

        Node dummy = new Node(-10001);
        Node prev = dummy;

        Node current = root;

        Deque<Node> stack = new ArrayDeque<>();

        // Do inorder traversal
        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            Node temp = stack.pop();    // get the leftmost node
            current = temp.right;       // make current to traverse it right node

            // Create connections for the poped node.
            temp.left = prev;
            prev.right = temp;
            prev = temp;
        }

        // Circular connection for the head and tail of the circular DLL
        prev.right = dummy.right;
        dummy.right.left = prev;

        return dummy.right;
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        Node result = treeToDoublyList(root);
        Node current = result;

        while(current.right != result) {
            System.out.print(current.val + " ");
            current = current.right;
        }
        System.out.println(current.val);

    }
}
