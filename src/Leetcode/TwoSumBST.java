package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 * Given the root of a Binary Search Tree and a target number k,
 * return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 * Example 1:
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 * Example 3:
 * Input: root = [2,1,3], k = 4
 * Output: true
 *
 * Example 4:
 * Input: root = [2,1,3], k = 1
 * Output: false
 *
 * Example 5:
 * Input: root = [2,1,3], k = 3
 * Output: true
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 */

/**
 * Time Complexity : O(n)
 * Space Complexity : O(log n)
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TwoSumBST {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;

        // Use Deque as Stack
        Deque<TreeNode> leftSide = new ArrayDeque<>();
        Deque<TreeNode> rightSide = new ArrayDeque<>();

        TreeNode temp = root;
        while(temp != null) {
            leftSide.push(temp);
            temp = temp.left;
        }

        temp = root;
        while(temp!= null) {
            rightSide.push(temp);
            temp = temp.right;
        }

        // Use 2 pointer style traversal.
        // But traverse the tree to looking for the next smaller or larger TreeNode.
        // This is applicable since this is a BST.

        TreeNode leftNode = leftSide.pop();
        TreeNode rightNode = rightSide.pop();

        while (leftNode.val != rightNode.val) {
            // left.val == right.val indicates a thorough search is completed.

            int currSum = leftNode.val + rightNode.val;
            if (currSum == k) {
                return true;
            } else if (currSum < k) {
                if (leftNode.right != null) {
                    leftNode = leftNode.right;
                    while (leftNode != null) {
                        leftSide.push(leftNode);
                        leftNode = leftNode.left;
                    }
                }
                leftNode = leftSide.pop();
            } else { // currSum > k
                if (rightNode.left != null) {
                    rightNode = rightNode.left;
                    while (rightNode != null) {
                        rightSide.push(rightNode);
                        rightNode = rightNode.right;
                    }
                }
                rightNode = rightSide.pop();
            }
        }
        return false;
    }
}
