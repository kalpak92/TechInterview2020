package Leetcode;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value.
 * This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input: root = [5,4,5,1,1,5]
 * Output: 2
 *
 * Example 2:
 * Input: root = [1,4,5,4,4,5]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 * The depth of the tree will not exceed 1000.
 *
 */

public class LongestUnivaluePath {
    static int result = 0;
    public static int longestUnivaluePath(TreeNode root) {
        findLongestUnivaluePathDFS(root, -1);
        return result;
    }

    private static int findLongestUnivaluePathDFS(TreeNode root, int value) {
        if(root == null)
            return 0;

        int left = findLongestUnivaluePathDFS(root.left, root.val);
        int right = findLongestUnivaluePathDFS(root.right, root.val);

        result = Math.max(result, left + right);

        if(root.val == value) {
            return 1 + Math.max(left, right);
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(5);

        longestUnivaluePath(root);
        System.out.println(result);
    }
}
