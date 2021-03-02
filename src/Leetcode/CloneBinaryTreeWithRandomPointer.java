package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.
 *
 * Return a deep copy of the tree.
 *
 * The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node.
 * You will be given the tree in class Node and you should return the cloned tree in class NodeCopy.
 * NodeCopy class is just a clone of Node class with the same attributes and constructors.
 *
 *
 * Example 1:
 * Input: root = [[1,null],null,[4,3],[7,0]]
 * Output: [[1,null],null,[4,3],[7,0]]
 * Explanation: The original binary tree is [1,null,4,7].
 * The random pointer of node one is null, so it is represented as [1, null].
 * The random pointer of node 4 is node 7, so it is represented as [4, 3] where 3 is the index of node 7 in the array representing the tree.
 * The random pointer of node 7 is node 1, so it is represented as [7, 0] where 0 is the index of node 1 in the array representing the tree.
 *
 *
 * Example 2:
 * Input: root = [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Output: [[1,4],null,[1,0],null,[1,5],[1,5]]
 * Explanation: The random pointer of a node can be the node itself.
 *
 *
 * Example 3:
 * Input: root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 * Output: [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
 *
 *
 * Example 4:
 * Input: root = []
 * Output: []
 * Example 5:
 *
 * Input: root = [[1,null],null,[2,null],null,[1,null]]
 * Output: [[1,null],null,[2,null],null,[1,null]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 1000].
 * Each node's value is between [1, 10^6].
 *
 */

public class CloneBinaryTreeWithRandomPointer {
    public class NodeCopy {
        int val;
        NodeCopy left;
        NodeCopy right;
        NodeCopy random;
        NodeCopy() {}
        NodeCopy(int val) { this.val = val; }
        NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public class NodeWithRandom {
        int val;
        NodeWithRandom left;
        NodeWithRandom right;
        NodeWithRandom random;
        NodeWithRandom() {}
        NodeWithRandom(int val) { this.val = val; }
        NodeWithRandom(int val, NodeWithRandom left, NodeWithRandom right, NodeWithRandom random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public NodeCopy copyRandomBinaryTree(NodeWithRandom root) {
        if(root == null)
            return null;

        Map<NodeWithRandom, NodeCopy> map = new HashMap<>(); // Since all node values are not unique, store the node as key
        Deque<NodeWithRandom> stack = new ArrayDeque<>();

        stack.add(root);

        while(!stack.isEmpty()) {
            NodeWithRandom current = stack.poll();
            NodeCopy copyCurrent = new NodeCopy(current.val);

            map.put(current, copyCurrent);

            if(current.left != null)
                stack.add(current.left);

            if(current.right != null)
                stack.add(current.right);

            // We cannot map the Random pointer here because we could enter a cycle in the traversal.
        }

        stack.add(root);
        while(!stack.isEmpty()) {
            NodeWithRandom current = stack.poll();

            if (current.left != null) {
                map.get(current).left  = map.get(current.left);
                stack.add(current.left);
            }

            if(current.right != null) {
                map.get(current).right = map.get(current.right);
                stack.add(current.right);
            }

            if(current.random != null) {
                map.get(current).random = map.get(current.random);
            }
        }
        return map.get(root);
    }
}
