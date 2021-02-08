package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */

public class ConstructBinaryTreeFromPreorderInorderTraversal {
    static Map<Integer, Integer> map;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();

        for(int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        TreeNode result = buildTreeDFS(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return result;
    }

    private static TreeNode buildTreeDFS(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart > preEnd || inStart > inEnd)
            return null;

        // Root is always the start of the preOrder
        TreeNode root = new TreeNode(preorder[preStart]);

        // Get the position of this node in the inorder list
        int inRoot = map.get(root.val);

        // Nodes left to inRoot will be the left subtree. Get the range
        int numberOfNodesToLeft = inRoot - inStart;

        root.left = buildTreeDFS(preorder, preStart + 1, preStart + numberOfNodesToLeft, inorder, inStart, inRoot - 1);
        root.right = buildTreeDFS(preorder, preStart + numberOfNodesToLeft + 1, preEnd, inorder, inRoot + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        buildTree(preorder, inorder);
    }
}
