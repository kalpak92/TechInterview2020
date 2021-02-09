package Leetcode;

/**
 * @author kalpak
 *
 * Given a binary tree, we install cameras on the nodes of the tree.
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 *
 * Example 1:
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 *
 *
 * Example 2:
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 * Note:
 *
 * The number of nodes in the given tree will be in the range [1, 1000].
 * Every node has value 0.
 */
public class BinaryTreeCameras {
    final static int NO_CAMERA = 0;
    final static int HAS_CAMERA = 2;
    final static int NOT_NEEDED = 1;

    static int result = 0;

    public static int minCameraCover(TreeNode root) {
        if(getCameraCoverDFS(root) == NO_CAMERA)
            result++;

        return result;
    }

    private static int getCameraCoverDFS(TreeNode root) {
        if(root == null)
            return NOT_NEEDED;

        int left = getCameraCoverDFS(root.left);
        int right = getCameraCoverDFS(root.right);

        if (left == NO_CAMERA || right == NO_CAMERA) {
            result++;
            return HAS_CAMERA;
        } else if (left == HAS_CAMERA || right == HAS_CAMERA)
            return NOT_NEEDED;
        else
            return NO_CAMERA;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(0);

        System.out.println("The number of Cameras needed are : " + minCameraCover(root));
    }
}
