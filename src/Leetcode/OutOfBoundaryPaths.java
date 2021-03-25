package Leetcode;

/**
 * @author kalpak
 *
 * There is an m by n grid with a ball.
 * Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right).
 * However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary.
 *
 * The answer may be very large, return it after mod 109 + 7.
 *
 *
 * Example 1:
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 *
 * Example 2:
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 *
 *
 * Note:
 *
 * Once you move the ball out of boundary, you cannot move it back.
 * The length and height of the grid is in range [1,50].
 * N is in range [0,50].
 *
 */

public class OutOfBoundaryPaths {
    private static final int MOD = 1000000007;

    public static int findPathsRecursive(int m, int n, int N, int i, int j) {
        if(i == m || j == n || i < 0 || j < 0)
            return 1;                           // path available to take the ball out

        if(N == 0)
            return 0;

        return findPathsRecursive(m, n, N - 1, i - 1, j) +
                findPathsRecursive(m, n, N - 1, i + 1, j) +
                findPathsRecursive(m, n, N - 1, i, j - 1) +
                findPathsRecursive(m, n, N - 1, i, j + 1);
    }

    public static int findPaths(int m, int n, int N, int i, int j) {
        if (m < 0 || n < 0 || N < 0 || i < 0 || i >= m || j < 0 || j >= n)
            return 0;

        Long[][][] dp = new Long[m][n][N + 1];

        return (int)findPathsDFS(dp, m, n, N, i, j);
    }

    private static long findPathsDFS(Long[][][] dp, int m, int n, int N, int i, int j) {
        if(i < 0 || j < 0 || i >= m || j >= n)
            return 1;

        if(N < 1)
            return 0;

        if (dp[i][j][N] != null)
            return dp[i][j][N];

        long down = findPathsDFS(dp, m, n, N - 1,i + 1, j);
        long up = findPathsDFS(dp, m, n, N - 1, i - 1, j);
        long left = findPathsDFS(dp, m, n, N - 1, i, j - 1);
        long right = findPathsDFS(dp, m, n, N - 1, i, j + 1);

        dp[i][j][N] = (down + up + left + right) % MOD;

        return dp[i][j][N];
    }

    public static void main(String[] args) {
        System.out.println(findPaths(1, 3, 3, 0, 1));
    }
}
