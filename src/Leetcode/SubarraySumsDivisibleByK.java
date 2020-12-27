package Leetcode;

/**
 * @author kalpak
 *
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 *
 * Example 1:
 *
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class SubarraySumsDivisibleByK {
    public static int subarraysDivByK(int[] A, int K) {
        /**
         * There K mod groups 0...K-1
         * For each prefix sum that does not have remainder 0 it needs to be paired with 1 with the same remainder
         * this is so that the remainders cancel out.
         */
        int[] modFrequency = new int[K];
        int result = 0;
        int runningSum = 0;

        for(int i : A) {
            runningSum += i;

            int rem = runningSum % K;   // Java has negative remainders
            if(rem < 0) {
                rem += K;
            }
            modFrequency[rem]++;
        }
        for(int mod : modFrequency) {
            if(mod > 1) {
                result += mod*(mod - 1)/2;
            }
        }
        return result + modFrequency[0];    // including all numbers that divide K
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,5,0,-2,-3,1};
        System.out.println(subarraysDivByK(arr, 5));
    }
}
