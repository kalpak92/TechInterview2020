package Leetcode;

/**
 * @author kalpak
 *
 * Given the root node of a binary search tree, return the sum of values of all nodes with a value in the range [low, high].
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 *
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 2 * 104].
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * All Node.val are unique.
 *
 */

public class RangeSumBST {
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null)
            return 0;

        if(root.val < low)
            return rangeSumBST(root.right, low, high);

        if(root.val > high)
            return rangeSumBST(root.left, low, high);

        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(18);

        root.left.left.left = new TreeNode(1);

        root.left.right.left = new TreeNode(6);

        System.out.println(rangeSumBST(root, 6, 10));
    }
}
