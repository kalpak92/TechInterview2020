package Leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 */

public class RightSideViewBinaryTree {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();

        if(root == null)
            return result;

        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();

            while(len > 0) {
                TreeNode current = queue.poll();

                if(current.left != null)
                    queue.offer(current.left);
                if(current.right != null)
                    queue.offer(current.right);

                if(len == 1)
                    result.add(current.val);
                len--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);

        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        List<Integer> result = rightSideView(root);
        System.out.print("Right Side View of the tree is: " + result);
    }
}
