package Leetcode;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 * (A subtree of a tree is any node of that tree plus all its descendants.
 * The average value of a tree is the sum of its values, divided by the number of nodes.)
 *
 *
 * Example 1:
 * Input: [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 *
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 5000.
 * Each node will have a value between 0 and 100000.
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 *
 */

public class MaximumAverageSubtree {
    static double result = Double.MIN_VALUE;
    public static double maximumAverageSubtree(TreeNode root) {
        getMaximumAverage(root);
        return result;
    }

    private static int[] getMaximumAverage(TreeNode root) {
        // Base Case
        if(root == null)
            return new int[]{0, 0};

        int[] leftAverage = getMaximumAverage(root.left);
        int[] rightAverage = getMaximumAverage(root.right);

        int currentValue = root.val + leftAverage[0] + rightAverage[0];
        int numOfNodes = leftAverage[1] + rightAverage[1] + 1;

        result = Math.max(result, 1.0*currentValue/numOfNodes);

        return new int[]{currentValue, numOfNodes};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6);
        root.right = new TreeNode(1);

        System.out.println(maximumAverageSubtree(root));
    }
}
