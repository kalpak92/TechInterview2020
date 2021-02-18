package Leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kalpak
 *
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle).
 * In one step, you can move up, down, left or right from and to an empty cell.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1)
 * given that you can eliminate at most k obstacles.
 *
 * If it is not possible to find such walk return -1.
 *
 * Example 1:
 * Input:
 * grid =
 * [[0,0,0],
 *  [1,1,0],
 *  [0,0,0],
 *  [0,1,1],
 *  [0,0,0]],
 *
 * k = 1
 * Output: 6
 *
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 * Example 2:
 * Input:
 * grid =
 * [[0,1,1],
 *  [1,1,1],
 *  [1,0,0]],
 *
 * k = 1
 * Output: -1
 *
 * Explanation:
 * We need to eliminate at least two obstacles to find such a walk.
 *
 *
 * Constraints:
 *
 * grid.length == m
 * grid[0].length == n
 * 1 <= m, n <= 40
 * 1 <= k <= m*n
 *
 * grid[i][j] == 0 or 1
 * grid[0][0] == grid[m-1][n-1] == 0
 */

public class ShortestPathInGridWithObstacleElimination {
    public static int shortestPath(int[][] grid, int k) {
        int numberOfSteps = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] visited = new int[grid.length][grid[0].length]; // min obstacles elimination from (0,0) to (x, y)

        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cur = queue.poll();

                // Check if we have reached the destination
                if (cur[0] == grid.length - 1 && cur[1] == grid[0].length - 1)
                    return numberOfSteps;

                for (int[] dir : directions) {
                    int i = dir[0] + cur[0];
                    int j = dir[1] + cur[1];

                    // Check if within range
                    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
                        continue;

                    int obstacleCount = grid[i][j] + cur[2];
                    // check if we can revisit the cell or we have any hammers left
                    // if not, move on to the next direction
                    if (obstacleCount >= visited[i][j] || obstacleCount > k)
                        continue;

                    // update the cell with the current obstacle count
                    visited[i][j] = obstacleCount;
                    queue.offer(new int[]{i, j, obstacleCount});
                }
            }
            numberOfSteps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        System.out.println(shortestPath(grid, 1));
    }
}
