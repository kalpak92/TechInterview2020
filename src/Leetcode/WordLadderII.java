package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

public class WordLadderII {
    static List<List<String>> result = new ArrayList<>();

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> givenDictionary = new HashSet<>(wordList);
        List<String> path = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        int level = 0;
        int depth = 0;

        if(!givenDictionary.contains(endWord))
            return result;

        queue.add(beginWord);
        visited.put(beginWord, 0);                      // level of initial word will be 0

        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;

            while(size-- > 0) {
                String currentWord = queue.poll();

                if(currentWord.equals(endWord)) {
                    if(depth == 0)
                        depth = level;
                    continue;
                }

                char[] temp = currentWord.toCharArray();
                ArrayList<String> adjacency = new ArrayList<>();

                for(int i = 0; i < temp.length; i++) {      // For all positions
                    char backup = temp[i];
                    for(char ch = 'a'; ch <= 'z'; ch++) {   // For all characters
                        if(ch == backup)                    // skip if letter is same as the original one
                            continue;

                        temp[i] = ch;
                        String nextWord = String.valueOf(temp);     // create new word

                        if(givenDictionary.contains(nextWord)) {    // if the new word is a valid word from the dictionary
                            if(visited.getOrDefault(nextWord, 1000) >= level) {        // check if the new word is visited or not
                                // if not visited, update the visited map with the respective level id
                                visited.put(nextWord, level);
                                queue.offer(nextWord);              // Add it to the queue for BFS traversal
                                adjacency.add(nextWord);
                            }
                        }
                    }
                    temp[i] = backup;       // revert back the word
                }
                graph.put(currentWord, adjacency);
            }
        }


        traversePossiblePathsDFS(beginWord, endWord, graph, path);  // Find all possible paths
        return result;
    }

    private static void traversePossiblePathsDFS(String beginWord, String endWord, Map<String, List<String>> graph, List<String> path) {
        path.add(beginWord);

        if(beginWord.equals(endWord)) {
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);       // backtrack
            return;
        }

        for(String nextWord : graph.get(beginWord))
            traversePossiblePathsDFS(nextWord, endWord, graph, path);

        path.remove(path.size() - 1);          // backtrack
    }

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>(Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}));
        System.out.println(findLadders("hit", "cog", wordList));
    }
}
