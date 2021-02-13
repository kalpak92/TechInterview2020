package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * There is a new alien language that uses the English alphabet. However, the order among letters are unknown to you.
 *
 * You are given a list of strings words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 *
 * Derive the order of letters in this language, and return it. If the given input is invalid, return "".
 * If there are multiple valid solutions, return any of them.
 *
 *
 * Example 1:
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 *
 * Example 2:
 * Input: words = ["z","x"]
 * Output: "zx"
 *
 * Example 3:
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 *
 */

public class AlienDictionary {
    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] indegree = new int[26];

        buildGraph(graph, indegree, words);
        String result = doOrderingBFS(graph, indegree);
        return result;
    }

    private static String doOrderingBFS(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder result = new StringBuilder();
        Deque<Character> queue = new LinkedList<>();


        // Add all characters with indegree 0 to result and queue
        for(char c : graph.keySet()) {
            if(indegree[c-'a'] == 0) {
                result.append(c);
                queue.offer(c);
            }
        }

        while(!queue.isEmpty()) {
            char current = queue.poll();

            if(graph.get(current) == null || graph.get(current).size() == 0)
                continue;

            for(char c : graph.get(current)) {
                indegree[c-'a']--;

                if(indegree[c-'a'] == 0) {
                    result.append(c);
                    queue.offer(c);
                }
            }
        }

        return (result.length() == graph.size()) ? result.toString() : "";
    }

    private static void buildGraph(Map<Character, Set<Character>> graph, int[] indegree, String[] words) {
        for(String word : words) {
            for(char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        for(int i = 1; i < words.length; i++) {
            String firstWord = words[i - 1];
            String secondWord = words[i];

            // Check If second word is a prefix of first word,
            // If thats the case its not a valid alien dictionary
            if(firstWord.startsWith(secondWord) && firstWord.length() > secondWord.length()) {
                graph.clear();
                return;
            }

            int len = Math.min(firstWord.length(), secondWord.length());

            for(int j = 0; j < len; j++) {
                if(firstWord.charAt(j) != secondWord.charAt(j)) {
                    char from = firstWord.charAt(j);
                    char to = secondWord.charAt(j);

                    if(!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        indegree[to - 'a']++;
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"wrt","wrf","er","ett","rftt"};
        System.out.println(alienOrder(words));
    }
}
