package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kalpak
 *
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 *
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
 * because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 *
 */

public class RottenOranges {
    public static int orangesRotting(int[][] grid) {
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int freshOrangesCount = 0;
        int timeTaken = 0;
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2) {
                    // Rotten Orange. Add to queue
                    queue.offer(new int[]{i, j});
                }

                if(grid[i][j] == 1) {
                    // Fresh Orange
                    freshOrangesCount++;
                }
            }
        }

        while(!queue.isEmpty() && freshOrangesCount > 0) {
            timeTaken++;
            int size = queue.size();
            while(size-- > 0) {
                int[] location = queue.poll();
                for(int[] dir : directions) {
                    int i = location[0] + dir[0];
                    int j = location[1] + dir[1];

                    // Check Edge scenarios (i and j validity, already rotten oranges, empty cell)
                    if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 2 || grid[i][j] == 0)
                        continue;

                    // Add fresh orange to queue
                    queue.offer(new int[]{i, j});   // Add the fresh orange to the queue
                    grid[i][j] = 2;                 // Mark the current orange as rotten
                    freshOrangesCount--;            // decrease the count of fresh oranges
                }
            }
        }

        return freshOrangesCount == 0 ? timeTaken : -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{2,1,1},{1,1,0}, {0,1,1}};
        System.out.println(orangesRotting(grid));
    }
}
