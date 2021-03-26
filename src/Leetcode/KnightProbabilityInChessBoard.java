package Leetcode;

/**
 * @author kalpak
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
 *
 * The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below.
 * Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random
 * (even if the piece would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard.
 * Return the probability that the knight remains on the board after it has stopped moving.
 *
 *
 * Example:
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 *
 * Note:
 *
 * N will be between 1 and 25.
 * K will be between 0 and 100.
 * The knight always initially starts on the board.
 *
 */

public class KnightProbabilityInChessBoard {
    private static final int[][]dir = new int[][]{{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};

    public static double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K + 1];
        return find(dp, N, K, r, c);
    }

    private static double find(double[][][] dp, int N,int K,int r,int c) {
        if (r < 0 || r > N - 1 || c < 0 || c > N - 1)
            return 0;

        if(K == 0)
            return 1;

        if(dp[r][c][K] != 0)
            return dp[r][c][K];


        double rate = 0;

        for(int i = 0;i < dir.length;i++) {
            rate += 0.125 * find(dp, N, K - 1, r + dir[i][0], c + dir[i][1]);
            dp[r][c][K] = rate;
        }
        return dp[r][c][K];
    }

    public static void main(String[] args) {
        System.out.println(knightProbability(3, 2, 0, 0));
    }
}
