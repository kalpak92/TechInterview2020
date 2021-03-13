package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once in a word.
 *
 * Example 1:
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 *
 * Example 2:
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 *
 */

public class WordSearchII {
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        TrieNode root = buildTrie(words);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                searchWordDFS(board, result, root, i, j);
            }
        }
        return result;
    }

    private static void searchWordDFS(char[][] board, List<String> result, TrieNode root, int i, int j) {
        char c = board[i][j];
        if(c == '#' || root.children[c - 'a'] == null)
            return;

        root = root.children[c - 'a'];

        if(root.word != null) {
            result.add(root.word);
            root.word = null;       // de-duplicate. No need of Set.
        }

        board[i][j] = '#';  // Track visisted cells

        if(i > 0)
            searchWordDFS(board, result, root, i - 1, j);
        if(j > 0)
            searchWordDFS(board, result, root, i, j - 1);
        if(i < board.length - 1)
            searchWordDFS(board, result, root, i + 1, j);
        if(j < board[0].length - 1)
            searchWordDFS(board, result, root, i, j + 1);

        board[i][j] = c;    // backtrack
    }

    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for(String word : words) {
            TrieNode current = root;

            for(char c : word.toCharArray()) {
                if(current.children[c - 'a'] == null)
                    current.children[c - 'a'] = new TrieNode();

                current = current.children[c - 'a'];
            }
            current.word = word;
        }

        return root;
    }

    static class TrieNode {
        private TrieNode[] children;
        private String word;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.word = null;
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};

        System.out.println(findWords(board, words));
    }
}
