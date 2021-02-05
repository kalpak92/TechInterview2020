package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kalpak
 *
 * A binary tree is named Even-Odd if it meets the following conditions:
 *
 * The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 * For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 * For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 *
 *
 * Example 1:
 * Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * Output: true
 * Explanation: The node values on each level are:
 * Level 0: [1]
 * Level 1: [10,4]
 * Level 2: [3,7,9]
 * Level 3: [12,8,6,2]
 * Since levels 0 and 2 are all odd and increasing, and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
 *
 *
 * Example 2:
 * Input: root = [5,4,2,3,3,7]
 * Output: false
 * Explanation: The node values on each level are:
 * Level 0: [5]
 * Level 1: [4,2]
 * Level 2: [3,3,7]
 * Node values in the level 2 must be in strictly increasing order, so the tree is not Even-Odd.
 *
 *
 * Example 3:
 * Input: root = [5,9,1,3,5,7]
 * Output: false
 * Explanation: Node values in the level 1 should be even integers.
 *
 *
 * Example 4:
 * Input: root = [1]
 * Output: true
 *
 *
 * Example 5:
 * Input: root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 * Output: true
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^6.
 *
 */

public class EvenOddTree {
    public static boolean isEvenOddTree(TreeNode root) {
        if(root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList();

        queue.add(root);
        boolean isEven = true;

        while(!queue.isEmpty()) {
            int len = queue.size();
            int prevVal = isEven ? Integer.MIN_VALUE : Integer.MAX_VALUE; // init preVal based on level even or odd

            while(len > 0) { // level by level
                TreeNode current = queue.poll();

                if(isEven && (current.val % 2 == 0 || current.val <= prevVal))
                    return false;                                                   // invalid case on even level

                if(!isEven && (current.val % 2 == 1 || current.val >= prevVal))
                    return false;                                                   // invalid case on odd level

                prevVal = current.val;          // update the prev value

                if(current.left != null)
                    queue.add(current.left);

                if(current.right != null)
                    queue.add(current.right);

                len--;
            }
            isEven = !isEven;                   // alter the levels
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(10);
        root.right = new TreeNode(4);

        root.left.left = new TreeNode(3);
        root.left.right = null;

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(8);

        root.right.left.left = new TreeNode(6);

        root.right.right.right = new TreeNode(2);

        System.out.println(isEvenOddTree(root));
    }
}
