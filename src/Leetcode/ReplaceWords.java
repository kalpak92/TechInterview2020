package Leetcode;

import java.util.List;

/**
 * @author kalpak
 *
 * In English, we have a concept called root, which can be followed by some other word to form another longer word -
 * let's call this word successor.
 *
 * For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
 *
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
 * replace all the successors in the sentence with the root forming it.
 *
 * If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
 *
 * Return the sentence after the replacement.
 *
 *
 * Example 1:
 * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 *
 * Example 2:
 * Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * Output: "a a b c"
 *
 * Example 3:
 * Input: dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 * Output: "a a a a a a a a bbb baba a"
 *
 *
 * Example 4:
 * Input: dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 *
 * Example 5:
 * Input: dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
 * Output: "it is ab that this solution is ac"
 *
 *
 * Constraints:
 *
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case letters.
 * 1 <= sentence.length <= 10^6
 * sentence consists of only lower-case letters and spaces.
 * The number of words in sentence is in the range [1, 1000]
 * The length of each word in sentence is in the range [1, 1000]
 * Each two consecutive words in sentence will be separated by exactly one space.
 * sentence does not have leading or trailing spaces.
 *
 */

public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();

        // Build the Trie using the dictionary
        for(String word: dictionary) {
            TrieNode current = root;
            for(char letter : word.toCharArray()) {
                if(current.children[letter - 'a'] == null)
                    current.children[letter - 'a'] = new TrieNode();

                current = current.children[letter - 'a'];
            }
            current.word = word;
        }

        StringBuilder result = new StringBuilder();
        for(String word: sentence.split("\\s")) {
            if(result.length() > 0)
                result.append(" ");

            TrieNode current = root;
            for(char letter : word.toCharArray()) {
                // Break for the shortest length successor
                if(current.children[letter - 'a'] == null || current.word != null)
                    break;

                current = current.children[letter - 'a'];
            }
            // add the word to the result, either from the tree or the word itself.
            // If the word is there on the trie, use it
            result.append(current.word != null ? current.word : word);
        }
        return result.toString();
    }

    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }
}
