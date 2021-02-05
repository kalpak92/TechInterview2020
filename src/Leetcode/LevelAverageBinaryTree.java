package Leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 *
 *
 */

public class LevelAverageBinaryTree {
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();

        if(root == null)
            return result;

        queue.offer(root);

        while(!queue.isEmpty()) {
            int len = queue.size();
            double average = 0;
            int itr = 0;
            while (itr < len) {
                TreeNode current = queue.poll();
                average += current.val;

                if(current.left != null)
                    queue.offer(current.left);

                if(current.right != null)
                    queue.offer(current.right);

                itr++;
            }
            average /= len;
            result.add(average);
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

        List<Double> result = averageOfLevels(root);
        System.out.print("Level averages are: " + result);
    }
}
