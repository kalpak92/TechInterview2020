package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right)
 * from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Note:
 *
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 *
 *
 * Example:
 *
 * Given the following 5x5 matrix:
 *
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 * Return:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 *
 */

public class PacificAtlanticWaterFlow {
    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();

        if(matrix == null || matrix.length == 0)
            return result;

        int[][] pacific = new int[matrix.length][matrix[0].length];
        int[][] atlantic = new int[matrix.length][matrix[0].length];

        // Call the DFS helper on the Pacific and Atlantic ocean which are the border of the matrix
        // Top : Pacific and Bottom : Atlantic
        for(int col = 0; col < matrix[0].length; col++) {
            findWaterFlowDFS(matrix, 0, col, Integer.MIN_VALUE, pacific);
            findWaterFlowDFS(matrix, matrix.length - 1, col, Integer.MIN_VALUE, atlantic);
        }

        // Left : Pacific and Right : Atlantic
        for(int row = 0; row < matrix.length; row++) {
            findWaterFlowDFS(matrix, row, 0, Integer.MIN_VALUE, pacific);
            findWaterFlowDFS(matrix, row, matrix[0].length - 1, Integer.MIN_VALUE, atlantic);
        }

        // Compare pacific and atlantic matrices.
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(pacific[i][j] == 1 && atlantic[i][j] == 1) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private static void findWaterFlowDFS(int[][] matrix, int i, int j, int preVal, int[][] ocean) {
        // Edge case:
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length )
            return;
        else if(matrix[i][j] < preVal)  // constraint : can't flow
            return;
        else if(ocean[i][j] == 1)       // no need to visit already visited node
            return;

        // Process
        ocean[i][j] = 1;

        // Recurse
        findWaterFlowDFS(matrix, i - 1, j, matrix[i][j], ocean);
        findWaterFlowDFS(matrix, i + 1, j, matrix[i][j], ocean);
        findWaterFlowDFS(matrix, i, j + 1, matrix[i][j], ocean);
        findWaterFlowDFS(matrix, i, j - 1, matrix[i][j], ocean);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};

        System.out.println(pacificAtlantic(matrix));
    }
}
