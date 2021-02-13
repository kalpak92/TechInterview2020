package Leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kalpak
 *
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 *
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 *
 * Example 2:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 *
 * Output: 3
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 *
 */

public class NumberOfIslands {
    static int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static int numIslands(char[][] grid) {
        int result = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    dfsDetectIslands(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private static void dfsDetectIslands(char[][] grid, int i, int j) {
        // edge cases
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;

        // Mark the cell as visited
        grid[i][j] = '0';

        // traverse in all possible direction
        dfsDetectIslands(grid, i+1, j);
        dfsDetectIslands(grid, i-1, j);
        dfsDetectIslands(grid, i, j+1);
        dfsDetectIslands(grid, i, j-1);
        return;
    }

    private static void bfsDetectIslands(char[][] grid, int i, int j) {
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        // Mark the cell as visited
        grid[i][j] = '0';

        while(!queue.isEmpty()) {
            int[] currentIdx = queue.poll();

            for(int[] dir : directions) {
                int row = currentIdx[0] + dir[0];
                int col = currentIdx[1] + dir[1];

                // edge cases
                if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0')
                    continue;

                // Mark the cell as visited
                grid[row][col] = '0';
                queue.offer(new int[]{row, col});
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
                {'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }
}
