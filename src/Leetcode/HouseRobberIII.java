package Leetcode;

/**
 * @author kalpak
 *
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 *
 * Example 2:
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 */

public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] result = robHelper(root); // 0 : root not robbed ; 1 : root robbed

        return Math.max(result[0], result[1]);
    }

    private int[] robHelper(TreeNode root) {
        if(root == null)
            return new int [2];

        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);

        int[] result = new int[2];

        // Max amount of money that can be robbed if the root is not robbed
        // which is sum of the larger of rob(root.left) and rob(root.right)
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // Max amount of money that can be robber if the root is robbed.
        // which is sum of the root + Max money on not robbing left child, i.e left[0] and max money on not robbing right child, i.e right[0]
        result[1] = root.val + left[0] + right[0];

        return result;
    }
}
