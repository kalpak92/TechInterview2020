package Leetcode;

/**
 * @author kalpak
 *
 * Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 *
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 *
 *
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: false
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */

public class PathSum {
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null)
            return false;

        // Check condition: Leaf node and targetSum has reached;
        if(root.left == null && root.right == null && (targetSum - root.val) == 0)
            return true;

        // Induction step
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);


        System.out.println("Tree has path: " + hasPathSum(root, 23));
        System.out.println("Tree has path: " + hasPathSum(root, 16));
    }
}
