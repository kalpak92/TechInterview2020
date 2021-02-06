package Leetcode;

/**
 * @author kalpak
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */

public class DiameterOfATree {
    static int result = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        dfsDiameterOfBinaryTree(root);
        return result;
    }

    private static int dfsDiameterOfBinaryTree(TreeNode root) {
        // Base Case
        if(root == null)
            return 0;

        // Induction step: Get the left and right max path sum to compute the current max path sum
        int leftHeight = Math.max(0, dfsDiameterOfBinaryTree(root.left));
        int rightHeight = Math.max(0, dfsDiameterOfBinaryTree(root.right));

        // update the result - longest path passes through a given node as the root node is left_height + right_height
        result = Math.max(result, leftHeight + rightHeight);

        // return back the best path, which is maximum.
        // +1 for the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        TreeNode root= new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(diameterOfBinaryTree(root));
    }
}
