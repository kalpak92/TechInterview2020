package Leetcode;

/**
 * @author kalpak
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 *
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */

public class MaximumPathSumBinaryTree {
    static int result = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        dfsMaxPathSum(root);
        return result;
    }

    private static int dfsMaxPathSum(TreeNode root) {
        // Base Case
        if(root == null)
            return 0;

        // Induction step: Get the left and right max path sum to compute the current max path sum
        int leftMaxSum = Math.max(0, dfsMaxPathSum(root.left));
        int rightMaxSum = Math.max(0, dfsMaxPathSum(root.right));

        // update the result - max path sum for this node
        result = Math.max(result, root.val + leftMaxSum + rightMaxSum);

        // return back the best path, which is maximum
        return root.val + Math.max(leftMaxSum, rightMaxSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxPathSum(root));
    }
}
