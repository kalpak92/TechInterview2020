package Leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
 * then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 */
public class ZigzagLevelOrderTraversalBinaryTree {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        boolean isOrder = true;

        if(root == null)
            return result;

        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();

            while(len > 0) {
                TreeNode current = queue.poll();

                if(isOrder)
                    temp.add(current.val);
                else
                    temp.add(0, current.val);

                if(current.left != null)
                    queue.offer(current.left);
                if(current.right != null)
                    queue.offer(current.right);

                len--;
            }
            isOrder = !(isOrder);
            result.add(temp);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);

        List<List<Integer>> result = zigzagLevelOrder(root);
        System.out.println("Zigzag traversal: " + result);
    }
}
