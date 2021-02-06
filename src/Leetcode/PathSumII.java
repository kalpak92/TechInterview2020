package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
 * A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 *
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 *
 *
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 */

public class PathSumII {
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfsPathSum(result, path, root, targetSum);
        return result;
    }

    private static void dfsPathSum(List<List<Integer>> result, List<Integer> path, TreeNode root, int targetSum) {
        if(root == null)
            return;

        path.add(root.val);

        if(root.left == null && root.right == null && (targetSum - root.val) == 0) {
            result.add(new ArrayList<>(path));
        }

        dfsPathSum(result, path, root.left, targetSum - root.val);
        dfsPathSum(result, path, root.right, targetSum - root.val);

        path.remove(path.size() - 1);
        return;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        int sum = 23;

        List<List<Integer>> result = pathSum(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }

}
