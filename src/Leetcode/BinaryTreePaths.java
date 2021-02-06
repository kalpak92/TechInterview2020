package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a binary tree, return all root-to-leaf paths.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 */

public class BinaryTreePaths {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        dfsBinaryTreePaths(result, path, root);
        return result;
    }

    private static void dfsBinaryTreePaths(List<String> result, StringBuilder path, TreeNode root) {
        if(root == null)
            return;

        int len = path.length();
        path.append(root.val);

        if(root.left == null && root.right == null) {
            result.add(path.toString());
        } else {
            path.append("->");
            dfsBinaryTreePaths(result, path, root.left);
            dfsBinaryTreePaths(result, path, root.right);
        }

        path.setLength(len);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        System.out.println(binaryTreePaths(root));
    }
}
