package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * For each node at position (row, col), its left and right children will be at positions
 * (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index
 * starting from the leftmost column and ending on the rightmost column.
 * There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 *
 * Example 3:
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 *
 */

public class VerticalOrderTraversalBinaryTree {
    static class TreeNodePoint {
        int val;
        int x;
        int y;

        TreeNodePoint(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null)
            return result;

        // Create a list of TreeNodePoints
        List<TreeNodePoint> treeNodePoints = new ArrayList<>();
        dfsTreeNode(root, 0, 0, treeNodePoints);

        // Sort the list
        Collections.sort(treeNodePoints, (a, b) -> a.x == b.x ? a.y == b.y ? a.val- b.val : b.y - a.y : a.x - b.x);

        //Build a treeMap with key as x and value as node.val
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for(TreeNodePoint nodePoint : treeNodePoints) {
            List<Integer> temp = map.getOrDefault(nodePoint.x, new ArrayList<>());
            temp.add(nodePoint.val);
            map.put(nodePoint.x, temp);
        }

        // Build the result
        for(List<Integer> val : map.values())
            result.add(val);

        return result;
    }

    private static void dfsTreeNode(TreeNode root, int x, int y, List<TreeNodePoint> treeNodePoints) {
        if(root == null)
            return;

        // Create a TreeNode
        TreeNodePoint currentNode = new TreeNodePoint(root.val, x, y);
        treeNodePoints.add(currentNode);

        // DFS into the tree
        dfsTreeNode(root.left, x-1, y-1, treeNodePoints);
        dfsTreeNode(root.right, x+1, y-1, treeNodePoints);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println(verticalTraversal(root));
    }
}
