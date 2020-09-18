package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input:
 * "tree"
 *
 * Output:
 * "eert"
 *
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 *
 * Input:
 * "cccaaa"
 *
 * Output:
 * "cccaaa"
 *
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 *
 * Input:
 * "Aabb"
 *
 * Output:
 * "bbAa"
 *
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */

public class SortCharacters {
    public static String frequencySort(String s) {
        Map<Character, Integer> characterFreq = new HashMap<>();
        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        StringBuilder result = new StringBuilder();

        for(char c : s.toCharArray()) {
            characterFreq.put(c, characterFreq.getOrDefault(c, 0) + 1);
        }

        maxHeap.addAll(characterFreq.entrySet());
        while(!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> temp = maxHeap.poll();

            for(int i = 0; i < temp.getValue(); i++) {
                result.append(temp.getKey());
            }
        }
        return result.toString();
    }

    public static String frequencySortMaintainingOrder(String s) {
        Map<Character, int[]> characterFrequency = new HashMap<>();
        PriorityQueue<Map.Entry<Character, int[]>> pq = new PriorityQueue<>((a, b) -> a.getValue()[0] == b.getValue()[0]
                    ? a.getValue()[1] - b.getValue()[1] : b.getValue()[0] - a.getValue()[0]);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!characterFrequency.containsKey(c))
                characterFrequency.put(c, new int[]{1, i});
            else {
                int[] freqAndSeq = characterFrequency.get(c);
                freqAndSeq[0]++;
                characterFrequency.put(c, freqAndSeq);
            }
        }

        pq.addAll(characterFrequency.entrySet());

        while (!pq.isEmpty()) {
            Map.Entry<Character, int[]> temp = pq.poll();
            for (int i = 0; i < temp.getValue()[0]; i++)
                result.append(temp.getKey());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
        System.out.println(frequencySort("Aabb"));
    }
}
