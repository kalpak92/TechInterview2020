package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example 1:
 *
 *
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * Example 2:
 *
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 * Example 3:
 *
 * Input: rooms = [[2147483647]]
 * Output: [[2147483647]]
 * Example 4:
 *
 * Input: rooms = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 *
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 231 - 1.
 *
 */

public class WallsAndGates {
    public static void wallsAndGates(int[][] rooms) {
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j] == 0) {
                    //Gate Found
                    dfsDetectRooms(rooms, i, j, 0);
                }
            }
        }
    }

    private static void dfsDetectRooms(int[][] rooms, int i, int j, int distance) {
        if(i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || distance > rooms[i][j])
            return;

        rooms[i][j] = distance;

        dfsDetectRooms(rooms, i+1, j, distance+1);
        dfsDetectRooms(rooms, i-1, j, distance+1);
        dfsDetectRooms(rooms, i, j+1, distance+1);
        dfsDetectRooms(rooms, i, j-1, distance+1);
    }

    public static void main(String[] args) {
        int[][] rooms = new int[][]{{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};

        wallsAndGates(rooms);

        for(int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms.length; j++)
                System.out.print(rooms[i][j] + " ");
            System.out.println();
        }
    }
}
