package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Implement the StreamChecker class as follows:
 *
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest,
 * including this letter just queried) spell one of the words in the given list.
 *
 *
 * Example:
 *
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('h');          // return false
 * streamChecker.query('i');          // return false
 * streamChecker.query('j');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 *
 *
 * Note:
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * Words will only consist of lowercase English letters.
 * Queries will only consist of lowercase English letters.
 * The number of queries is at most 40000.
 */

class StreamOfCharacters {
    private StringBuilder sb;
    private TrieNode root;

    public StreamOfCharacters(String[] words) {
        sb = new StringBuilder();
        root = new TrieNode();

        for(String word: words) {
            root.insert(word);
        }
    }

    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        for(int i = sb.length() - 1; i >= 0 && node != null; i--) {
            char c = sb.charAt(i);
            node = node.next.get(c);

            if(node != null && node.isWord)
                return true;
        }
        return false;
    }

    class TrieNode {
        Map<Character, TrieNode> next;
        boolean isWord;

        public TrieNode() {
            next = new HashMap<>();
        }

        public void insert(String word) {
            TrieNode current = root;

            for(int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                TrieNode temp = current.next.getOrDefault(c, new TrieNode());
                current.next.put(c, temp);
                current = temp;
            }

            current.isWord = true;
        }
    }
}
