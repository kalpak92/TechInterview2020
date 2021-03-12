package Leetcode;

/**
 * @author kalpak
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.
 * The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degrees to the right.
 *
 * The robot performs the instructions given in order, and repeats them forever.
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 *
 * Example 1:
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 *
 *
 * Example 2:
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot moves north indefinitely.
 *
 * Example 3:
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 *
 *
 * Constraints:
 *
 * 1 <= instructions.length <= 100
 * instructions[i] is 'G', 'L' or, 'R'.
 *
 */

public class RobotBoundedInCircle {
    public static boolean isRobotBounded(String instructions) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Initial position
        int x = 0;
        int y = 0;

        int currentIdx = 0;

        for (int i = 0; i < 4; i++) {
            for(char c : instructions.toCharArray()) {
                if(c == 'L')
                    currentIdx = (currentIdx + 3) % 4;
                else if (c == 'R')
                    currentIdx = (currentIdx + 1) % 4;
                else {
                    x += directions[currentIdx][0];
                    y += directions[currentIdx][1];
                }
            }
        }


        // True if the robot is back to the centre or currentIdx does not face north so we wont have a cycle
        return (x == 0 && y == 0) || (currentIdx != 0);
    }

    public static void main(String[] args) {
        String instuctions = "GL";
        System.out.println(isRobotBounded(instuctions));
    }
}
