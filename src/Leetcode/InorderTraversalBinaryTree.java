package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 *
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 *
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Example 4:
 * Input: root = [1,2]
 * Output: [2,1]
 *
 *
 * Example 5:
 * Input: root = [1,null,2]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up:
 *
 * Recursive solution is trivial, could you do it iteratively?
 */

public class InorderTraversalBinaryTree {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        doInorderTraversal(root, result);
        return result;
    }

    private static void doInorderTraversal(TreeNode root, List<Integer> result) {
        // Base case
        if(root == null)
            return;

        // Inorder traversal : Left -> Root -> Right
        doInorderTraversal(root.left, result);
        result.add(root.val);
        doInorderTraversal(root.right, result);
        return;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(inorderTraversal(root));
    }
}
