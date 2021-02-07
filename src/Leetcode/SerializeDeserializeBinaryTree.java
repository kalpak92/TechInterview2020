package Leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kalpak
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 *
 * Example 1:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 *
 * Example 4:
 * Input: root = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 *
 */

public class SerializeDeserializeBinaryTree {
    private static final String DELIMIT = ",";
    private static final String NULL = "X";

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        doSerialize(root, result);
        return result.toString();
    }

    private static void doSerialize(TreeNode root, StringBuilder result) {
        if (root == null)
            result.append(NULL).append(DELIMIT);
        else {
            result.append(root.val).append(DELIMIT);

            doSerialize(root.left, result);
            doSerialize(root.right, result);
        }
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        Deque<String> queue = new LinkedList<>();

        queue.addAll(Arrays.asList(data.split(DELIMIT)));
        return doDeserialize(queue);
    }

    private static TreeNode doDeserialize(Deque<String> queue) {
        String currentNode = queue.poll();

        if(currentNode.equals(NULL))
            return null;

        TreeNode current = new TreeNode(Integer.valueOf(currentNode));

        current.left = doDeserialize(queue);
        current.right = doDeserialize(queue);
        return current;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(serialize(root));
    }
}
