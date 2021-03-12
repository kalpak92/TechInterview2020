package Leetcode;

/**
 * @author kalpak
 *
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 *
 * Example 1:
 *
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 *
 * Constraints:
 *
 * 1 <= key.length, prefix.length <= 50
 * key and prefix consist of only lowercase English letters.
 * 1 <= val <= 1000
 * At most 50 calls will be made to insert and sum.
 *
 */

public class MapSumPairs {
    TrieNode root;
    /** Initialize your data structure here. */
    public MapSumPairs() {
        root = new TrieNode('\0');
    }

    public void insert(String key, int val) {
        TrieNode current = root;

        for (char c : key.toCharArray()) {
            if(current.children[c-'a'] == null)
                current.children[c-'a'] = new TrieNode(c);

            current = current.children[c-'a'];
        }
        current.val = val;
    }

    public int sum(String prefix) {
        TrieNode current = root;

        for (char c : prefix.toCharArray()) {
            if(current.children[c-'a'] == null)
                return 0;

            current = current.children[c-'a'];
        }

        return getSumDFS(current);
    }

    private int getSumDFS(TrieNode node) {
        int sum = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if(node.children[c-'a'] != null) {
                sum += getSumDFS(node.children[c-'a']);
            }
        }
        return sum + node.val;
    }

    class TrieNode {
        private char letter;
        private TrieNode[] children;
        private int val;

        public TrieNode(char letter) {
            this.letter = letter;
            this.children = new TrieNode[26];
            this.val = 0;

        }
    }
}
