package Leetcode;

/**
 * @author kalpak
 *
 * Trie (we pronounce "try") or prefix tree is a tree data structure used to retrieve a key in a strings dataset.
 * There are various applications of this very efficient data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() initializes the trie object.
 * void insert(String word) inserts the string word to the trie.
 * boolean search(String word) returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist of lowercase English letters.
 * At most 3 * 10^4 calls will be made to insert, search, and startsWith.
 */

public class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode('\0');  // dummy
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(current.children[c - 'a'] == null)
                current.children[c - 'a'] = new TrieNode(c);

            current = current.children[c - 'a'];    // update current so that we go down the chain in the tree
        }
        current.isWord = true;
    }

    private TrieNode getNode(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(current.children[c - 'a'] == null)
                return null;                        //  We dont have the current character

            current = current.children[c - 'a'];    // update current so that we go down the chain in the tree
        }
        return current;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = getNode(word);

        return current != null && current.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return (getNode(prefix) != null);
    }

    class TrieNode {
        private char letter;
        private boolean isWord;
        private TrieNode[] children;

        public TrieNode(char letter) {
            this.letter = letter;
            isWord = false;
            children = new TrieNode[26];
        }
    }
}
