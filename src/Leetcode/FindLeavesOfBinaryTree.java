package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 *
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 *
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
 *
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 100].
 * 1 <= Node.val <= 100
 *
 */

public class FindLeavesOfBinaryTree {
    private static List<List<Integer>> result;

    public static List<List<Integer>> findLeaves(TreeNode root) {
        result = new ArrayList<>();

        findLeavesDFS(root);

        return result;
    }

    private static int findLeavesDFS(TreeNode root) {
        if(root == null)
            return -1;

        int leftHeight = findLeavesDFS(root.left);
        int rightHeight = findLeavesDFS(root.right);

        int currentHeight = 1 + Math.max(leftHeight, rightHeight);

        if(result.size() == currentHeight)
            result.add(new ArrayList<>());

        result.get(currentHeight).add(root.val);

        return currentHeight;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(findLeaves(root));
    }
}
