package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * - Each node has either 0 or 2 children;
 * - The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 *      (Recall that a node is a leaf if and only if it has 0 children.)
 * - The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 *
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
 * It is guaranteed this sum fits into a 32-bit integer.
 *
 *
 *
 * Example 1:
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
 *
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 *
 */

/**
 * Since the given leaf nodes are result of inorder traversal,
 * we know there will be pivots that divide arr into left and right,
 *      nodes in the left build the left subtree and nodes in the right build the right subtree.
 *
 * For each subtree, if we know the minimum sum, we can use it to build the result of the parent tree.
 * So the problem can be divided into sub-problems,
 *
 * We have the following general transition equation :
 *      res(i, j) means the minimum non-leaf nodes sum with leaf nodes from arr[i] to arr[j]):
 *
 *      for k from i to j
 *              res(i, j) = min(res(i, k) + res(k + 1, j) + max(arr[i] ... arr[k]) * max(arr[k + 1] ... arr[j]))
 */
public class MinCostTreeFromLeafValues {
    static int[][] dp;
    public static int mctFromLeafValues(int[] arr) {
        dp = new int[arr.length + 1][arr.length + 1];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        return findMinimumCostTree(arr, 0, arr.length - 1);
    }

    private static int findMinimumCostTree(int[] arr, int start, int end) {
        if(dp[start][end] != -1)
            return dp[start][end];

        if(start >= end)
            return 0;

        int result = Integer.MAX_VALUE;

        for(int i = start; i < end; i++) {
            int rootVal = max(arr, start, i) * max(arr, i + 1, end);

            int nonLeafNodeFromLeftSubtree = findMinimumCostTree(arr, start, i);
            int nonLeafNodeFromRightSubtree = findMinimumCostTree(arr, i + 1, end);

            result = Math.min(result, rootVal + nonLeafNodeFromLeftSubtree + nonLeafNodeFromRightSubtree);
        }

        dp[start][end] = result;
        return result;
    }

    private static int max(int[] arr, int start, int end) {
        int max = 0;

        for(int i = start; i <= end; i++) {
            max = Math.max(max, arr[i]);
        }

        return max;
    }
}
