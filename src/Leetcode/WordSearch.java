package Leetcode;

/**
 * @author kalpak
 *
 * Given an m x n board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where "adjacent" cells are horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once.
 *
 * Example 1:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 *
 * Example 2:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 *
 * Example 3:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 200
 * 1 <= word.length <= 103
 * board and word consists only of lowercase and uppercase English letters.
 *
 */

public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        if(board == null || word.length() == 0)
            return false;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(backtrackWordSearch(board, i, j, 0, word))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean backtrackWordSearch(char[][] board, int i, int j, int count, String word) {
        // base case
        if(count == word.length())
            return true;

        // Check if i and j are in bounds
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;

        // check if the character at given index of word matches the board[i][j]th position
        if(board[i][j] != word.charAt(count))
            return false;

        // We have a match. Need to search further.
        // Change the current character to keep track of the characters that have been visited
        char visited = word.charAt(count);
        board[i][j] = '*';

        boolean found = backtrackWordSearch(board, i+1, j, count+1, word) ||
                backtrackWordSearch(board, i-1, j, count+1, word) ||
                backtrackWordSearch(board, i, j+1, count+1, word) ||
                backtrackWordSearch(board, i, j-1, count+1, word);

        // Revert back the board state
        board[i][j] = visited;
        return found;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(exist(board, "SEE"));
    }
}
