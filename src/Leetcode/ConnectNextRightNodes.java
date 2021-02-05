package Leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kalpak
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 */

class TreeNodeWithNext {
    public int val;
    public TreeNodeWithNext left;
    public TreeNodeWithNext right;
    public TreeNodeWithNext next;

    public TreeNodeWithNext() {}

    public TreeNodeWithNext(int _val) {
        val = _val;
    }

    public TreeNodeWithNext(int _val, TreeNodeWithNext _left, TreeNodeWithNext _right, TreeNodeWithNext _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

public class ConnectNextRightNodes {
    public static TreeNodeWithNext connect(TreeNodeWithNext root) {
        Deque<TreeNodeWithNext> queue = new LinkedList<>();

        if(root == null)
            return root;

        queue.offer(root);

        while(!queue.isEmpty()) {
            int len = queue.size();

            for(int i = 0; i < len; i++) {
                TreeNodeWithNext current = queue.poll();

                if(i < len-1) // not the right leaf node
                    current.next = queue.peek();

                if(current.left != null)
                    queue.offer(current.left);

                if(current.right != null)
                    queue.offer(current.right);
            }
        }
        return root;
    }

    public static TreeNodeWithNext connectRecursive(TreeNodeWithNext root) {
        doConnection(root);
        return root;
    }

    private static void doConnection(TreeNodeWithNext root) {
        // base case
        if(root == null || root.left == null)
            return;

        // At every step, connect the child nodes and recurse
        // Connect the left child to the right child
        root.left.next = root.right;

        if(root.next != null) { // this means the current level has at least two nodes
            root.right.next = root.next.left;
        }

        // recurse
        doConnection(root.left);
        doConnection(root.right);
    }

    public static void main(String[] args) {
        TreeNodeWithNext root = new TreeNodeWithNext(12);

        root.left = new TreeNodeWithNext(7);
        root.right = new TreeNodeWithNext(1);

        root.left.left = new TreeNodeWithNext(9);
        root.left.right = new TreeNodeWithNext(2);

        root.right.left = new TreeNodeWithNext(10);
        root.right.right = new TreeNodeWithNext(5);

        TreeNodeWithNext result = connect(root);
        TreeNodeWithNext resultRecursive = connectRecursive(root);
    }
}
