package String;

import java.util.ArrayList;
import java.util.List;

/**
 * We are going to count the consecutive occurrences of the characters in a given string.
 * The character may repeat multiple time with different consecutive counts.
 *
 * Characters count should not be case sensitive. Lets look at some of the examples.
 *
 * Ex1: Input: aahnmkhhlkknjm
 * Output: a : 2, h : 1, n : 1, m : 1, k : 1, h : 2, l : 1, k : 2, n : 1, j : 1, m : 1
 *
 * Ex2: Input: recommended
 * Output: r : 1, e : 1, c : 1, o : 1, m : 2, e : 1, n : 1, d : 1, e : 1, d : 1
 *
 * Ex3: Input: recoMmended
 * Output: r : 1, e : 1, c : 1, o : 1, m : 2, e : 1, n : 1, d : 1, e : 1, d : 1
 *
 */

public class ConsecutiveOccurenceOfCharacters {
    public static List<String> getConsecutiveOccurrenceOfChar(String str) {
        List<String> result = new ArrayList<>();

        char current = str.charAt(0);
        int count = 1;

        for(int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if(current == c) {
                count++;
            } else {
                result.add(current + " : " + count);
                count = 1;
                current = c;
            }
        }
        result.add(current + " : " + count);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getConsecutiveOccurrenceOfChar("aaaabbbcca"));
    }
}
