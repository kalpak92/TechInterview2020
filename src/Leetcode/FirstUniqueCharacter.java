package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode"
 * return 2.
 *
 *
 *
 * Note: You may assume the string contains only lowercase English letters.
 */

public class FirstUniqueCharacter {
    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public static int firstUniqCharUsingTwoPointers(String s) {

        // Use a slow pointer to point to the current unique character
        // A fast pointer to scan the string.
        // The fast pointer not only just add the count of the character. Meanwhile, when fast pointer finds the identical character of the character at the current slow pointer, we move the slow pointer to the next unique character or not visited character.

        if(s == null || s.length() == 0)
            return -1;

        int slow = 0;
        int fast = 0;
        char[] chs = s.toCharArray();
        int[] count=new int[256];

        int n = chs.length;
        while (fast < n) {
            count[chs[fast]]++;
            while (slow < n && count[chs[slow]] > 1){
                slow++;
            }

            if (slow == n)
                return -1;

            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }
}
