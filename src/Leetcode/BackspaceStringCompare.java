package Leetcode;

/**
 * @author kalpak
 *
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 *
 * Can you solve it in O(N) time and O(1) space?
 */

public class BackspaceStringCompare {
    public static boolean backspaceCompare(String S, String T) {
        // Start the pointers from the back
        int i = S.length() - 1;
        int j = T.length() - 1;

        int skipCountS = 0;
        int skipCountT = 0;

        while(i >= 0 || j >= 0) {
            while(i >= 0) {
                if(S.charAt(i) == '#') {    // if there's a #, we need to skip the next character
                    skipCountS++;
                    i--;
                } else if(skipCountS > 0) {
                    skipCountS--;
                    i--;
                } else
                    break;
            }

            while(j >= 0) {
                if(T.charAt(j) == '#') {    // if there's a #, we need to skip the next character
                    skipCountT++;
                    j--;
                } else if(skipCountT > 0) {
                    skipCountT--;
                    j--;
                } else
                    break;
            }


            // Now that the housekeeping of backspace is done, check if the characters are equal.
            if(i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;

            // Check if the indices are same or not. If not, no point checking further.
            if((i >= 0) != (j >= 0))
                return false;

            i--;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str1 = "ab##";
        String str2 = "cd##";
        String str3 = "ab#c";
        String str4 = "ad#c";

        System.out.println(backspaceCompare(str1, str2));
        System.out.println(backspaceCompare(str3, str4));
    }
}
