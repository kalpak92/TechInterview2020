package Leetcode;

/**
 * @author kalpak
 *
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor of two nodes p and q in a tree T is the lowest node
 * that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 *
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 *
 *
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 *
 */

public class LCAwithParentConnectionInBinaryTree {
    static class NodeWithParent {
        public int val;
        public NodeWithParent left;
        public NodeWithParent right;
        public NodeWithParent parent;

        NodeWithParent(int val, NodeWithParent parent) {
            this.val = val;
            this.parent = parent;
        }
    }

    public static NodeWithParent lowestCommonAncestor(NodeWithParent p, NodeWithParent q) {
        NodeWithParent temp1 = p;
        NodeWithParent temp2 = q;

        while(temp1 != temp2) {
            temp1 = (temp1 == null) ? q : temp1.parent;
            temp2 = (temp2 == null) ? p : temp2.parent;
        }
        return temp1;
    }

    public static void main(String[] args) {
        NodeWithParent root = new NodeWithParent(3, null);
        root.left = new NodeWithParent(5, root);
        root.right = new NodeWithParent(1, root);
        root.left.left = new NodeWithParent(6, root.left);
        root.left.right = new NodeWithParent(2, root.left);
        root.right.left = new NodeWithParent(0, root.right);
        root.right.right = new NodeWithParent(8, root.right);
        root.left.right.left = new NodeWithParent(7, root.left.right);
        root.left.right.right = new NodeWithParent(4, root.left.right);

        System.out.println(lowestCommonAncestor(root.left, root.left.right.right ).val);
    }
}
