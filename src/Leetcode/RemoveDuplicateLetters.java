package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 *
 */

public class RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s) {
        Map<Character, Integer> lastOccurence = new HashMap<>();
        Set<Character> isVisited = new HashSet<>();
        Deque<Character> stack = new ArrayDeque<>();    // answer stack
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            lastOccurence.put(s.charAt(i) , i);
        }

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(isVisited.contains(c))      // if character is already present in stack, dont bother
                continue;

            //if current character is smaller than last character in stack which occurs later in the string again
            //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
            while(!stack.isEmpty() &&  c < stack.peek() && lastOccurence.get(stack.peek()) > i) {
                isVisited.remove(stack.pop());
            }

            stack.push(c); //add current character and mark it as visited
            isVisited.add(c);
        }

        //pop character from stack and build answer string from back
        while(!stack.isEmpty()){
            result.insert(0, stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
    }
}
