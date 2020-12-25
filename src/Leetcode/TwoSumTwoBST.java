package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 *
 * Example 1:
 * Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
 * Output: true
 * Explanation: 2 and 3 sum up to 5.
 *
 * Example 2:
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 * Output: false
 *
 *
 * Constraints:
 *
 * Each tree has at most 5000 nodes.
 * -10^9 <= target, node.val <= 10^9
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Time Complexity : O(n1 + n2)
// Space Complexity : O(h1 + h2)

public class TwoSumTwoBST {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null)
            return false;

        // stack 'tree1' used for the inorder traversal of root1
        // stack 'tree2' used for the reverse inorder traversal of root2
        Deque<TreeNode> tree1 = new ArrayDeque<>();
        Deque<TreeNode> tree2 = new ArrayDeque<>();

        TreeNode t1, t2;

        while(true) {
            while(root1 != null) {
                tree1.push(root1);
                root1 = root1.left;
            }

            while(root2 != null) {
                tree2.push(root2);
                root2 = root2.right;
            }

            if (tree1.isEmpty() || tree2.isEmpty())
                break;

            t1 = tree1.peek();
            t2 = tree2.peek();

            if(t1.val + t2.val == target)
                return true;
            else if (t1.val + t2.val < target) {
                // move to next node in inorder traversal
                tree1.pop();
                root1 = t1.right;
            } else {
                // move to next node in reverse inorder traversal
                tree2.pop();
                root2 = t2.left;
            }
        }
        return false;
    }
}
