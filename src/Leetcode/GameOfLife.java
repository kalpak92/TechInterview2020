package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * Given the current state of the m x n grid board, return the next state.
 *
 */

public class GameOfLife {
    public static void gameOfLife(int[][] board) {
        int[][] boardCopy = new int[board.length][board[0].length];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }

        // Neighbors array for 8 neighboring cells for a given cell
        int[] directions = new int[]{0, 1, -1};

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {

                // number of live neighbors
                int liveNeighbors = 0;

                for(int p = 0; p < 3; p++) {
                    for(int q = 0; q < 3; q++) {

                        if(!(directions[p] == 0 && directions[q] == 0)) {
                            int rowIdx = (i + directions[p]);
                            int colIdx = (j + directions[q]);

                            // check criteria from boardCopy
                            if((rowIdx < board.length && rowIdx >= 0) && (colIdx < board[0].length && colIdx >= 0) && (boardCopy[rowIdx][colIdx] == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }
                // Rule 1 or Rule 3
                if((boardCopy[i][j] == 1) && (liveNeighbors < 2 || liveNeighbors > 3))
                    board[i][j] = 0;

                // Rule 4
                if(boardCopy[i][j] == 0 && liveNeighbors == 3)
                    board[i][j] = 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        gameOfLife(board);

        for(int[] row : board)
            System.out.println(Arrays.toString(row));
    }
}
