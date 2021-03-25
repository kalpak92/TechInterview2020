package Leetcode;

/**
 * @author kalpak
 *
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 *
 * Example 1:
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 *
 *
 * Example 2:
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 *
 */

public class CountSquareSubMatricesWithAllOnes {
    public static int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int result = 0;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) {
                    if(i == 0 || j == 0)    // 1st row or 1st column
                        result += 1;        // The 1 cells are square on its own
                    else {
                        int val = Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][j - 1], matrix[i][j - 1])) + matrix[i][j];
                        result += val;
                        matrix[i][j] = val; // memoize
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        System.out.println(countSquares(matrix));
    }
}
