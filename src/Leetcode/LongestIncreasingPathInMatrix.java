package Leetcode;

/**
 * @author kalpak
 *
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 * Example 1:
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 *
 * Example 2:
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 *
 * Example 3:
 * Input: matrix = [[1]]
 * Output: 1
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */

public class LongestIncreasingPathInMatrix {
    public static int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int result = 0;
        int[][] dp = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(dp[i][j] == 0) {
                    getLongestIncreasingPathDFS(matrix, dp, i, j, Integer.MIN_VALUE);
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    private static int getLongestIncreasingPathDFS(int[][] matrix, int[][] dp, int i, int j, int prev) {
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= prev)
            return 0;

        if(dp[i][j] != 0)
            return dp[i][j];

        int up = getLongestIncreasingPathDFS(matrix, dp, i - 1, j, matrix[i][j]);
        int down = getLongestIncreasingPathDFS(matrix, dp, i + 1, j, matrix[i][j]);
        int left = getLongestIncreasingPathDFS(matrix, dp, i, j - 1, matrix[i][j]);
        int right = getLongestIncreasingPathDFS(matrix, dp, i, j + 1, matrix[i][j]);

        dp[i][j] = Math.max(up, Math.max(down, Math.max(left, right))) + 1;

        return dp[i][j];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(longestIncreasingPath(matrix));
    }
}
