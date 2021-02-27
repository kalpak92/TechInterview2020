package Leetcode;

/**
 * @author kalpak
 *
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 *
 */

public class MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;

        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int result = Integer.MIN_VALUE;

        for(int i = 0; i < dp.length; i++)
            dp[i][0] = 0;

        for(int i = 0; i < dp[0].length; i++)
            dp[0][i] = 0;

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result * result;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},
                            {'1','1','1','1','1'},{'1','0','0','1','0'}};

        System.out.println(maximalSquare(matrix));
    }
}
