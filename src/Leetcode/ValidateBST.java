package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -2^31 <= Node.val <= 2^31 - 1
 *
 */

public class ValidateBST {
    public static boolean isValidBST(TreeNode root) {
        return doBSTValidation(root, null, null);
    }

    private static boolean doBSTValidation(TreeNode root, Integer low, Integer high) {
        if(root == null)
            return true;

        if ((low != null && root.val <= low) || (high != null && root.val >= high))
            return false;

        return doBSTValidation(root.left, low, root.val) && doBSTValidation(root.right, root.val, high);
    }

    public static boolean isValidBSTIterative(TreeNode root) {
        if(root == null)
            return true;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        TreeNode prev = null;

        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if(prev != null && current.val <= prev.val)
                return false;
            prev = current;
            current = current.right;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println(isValidBST(root));
        System.out.println(isValidBSTIterative(root));
    }
}
