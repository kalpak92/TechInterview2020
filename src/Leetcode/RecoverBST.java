package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 *
 * Example 1:
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 *
 *
 * Example 2:
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 1000].
 * -2^31 <= Node.val <= 2^31 - 1
 */

public class RecoverBST {
    public static void recoverTree(TreeNode root) {
        TreeNode firstElement = null;
        TreeNode secondElement = null;
        TreeNode prev = new TreeNode(Integer.MIN_VALUE);    // to avoid null pointer exception
        TreeNode current = root;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            if(firstElement == null && prev.val > current.val)
                firstElement = prev;

            if(firstElement != null && prev.val > current.val)
                secondElement = current;

            prev = current;
            current = current.right;
        }

        // Swap the nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private static void printPreorder(TreeNode root) {
        if(root == null) {
            System.out.print("null ");
            return;
        }

        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = null;

        root.left.left = null;
        root.left.right = new TreeNode(2);

        recoverTree(root);
        printPreorder(root);
    }
}
