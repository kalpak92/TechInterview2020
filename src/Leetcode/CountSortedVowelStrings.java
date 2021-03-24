package Leetcode;

/**
 * @author kalpak
 *
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 *
 * Example 1:
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 *
 *
 * Example 2:
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 *
 *
 * Example 3:
 * Input: n = 33
 * Output: 66045
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 *
 */

public class CountSortedVowelStrings {
    public static int countVowelStringsBacktracking(int n) {
        return countVowelStringsBacktracking(n, 1);
    }

    private static int countVowelStringsBacktracking(int n, int numberOfVowels) {
        if(n == 0)
            return 1;

        int result = 0;

        for(int i = numberOfVowels; i <= 5; i++) {
            result += countVowelStringsBacktracking(n - 1, i);
        }
        return result;
    }

    public static int countVowelStringsRecursive(int n) {
        return countVowelStringsRecursive(n, 5);
    }

    private static int countVowelStringsRecursive(int n, int numberOfVowels) {
        if(n == 1)
            return numberOfVowels;

        if(numberOfVowels == 1)
            return 1;

        return countVowelStringsRecursive(n - 1, numberOfVowels) + countVowelStringsRecursive(n, numberOfVowels - 1);
    }

    public static int countVowelStringsTopDown(int n) {
        int[][] dp = new int[n + 1][5 + 1];

        return countVowelStringsTopDown(n, dp, 5);
    }

    private static int countVowelStringsTopDown(int n, int[][] dp, int numberOfVowels) {
        if(n == 1)
            return numberOfVowels;

        if(numberOfVowels == 1)
            return 1;

        if(dp[n][numberOfVowels] != 0)
            return dp[n][numberOfVowels];

        int result = countVowelStringsTopDown(n - 1, dp, numberOfVowels) + countVowelStringsTopDown(n, dp, numberOfVowels - 1);

        dp[n][numberOfVowels] = result;
        return dp[n][numberOfVowels];
    }

    public static void main(String[] args) {
        System.out.println(countVowelStringsBacktracking(5));
        System.out.println(countVowelStringsRecursive(5));
        System.out.println(countVowelStringsTopDown(5));
    }
}
