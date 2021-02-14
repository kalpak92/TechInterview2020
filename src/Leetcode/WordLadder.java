package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words such that:
 *
 * The first word in the sequence is beginWord.
 * The last word in the sequence is endWord.
 * Only one letter is different between each adjacent pair of words in the sequence.
 * Every word in the sequence is in wordList.
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 *
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog" with 5 words.
 *
 *
 * Example 2:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no possible transformation.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the strings in wordList are unique.
 *
 */

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> givenDictionary = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int level = 0;

        queue.offer(beginWord);
        visited.add(beginWord);

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size > 0) {
                String word = queue.poll();
                // check if word is the destination word
                if(word.equals(endWord))
                    return level + 1;

                // Generate all permutations of the word by changing each character from [a-z]
                char[] temp = word.toCharArray();
                // for each position
                for(int i = 0; i < temp.length; i++) {
                    char backup = temp[i];
                    // check from [a-z]
                    for(char ch = 'a'; ch <= 'z'; ch++) {
                        temp[i] = ch;
                        String nextWord = String.valueOf(temp);

                        if(!visited.contains(nextWord) && givenDictionary.contains(nextWord)) {
                            queue.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                    temp[i] = backup;
                }
                size--;
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>(Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}));
        System.out.println("The number of words in the shortest transformation is : " + ladderLength("hit", "cog", wordList));
    }
}
