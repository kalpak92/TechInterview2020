package Leetcode;

/**
 * @author kalpak
 *
 * This question is about implementing a basic elimination algorithm for Candy Crush.
 *
 * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies.
 * A value of board[i][j] = 0 represents that the cell at position (i, j) is empty.
 * The given board represents the state of the game following the player's move.
 *
 * Now, you need to restore the board to a stable state by crushing candies according to the following rules:
 * - If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
 * - After crushing all candies simultaneously, if an empty space on the board has candies on top of itself,
 *      then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 * - After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 * - If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 *
 * You need to perform the above rules until the board becomes stable, then return the current board.
 *
 * Example:
 *
 * Input:
 * board = [[110,5,112,113,114],
 *          [210,211,5,213,214],
 *          [310,311,3,313,314],
 *          [410,411,412,5,414],
 *          [5,1,512,3,3],
 *          [610,4,1,613,614],
 *          [710,1,2,713,714],
 *          [810,1,2,1,1],
 *          [1,1,2,2,2],
 *          [4,1,4,4,1014]]
 *
 * Output:
 * [[0,0,0,0,0],
 * [0,0,0,0,0],
 * [0,0,0,0,0],
 * [110,0,0,0,114],
 * [210,0,0,0,214],
 * [310,0,0,113,314],
 * [410,0,0,213,414],
 * [610,211,112,313,614],
 * [710,311,412,613,714],
 * [810,411,512,713,1014]]
 *
 * Explanation:
 * Note:
 *
 * The length of board will be in the range [3, 50].
 * The length of board[i] will be in the range [3, 50].
 * Each board[i][j] will initially start as an integer in the range [1, 2000].
 */

public class CandyCrush {
    public static int[][] candyCrush(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        boolean found = true;

        while(found) {
            found = false;
            for(int i = 0; i < row; i++) {
                for(int j = 0; j < col; j++) {
                    int cellValue = Math.abs(board[i][j]);

                    if(cellValue == 0)
                        continue;

                    // For each cell,
                    // if there are at least three candies of the same type rightward or downward,
                    // set them all to its negative value to mark them.

                    if(j < col - 2 && Math.abs(board[i][j + 1]) == cellValue && Math.abs(board[i][j + 2]) == cellValue) {
                        found = true;
                        int index = j;

                        while (index < col && Math.abs(board[i][index]) == cellValue)
                            board[i][index++] = -cellValue;
                    }

                    if(i < row - 2 && Math.abs(board[i + 1][j]) == cellValue && Math.abs(board[i + 2][j]) == cellValue) {
                        found = true;
                        int index = i;

                        while (index < row && Math.abs(board[index][j]) == cellValue)
                            board[index++][j] = -cellValue;
                    }
                }
            }

            // After each traversal,
            // we need to remove all those negative values by setting them to 0,
            // then let the rest drop down to their correct position.
            if(found) {
                // A easy way is to iterate through each column -
                // For each column, move positive values to the bottom then set the rest to 0.
                for(int j = 0; j < col; j++) {
                    int runningRowIndex = row - 1;
                    for(int i = row - 1; i >= 0; i--) {
                        if(board[i][j] > 0) {
                            board[runningRowIndex][j] = board[i][j];
                            runningRowIndex--;
                        }
                    }
                    for(int k = runningRowIndex; k >= 0; k--)
                        board[k][j] = 0;
                }
            }
        }
        return board;
    }

    public static void printBoard(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{110,5,112,113,114},{210,211,5,213,214},{310,311,3,313,314},
                                    {410,411,412,5,414},{5,1,512,3,3},{610,4,1,613,614},{710,1,2,713,714},
                                    {810,1,2,1,1},{1,1,2,2,2},{4,1,4,4,1014}};
        System.out.println("Board before move.");
        printBoard(board);
        candyCrush(board);
        System.out.println("Board after a move.");
        printBoard(board);
    }
}
