package Leetcode;

/**
 * @author kalpak
 *
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 *
 * Input:
 *
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 *
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *
 * Output:
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 *
 *
 * Constraints:
 *
 * 1 <= A.length, B.length <= 100
 * 1 <= A[i].length, B[i].length <= 100
 * -100 <= A[i][j], B[i][j] <= 100
 *
 */

public class SparseMatrixMultiplication {
    public static int[][] multiplyOptimized(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];

        for(int i = 0; i < A.length; i++) {
            for(int k = 0; k < A[0].length; k++) {
                if(A[i][k] != 0) {
                    for(int j = 0; j < B[0].length; j++) {
                        if(B[k][j] != 0)
                            result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }

        return result;
    }

    public int[][] multiplyNaive(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];

        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B[0].length; j++) {
                for(int k = 0; k < A[0].length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }
}
