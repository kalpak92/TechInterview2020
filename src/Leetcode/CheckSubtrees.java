package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kalpak
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * Example 1:
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 *
 *
 * Example 2:
 * Input: root = [2,1,1]
 * Output: [[1]]
 *
 *
 * Example 3:
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the tree will be in the range [1, 10^4]
 * -200 <= Node.val <= 200
 *
 */

public class CheckSubtrees {
    static Map<String, Integer> map;
    static List<TreeNode> result;

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap();
        result = new ArrayList();

        doFindDuplicateSubtrees(root);

        return result;
    }
    public static String doFindDuplicateSubtrees(TreeNode root) {
        if (root == null)
            return "#" + ",";

        String currentSubtree = root.val + ",";

        String left = doFindDuplicateSubtrees(root.left);
        String right = doFindDuplicateSubtrees(root.right);

        currentSubtree += left + right;

        map.put(currentSubtree, map.getOrDefault(currentSubtree, 0) + 1);

        if (map.get(currentSubtree) == 2)
            result.add(root);

        return currentSubtree;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);

        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);

        System.out.println(findDuplicateSubtrees(root));

    }
}
