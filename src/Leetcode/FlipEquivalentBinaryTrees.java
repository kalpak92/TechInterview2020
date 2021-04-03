package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kalpak
 *
 * For a binary tree T, we can define a flip operation as follows:
 *  choose any node, and
 *  swap the left and right child subtrees.
 *
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 *
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivelent or false otherwise.
 *
 * Example 1:
 * Flipped Trees Diagram
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 *
 *
 * Example 2:
 * Input: root1 = [], root2 = []
 * Output: true
 *
 *
 * Example 3:
 * Input: root1 = [], root2 = [1]
 * Output: false
 *
 *
 * Example 4:
 * Input: root1 = [0,null,1], root2 = []
 * Output: false
 *
 *
 * Example 5:
 * Input: root1 = [0,null,1], root2 = [0,1]
 * Output: true
 *
 *
 * Constraints:
 *
 * The number of nodes in each tree is in the range [0, 100].
 * Each tree will have unique node values in the range [0, 99].
 *
 */

public class FlipEquivalentBinaryTrees {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root1);
        queue.offer(root2);

        while(!queue.isEmpty()) {
            TreeNode current1 = queue.poll();
            TreeNode current2 = queue.poll();

            if(current1 == null && current2 == null)
                continue;

            if(!areEqual(current1, current2))
                return false;

            if (areEqual(current1.left, current2.left) && areEqual(current1.right, current2.right)) {
                queue.offer(current1.left);
                queue.offer(current2.left);

                queue.offer(current1.right);
                queue.offer(current2.right);
            } else if (areEqual(current1.left, current2.right) && areEqual(current1.right, current2.left)) {
                queue.offer(current1.left);
                queue.offer(current2.right);

                queue.offer(current1.right);
                queue.offer(current2.left);
            } else
                return false;
        }
        return true;
    }

    private boolean areEqual(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null && root1.val == root2.val)
            return true;
        else
            return false;
    }

    public boolean flipEquivRecursive(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2 == null;
        if (root2 == null) return root1 == null;
        if (root1.val != root2.val) return false;
        return (flipEquivRecursive(root1.left, root2.left) && flipEquivRecursive(root1.right, root2.right)) || (
                flipEquivRecursive(root1.left, root2.right) && flipEquivRecursive(root1.right, root2.left)
        );
    }
}
