package DynamicProgramming;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given a string of size ‘n’.
 * The task is to remove or delete the minimum number of characters from the string so that the resultant string is a palindrome.
 *
 * Note: The order of characters should be maintained.
 *
 * Examples :
 * Input : aebcbda
 * Output : 2
 * Remove characters 'e' and 'd'
 * Resultant string will be 'abcba'
 * which is a palindromic string
 *
 * Input : geeksforgeeks
 * Output : 8
 *
 */

public class MinimumNumberOfDeletionsToMakePalindrome {
    // Time Complexity : O(3^N)
    public static int findMinimumDeletions(String s) {
        return findMinimumDeletionsRecursive(s, 0, s.length() - 1);
    }

    private static int findMinimumDeletionsRecursive(String s, int start, int end) {
        if(start >= end)
            return 0;

        if (s.charAt(start) == s.charAt(end))
            return findMinimumDeletionsRecursive(s, start + 1, end - 1);

        int c1 = 1 + findMinimumDeletionsRecursive(s, start + 1, end);
        int c2 = 1 + findMinimumDeletionsRecursive(s, start, end - 1);

        return Math.min(c1, c2);
    }

    public static int findMinimumDeletionsTopDown(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for(int[] row : dp)
            Arrays.fill(row, -1);

        return findMinimumDeletionsTopDown(s, dp, 0, s.length() - 1);
    }

    private static int findMinimumDeletionsTopDown(String s, int[][] dp, int start, int end) {
        if(start >= end)
            return 0;

        if(dp[start][end] == -1) {
            if(s.charAt(start) == s.charAt(end)) {
                dp[start][end] = findMinimumDeletionsTopDown(s, dp, start + 1, end - 1);
                return dp[start][end];
            }

            int c1 = 1 + findMinimumDeletionsRecursive(s, start + 1, end);
            int c2 = 1 + findMinimumDeletionsRecursive(s, start, end - 1);

            dp[start][end] = Math.min(c1, c2);
        }
        return dp[start][end];
    }

    public static void main(String[] args) {
        System.out.println(findMinimumDeletions("abdbca"));
        System.out.println(findMinimumDeletionsTopDown("abdbca"));
    }
}
