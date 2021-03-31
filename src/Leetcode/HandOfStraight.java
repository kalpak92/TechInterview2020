package Leetcode;

import java.util.TreeMap;

/**
 * @author kalpak
 *
 * Alice has a hand of cards, given as an array of integers.
 *
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 *
 * Return true if and only if she can.
 *
 * Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 *
 *
 * Example 1:
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 *
 * Example 2:
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 *
 *
 *
 * Constraints:
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 *
 */

public class HandOfStraight {
    public static boolean isNStraightHand(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return true;

        if (nums.length % k != 0)
            return false;

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();

        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        while(freqMap.size() > 0) {
            int min = freqMap.firstKey();           // O(log n) lookup
            int minOccurrences = freqMap.get(min);  // O(log n) lookup

            for (int i = 0; i < k; ++i) {
                // Check if we can create minOccurrences sequences of k consecutive numbers with the min as the first number.
                if (freqMap.containsKey(min + i) && minOccurrences <= freqMap.get(min + i)) {
                    freqMap.put(min + i, freqMap.get(min + i) - minOccurrences);

                    if (freqMap.get(min + i) == 0)
                        freqMap.remove(min + i);     // Remove min+1 if there are no more occurrences left.
                } else
                    return false;   // min doesn't belong to a group of k consecutive numbers so we return false.
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] hands = new int[]{1,2,3,6,2,3,4,7,8};
        System.out.println(isNStraightHand(hands, 3));
    }
}
