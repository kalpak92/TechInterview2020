package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 */

public class PathSumIII {
    public static int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();
        // cumulative sum can be zero because of negative numbers
        map.put(0, 1);

        int result = dfsPathSum(root, map, 0, sum);
        return result;
    }

    private static int dfsPathSum(TreeNode root, Map<Integer, Integer> map, int runningSum, int target) {
        if(root == null)
            return 0;

        runningSum += root.val;

        // get the number of valid paths from top to current node
        int numOfPathsTillCurrentNode = map.getOrDefault(runningSum - target, 0);

        // add the current runningSum
        map.put(runningSum, map.getOrDefault(runningSum, 0) + 1);


        // Inductively, compute the same for left and right child
        int numOfPathsFromLeft = dfsPathSum(root.left, map, runningSum, target);
        int numOfPathsFromRight = dfsPathSum(root.right, map, runningSum, target);

        // remove the current entry map before going up
        map.put(runningSum, map.get(runningSum) - 1);

        return numOfPathsTillCurrentNode + numOfPathsFromLeft + numOfPathsFromRight;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        root.right.left = null;
        root.right.right = new TreeNode(11);

        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);

        root.left.right.right = new TreeNode(1);

        System.out.println("The number of paths with given sum is: " + pathSum(root, 8));
    }
}
