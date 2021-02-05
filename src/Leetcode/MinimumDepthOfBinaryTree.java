package Leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kalpak
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 *
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 *
 */

public class MinimumDepthOfBinaryTree {
    public static int minDepth(TreeNode root) {
        int result = 0;
        Deque<TreeNode> queue = new LinkedList<>();

        if(root == null)
            return result;

        queue.add(root);

        while(!queue.isEmpty()) {
            int len = queue.size();
            result++;

            while(len > 0) {
                TreeNode current = queue.poll();

                if(current.left == null && current.right == null)
                    return result;

                if(current.left != null)
                    queue.offer(current.left);
                if(current.right != null)
                    queue.offer(current.right);

                len--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        System.out.println("Tree Minimum Depth: " + minDepth(root));

        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);

        System.out.println("Tree Minimum Depth: " + minDepth(root));
    }
}
