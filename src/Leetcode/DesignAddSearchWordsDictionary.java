package Leetcode;

/**
 * @author kalpak
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word)
 *      Adds word to the data structure, it can be matched later.
 *
 * bool search(word)
 *      Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 500
 * word in addWord consists lower-case English letters.
 * word in search consist of  '.' or lower-case English letters.
 * At most 50000 calls will be made to addWord and search.
 */

public class DesignAddSearchWordsDictionary {
    class TrieNodeWordSearch {
        TrieNodeWordSearch[] children;
        boolean isWord;

        public TrieNodeWordSearch() {
            children = new TrieNodeWordSearch[26];
            boolean isWord = false;
        }
    }

    private TrieNodeWordSearch root;

    /** Initialize your data structure here. */
    public DesignAddSearchWordsDictionary() {
        root = new TrieNodeWordSearch();
    }

    public void addWord(String word) {
        TrieNodeWordSearch current = root;

        for(char c : word.toCharArray()) {
            int index = (int)(c - 'a');
            if(current.children[index] == null) {
                current.children[index] = new TrieNodeWordSearch();
            }
            current = current.children[index];
        }

        current.isWord = true;
    }

    public boolean search(String word) {
        return find(word, root, 0);
    }

    private boolean find(String word, TrieNodeWordSearch current, int index) {
        // base case
        if(index == word.length())
            return current.isWord;

        // get the current character
        char c = word.charAt(index);

        if(c == '.') {
            // try all 26 possibilities
            for(int i = 0; i < 26; i++) {
                if(current.children[i] != null && find(word, current.children[i], index+1))
                    return true;
            }
            return false;
        } else {
            return current.children[c-'a'] != null && find(word, current.children[c-'a'], index+1);
        }
    }

    public static void main(String[] args) {
        DesignAddSearchWordsDictionary wordDictionary = new DesignAddSearchWordsDictionary();

        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }
}
