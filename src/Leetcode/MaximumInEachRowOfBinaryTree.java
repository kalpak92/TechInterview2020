package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 *
 * Example 1:
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]
 *
 * Example 2:
 * Input: root = [1,2,3]
 * Output: [1,3]
 *
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 *
 * Example 4:
 * Input: root = [1,null,2]
 * Output: [1,2]
 *
 * Example 5:
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [0, 104].
 * -2^31 <= Node.val <= 2^31 - 1
 */

public class MaximumInEachRowOfBinaryTree {
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();

        if(root == null)
            return result;

        queue.offer(root);
        while(!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int len = queue.size();

            while(len > 0) {
                TreeNode current = queue.poll();
                max = Math.max(max, current.val);

                if(current.left != null)
                    queue.offer(current.left);
                if(current.right != null)
                    queue.offer(current.right);

                len--;
            }
            result.add(max);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        System.out.println(largestValues(root));
    }
}
