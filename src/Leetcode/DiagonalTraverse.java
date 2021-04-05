package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given a matrix of M x N elements (M rows, N columns),
 * Return all elements of the matrix in diagonal order as shown in the below image.
 *
 *
 * Example:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * Output:  [1,2,4,7,5,3,6,8,9]
 *
 * Note:
 * The total number of elements of the given matrix will not exceed 10,000.
 *
 */

public class DiagonalTraverse {
    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[0];

        int[] result = new int[matrix.length * matrix[0].length];

        int row = 0;
        int col = 0;

        for(int i = 0; i < matrix.length * matrix[0].length; i++) {
            result[i] = matrix[row][col];

            // Even sum diagonal
            if ((row + col) % 2 == 0) {
                if (row - 1 >= 0 && col + 1 < matrix[0].length) {
                    row = row - 1;
                    col = col + 1;
                } else if (col + 1 < matrix[0].length) {
                    col = col + 1;
                } else {
                    row = row + 1;
                }
            } else if ((row + col) % 2 != 0){
                if (row + 1 < matrix.length && col - 1 >= 0) {
                    row = row + 1;
                    col = col - 1;
                } else if (row + 1 < matrix.length) {
                    row = row + 1;
                } else {
                    col = col + 1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

        System.out.println(Arrays.toString(findDiagonalOrder(arr)));
    }
}
