package Leetcode;

/**
 * @author kalpak
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Explanation:
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 *
 */

public class SurroundedRegions {
    public static void solve(char[][] board) {
        if (board.length == 0)
            return;

        for (int i = 0; i < board[0].length; i++) {    // Check first row
            if (board[0][i]=='O')
                setSign(board, 0, i);
        }

        for (int i = 0; i < board[0].length; i++) {    // Check last row
            if (board[board.length - 1][i] == 'O')
                setSign(board, board.length - 1, i);
        }

        for (int i = 0; i < board.length; i++) {       // Check first column
            if (board[i][0] == 'O')
                setSign(board, i, 0);
        }

        for (int i = 0; i < board.length; i++) {       // Check last column
            if (board[i][board[0].length-1] == 'O')
                setSign(board, i, board[0].length-1);
        }

        // Capture all '0'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]=='O')
                    board[i][j]='X';
            }
        }

        // Flip back the '-'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]=='-')
                    board[i][j]='O';
            }
        }
    }

    private static void setSign(char[][] board, int i, int j) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O')
            return;

        board[i][j]='-';

        setSign(board, i + 1, j);
        setSign(board, i - 1, j);
        setSign(board, i, j + 1);
        setSign(board, i, j - 1);
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(board);

        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }
}
