package Leetcode;

/**
 * @author kalpak
 *
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 *
 *
 * Example 1:
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 *
 * Explanation: There are two falling paths with a minimum sum underlined below:
 * [[2,1,3],      [[2,1,3],
 *  [6,5,4],       [6,5,4],
 *  [7,8,9]]       [7,8,9]]
 *
 *
 * Example 2:
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is underlined below:
 * [[-19,57],
 *  [-40,-5]]
 *
 *
 * Example 3:
 * Input: matrix = [[-48]]
 * Output: -48
 *
 *
 * Constraints:
 *
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 *
 */

public class MinimumFallingPathSum {
    public static int minFallingPathSum(int[][] matrix) {
        // edge case
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int[][] dp = new int[matrix.length][matrix[0].length];
        int result = Integer.MAX_VALUE;

        for(int j = 0; j < matrix[0].length; j++)
            dp[0][j] = matrix[0][j];

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(j == 0) {    // First Column
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                } else if ( j == matrix[0].length - 1) {    // Last Column
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1]));
                }

                dp[i][j] += matrix[i][j];
            }
        }

        for(int j = 0; j < dp[0].length; j++) {
            result = Math.min(result, dp[dp.length - 1][j]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println("The minimum Falling Path sum is : " + minFallingPathSum(matrix));
    }
}
