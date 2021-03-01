package Leetcode;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, find the largest subtree,
 * which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
 *
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 *
 * The left subtree values are less than the value of their parent (root) node's value.
 * The right subtree values are greater than the value of their parent (root) node's value.
 * Note: A subtree must include all of its descendants.
 *
 * Follow up: Can you figure out ways to solve it with O(n) time complexity?
 *
 * Example 1:
 * Input: root = [10,5,15,1,8,null,7]
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one.
 * The return value is the subtree's size, which is 3.
 *
 *
 * Example 2:
 * Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -10^4 <= Node.val <= 10^4
 *
 */

public class LargestBSTinSubtree {
    public int largestBSTSubtree(TreeNode root) {
        if(root == null)
            return 0;

        if(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE))
            return bstSize(root);

        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    private boolean isBST(TreeNode root, int minVal, int maxVal) {
        if(root == null)
            return true;

        if(root.val < minVal || root.val > maxVal)
            return false;

        return isBST(root.left, minVal, root.val - 1) && isBST(root.right, root.val + 1, maxVal);
    }

    private int bstSize(TreeNode root) {
        if(root == null)
            return 0;

        return 1 + bstSize(root.left) + bstSize(root.right);
    }

    public static int largestBSTSubtreeOptimized(TreeNode root) {
        BSTNode result = getLargestBST(root);
        return result.getSize();
    }

    private static BSTNode getLargestBST(TreeNode root) {
        if(root == null)
            return new BSTNode(true, 0, null, null);

        // Post Order traversal
        BSTNode leftSubtree = getLargestBST(root.left);
        BSTNode rightSubtree = getLargestBST(root.right);


        // Both left and right subtree are valid
        if(leftSubtree.getIsBST() && rightSubtree.getIsBST()) {
            // check if leftSubtree_Max < root .val < rightSubtree_Min
            // then return true with size = leftSubtree + rightSubtree + 1 and leftMin and RightMax respectively.
            // If leaf node, then leftMin and RightMax == root.val
            if ((root.left == null || root.val > leftSubtree.getMaxVal()) && (root.right == null || root.val < rightSubtree.getMinVal()))
                return new BSTNode(true, leftSubtree.getSize() + rightSubtree.getSize() + 1, root.left == null ? root.val : leftSubtree.getMinVal(), root.right == null ? root.val : rightSubtree.getMaxVal());
        }

        // Both left and right subtree are valid but root violates the BST Criteria
        // Propagate back to the call stack with the maximum size of the leftSubtree or rightSubtree
        return new BSTNode(false, Math.max(leftSubtree.getSize(), rightSubtree.getSize()), null, null);
    }

    static class BSTNode {
        private boolean isBST;
        private int size;
        private Integer minVal;
        private Integer maxVal;

        public BSTNode(boolean isBST, int size, Integer minVal, Integer maxVal) {
            this.isBST = isBST;
            this.size = size;
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        public boolean getIsBST() {
            return isBST;
        }

        public int getSize() {
            return size;
        }

        public int getMinVal() {
            return minVal;
        }

        public int getMaxVal() {
            return maxVal;
        }
    }
}
