package Leetcode;

import com.sun.source.tree.Tree;

/**
 * @author kalpak
 *
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * Example:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 *
 */

public class ClosestValueInBST {
    public static int closestValue(TreeNode root, double target) {
        int result = root.val;
        int currentNodeVal = 0;

        // Binary Search approach
        while(root != null) {
            currentNodeVal = root.val;

            // update the result
            if(Math.abs(currentNodeVal - target) < Math.abs(result - target))
                result = currentNodeVal;

            // either go left or right
            if(target < root.val)
                root = root.left;
            else
                root = root.right;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(closestValue(root, 3.714286));

    }
}
