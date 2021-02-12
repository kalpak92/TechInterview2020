package Leetcode;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, return the number of uni-value subtrees.
 *
 * A uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example 1:
 * Input: root = [5,1,5,5,5,null,5]
 * Output: 4
 *
 * Example 2:
 * Input: root = []
 * Output: 0
 *
 * Example 3:
 * Input: root = [5,5,5,5,5,null,5]
 * Output: 6
 *
 * Constraints:
 *
 * The numbrt of the node in the tree will be in the range [0, 1000].
 * -1000 <= Node.val <= 1000
 *
 */

public class CountUnivalueSubtrees {
    static int result = 0;

    public static int countUnivalSubtrees(TreeNode root) {
        if(root == null)
            return 0;

        countUnivalSubtreesDFS(root, -1);
        return result;
    }

    private static boolean countUnivalSubtreesDFS(TreeNode root, int value) {
        if(root == null)
            return true;

        // Check for Leaf Node.
        // If leaf, then return true since leaf nodes are individually uni-value subtrees
        if(root.left == null && root.right == null) {
            result++;
            return(root.val == value ? true : false);
        }

        // Traverse the left and right subtree
        boolean left = countUnivalSubtreesDFS(root.left, root.val);
        boolean right = countUnivalSubtreesDFS(root.right, root.val);

        // Check if both of them are true, increase the result and return if the root matches the value.
        if(left == true && right == true) {
            result++;
            return (root.val == value ? true : false);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root= new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(5);

        countUnivalSubtrees(root);
        System.out.println("The number of univalue subtrees are : " + result);
    }
}
