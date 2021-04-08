package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kalpak
 *
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
 * If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell
 * (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 *
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 *
 *
 * Example 2:
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 *
 *
 * Example 3:
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *
 *
 * Constraints:
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 *
 */

public class ShortestPathInBinaryMatrix {
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1)
            return -1;

        int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};

        Queue<int []> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[grid.length][grid.length];

        int result = 0;

        if(grid[0][0] == 0)
            q.add(new int[]{0,0});

        int count =1;

        while(!q.isEmpty()) {
            int size = q.size();
            count = 0;
            result++;

            for(int i = 0;i < size; i++) {
                int point[] = q.poll();

                int x1 = point[0];
                int y1 = point[1];

                if(isVisited[x1][y1]==true)
                    continue;

                if(x1 == grid.length - 1 && y1 == grid.length - 1)
                    return result;

                isVisited[x1][y1] = true;

                for(int[] dir : dirs) {
                    int x = x1 + dir[0];
                    int y = y1 + dir[1];

                    if(x < 0 || y < 0 || x >= grid.length || y >= grid.length || isVisited[x][y] == true || grid[x][y] == 1)
                        continue;
                    else {
                        q.add(new int[]{x,y});
                        count++;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0}, {1,1,0}, {1,1,0}};
        System.out.println("The distance of the shortest path in the binary matrix is : " + shortestPathBinaryMatrix(grid));
    }
}
