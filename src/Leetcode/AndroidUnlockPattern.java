package Leetcode;

/**
 * @author kalpak
 *
 * Android devices have a special lock screen with a 3 x 3 grid of dots.
 * Users can set an "unlock pattern" by connecting the dots in a specific sequence,
 * forming a series of joined line segments where each segment's endpoints are two consecutive dots in the sequence.
 *
 * A sequence of k dots is a valid unlock pattern if both of the following are true:
 *      All the dots in the sequence are distinct.
 *      If the line segment connecting two consecutive dots in the sequence passes through any other dot,
 *      the other dot must have previously appeared in the sequence. No jumps through non-selected dots are allowed.
 *
 * Here are some example valid and invalid unlock patterns:
 *
 *
 *
 * The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot 2, but dot 2 did not previously appear in the sequence.
 * The 2nd pattern [4,1,9,2] is invalid because the line connecting dots 1 and 9 pass through dot 5, but dot 5 did not previously appear in the sequence.
 * The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions. The line connecting dots 1 and 3 meets the condition because dot 2 previously appeared in the sequence.
 * The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions. The line connecting dots 1 and 9 meets the condition because dot 5 previously appeared in the sequence.
 * Given two integers m and n, return the number of unique and valid unlock patterns of the Android grid lock screen that consist of at least m keys and at most n keys.
 *
 * Two unlock patterns are considered unique if there is a dot in one sequence that is not in the other, or the order of the dots is different.
 *
 *
 *
 * Example 1:
 * Input: m = 1, n = 1
 * Output: 9
 *
 * Example 2:
 * Input: m = 1, n = 2
 * Output: 65
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 9
 */

public class AndroidUnlockPattern {
    private static int[][] jumps;
    private static boolean[] visited;

    public static int numberOfPatterns(int m, int n) {
        // We can check invalid moves by using a jumping table.
        // e.g. If a move requires a jump and the key that it is crossing is not visited, then the move is invalid.

        jumps = new int[10][10];

        jumps[1][3] = jumps[3][1] = 2;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5;

        visited = new boolean[10];

        int result = 0;

        result += calcualtePatterns(1, 1, 0, m, n) * 4; // 1, 3, 7, 9 are symmetrical
        result += calcualtePatterns(2, 1, 0, m, n) * 4; // 2, 4, 6, 8 are symmetrical
        result += calcualtePatterns(5, 1, 0, m, n);

        return result;
    }

    private static int calcualtePatterns(int num, int len, int count, int m, int n) {
        if (len >= m)
            count++; // only count if moves are larger than m

        len++;

        if (len > n)
            return count;

        visited[num] = true;

        for (int next = 1; next <= 9; next++) {
            int jump = jumps[num][next];

            if (!visited[next] && (jump == 0 || visited[jump])) {
                count = calcualtePatterns(next, len, count, m, n);
            }
        }

        visited[num] = false; // backtracking

        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOfPatterns(1, 2));
    }
}
