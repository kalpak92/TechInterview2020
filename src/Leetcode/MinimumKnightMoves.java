package Leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author kalpak
 *
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below.
 * Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].
 * It is guaranteed the answer exists.
 *
 *
 * Example 1:
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 *
 * Example 2:
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 *
 * Constraints:
 *
 * |x| + |y| <= 300
 *
 */

public class MinimumKnightMoves {
    public static int minKnightMoves(int x, int y) {
        int totalMoves = 0;
        int[][] directions = new int[][]{{2,1}, {-2,1}, {2,-1}, {-2,-1}, {1,2}, {-1,2}, {1,-2}, {-1,-2}};
        Queue<int[]> queue = new LinkedList<>();
        Set<String> isVisited = new HashSet<>();

        queue.offer(new int[]{0,0});
        isVisited.add("0,0");

        // Will be using symmetry
        x = Math.abs(x);
        y = Math.abs(y);

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] currentPosition = queue.poll();

                if(currentPosition[0] == x && currentPosition[1] == y)
                    return totalMoves;

                for(int[] dir : directions) {
                    int i = currentPosition[0] + dir[0];
                    int j = currentPosition[1] + dir[1];

                    // Valid move (Unvisited & obeys symmetry constraint)
                    if(!isVisited.contains(i+","+j) && i >= -2 && j >= -2) {
                        isVisited.add(i+","+j);
                        queue.offer(new int[]{i,j});
                    }
                }
            }
            totalMoves++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minKnightMoves(5, 5));
    }
}
