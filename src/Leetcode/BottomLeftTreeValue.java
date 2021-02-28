package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,null,5,6,null,null,7]
 * Output: 7
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -2^31 <= Node.val <= 2^31 - 1
 *
 */

public class BottomLeftTreeValue {
    public static int findBottomLeftValue(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode result = null;

        while(!queue.isEmpty()) {
            int size = queue.size();

            TreeNode current = queue.poll();

            if(current.right != null)
                queue.offer(current.right);

            if(current.left != null)
                queue.offer(current.left);

            result = current;
        }
        return result.val;
    }
}
