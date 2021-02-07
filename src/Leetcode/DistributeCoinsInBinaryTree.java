package Leetcode;

/**
 * @author kalpak
 *
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins and there are n coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.
 * (A move may be from parent to child, or from child to parent.)
 *
 * Return the number of moves required to make every node have exactly one coin.
 *
 *
 * Example 1:
 * Input: root = [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 *
 *
 * Example 2:
 * Input: root = [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves].
 * Then, we move one coin from the root of the tree to the right child.
 *
 *
 * Example 3:
 * Input: root = [1,0,2]
 * Output: 2
 *
 * Example 4:
 * Input: root = [1,0,0,null,3]
 * Output: 4
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= n <= 100
 * 0 <= Node.val <= n
 * The sum of Node.val is n.
 *
 */

public class DistributeCoinsInBinaryTree {
    static int result = 0;
    public static int distributeCoins(TreeNode root) {
        doDistributionDfs(root);
        return result;
    }

    private static int doDistributionDfs(TreeNode root) {
        // Base case:  no coins, return 0
        if(root == null)
            return 0;

        int coinsLeft = doDistributionDfs(root.left);
        int coinsRight = doDistributionDfs(root.right);

        // update result
        result += Math.abs(coinsLeft) + Math.abs(coinsRight);

        // passing back the result to the parent node based on the number of moves from each Node
        // currNodeValue + left + right - 1.
        // The -1 is the current node as it keeps 1 coin for itself
        return root.val + coinsLeft + coinsRight - 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);

        root.left.left = null;
        root.left.right = new TreeNode(3);

        System.out.println("The number of moves needed to distribute the coins over all the nodes are: " + distributeCoins(root));
    }
}
