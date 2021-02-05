package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthBinaryTree {
    public static int widthOfBinaryTree(TreeNode root) {
        int result = 0;
        Queue<Pair> queue = new LinkedList<>();

        if(root == null)
            return 0;

        queue.offer(new Pair(root, 1));

        while(!queue.isEmpty()) {
            int len = queue.size();
            int start = 0;
            int end = 0;

            for(int i = 0; i < len; i++) {
                Pair temp = queue.poll();
                TreeNode current = temp.node;
                int index = temp.index;

                if(i == 0)
                    start = index;
                if(i == len - 1)
                    end = index;

                if(current.left != null)
                    queue.offer(new Pair(current.left, 2*index));
                if(current.right != null)
                    queue.offer(new Pair(current.right, 2*index+1));
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }

    static class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(3);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(5);
        root.right.right = new TreeNode(9);

        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        System.out.println(widthOfBinaryTree(root));
    }
}
