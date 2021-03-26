package Leetcode;

/**
 * @author kalpak
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2)
 *      returns the sum of the elements of the matrix array inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 */

public class RangeSumQuery2DImmutable {
    private int[][] dp;
    public RangeSumQuery2DImmutable(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return;

        dp = new int[matrix.length + 1][matrix[0].length + 1];

        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[0].length; c++)
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
