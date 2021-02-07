package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.
 * The answer can be returned in any order.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * Output: [7,4,1]
 *
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 * Note:
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 *
 */

public class AllNodesAtKDistanceBinaryTree {
    List<Integer> result;
    int k = 0;
    TreeNode target;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        this.result = new ArrayList<>();
        this.target = target;
        this.k = K;

        distanceKDfs(root);
        return result;
    }

    private int distanceKDfs(TreeNode root) {
        if(root == null)
            return -1;

        if(root == target) {
            getKnodesFromTarget(root, 0);
            return 1;                       // return 1 to parent because parent is at distance 1 from current target
        } else {
            // Need to search for target in left and right subtree
            int left = distanceKDfs(root.left);

            if(left != -1) {
                if(left == k)
                    result.add(root.val);
                else {
                    getKnodesFromTarget(root.right, left + 1);
                }
                return left + 1;
            }

            int right = distanceKDfs(root.right);

            if(right != -1) {
                if(right == k)
                    result.add(root.val);
                else {
                    getKnodesFromTarget(root.left, right + 1);
                }
                return right + 1;
            }
            return -1;
        }
    }

    private void getKnodesFromTarget(TreeNode current, int distance) {
        if(current == null)
            return;
        else if (distance == k) {
            result.add(current.val);
        } else {
            getKnodesFromTarget(current.left, distance + 1);
            getKnodesFromTarget(current.right, distance + 1);
        }
    }

    public static void main(String[] args) {
        AllNodesAtKDistanceBinaryTree obj = new AllNodesAtKDistanceBinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        System.out.println(obj.distanceK(root, root.left, 2));
    }
}
