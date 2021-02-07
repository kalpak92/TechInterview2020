package Leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kalpak
 *
 * Invert a binary tree.
 *
 * Example:
 *
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * Output:
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */

public class InvertBinaryTree {
    public static TreeNode invertTreeDFS(TreeNode root) {
        if(root == null)
            return null;

        TreeNode tempLeft = root.left;

        root.left = invertTreeDFS(root.right);
        root.right = invertTreeDFS(tempLeft);

        return root;
    }

    public static TreeNode invertTreeBFS(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();

        if(root == null)
            return null;

        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode tempLeft = current.left;
            current.left = current.right;
            current.right = tempLeft;

            if(current.left != null)
                queue.offer(current.left);
            if(current.right != null)
                queue.offer(current.right);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        TreeNode result = invertTreeDFS(root);
    }
}
