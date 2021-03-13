package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kalpak
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 *
 * Example 1:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 * Example 2:
 * Input: n = 1
 * Output: [["Q"]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 9
 *
 */

public class NQueens {
    static List<List<String>> result;
    public static List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();

        placeQueen(new int[n][n], 0, new HashSet<>(), new HashSet<>(), new HashSet<>());

        return result;

    }

    private static void placeQueen(int[][] board, int i, Set<Integer> diagonal, Set<Integer> antiDiagonal, Set<Integer> vertical) {
        if(i == board.length) {
            // call the function that adds the strings
            addToList(board);
            return;
        }

        for(int j = 0; j < board.length; j++) {
            if(!diagonal.contains(i + j) && !antiDiagonal.contains(j - i) && !vertical.contains(j)) {
                board[i][j] = 1;
                diagonal.add(i + j);
                antiDiagonal.add(j - i);
                vertical.add(j);

                placeQueen(board, i + 1, diagonal, antiDiagonal, vertical);

                board[i][j] = 0;
                diagonal.remove(i + j);
                antiDiagonal.remove(j - i);
                vertical.remove(j);
            }
        }
    }

    private static void addToList(int[][] board) {
        List<String> boardState = new ArrayList<>();
        StringBuilder temp;

        for(int i = 0; i < board.length; i++) {
            temp = new StringBuilder();
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == 0)
                    temp.append(".");
                else
                    temp.append("Q");
            }
            boardState.add(temp.toString());
        }
        result.add(boardState);
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(8));
    }
}
