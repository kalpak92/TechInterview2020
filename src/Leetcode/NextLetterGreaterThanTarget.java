package Leetcode;

/**
 * @author kalpak
 *
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target,
 * find the smallest element in the list that is larger than the given target.
 *
 * Letters also wrap around.
 * For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 *
 * Examples:
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 * Note:
 *
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique letters.
 * target is a lowercase letter.
 *
 */
public class NextLetterGreaterThanTarget {
    public static char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        char result = '#';

        while (left <= right) {
            int mid = left + (right - left)/2;

            if(letters[mid] == target) {
                left = mid + 1;
            }
            else if (letters[mid] < target) {
                left = mid + 1;
            } else if (letters[mid] > target) {
                right = mid - 1;
                result = letters[mid];
            }
        }
        return (left >= letters.length) ? letters[0] : result;  // wrap around if letter to be added in the end
    }

    public static void main(String[] args) {
        char[] letters = new char[]{'c', 'f', 'j'};
        System.out.println(nextGreatestLetter(letters, 'a'));
        System.out.println(nextGreatestLetter(letters, 'c'));
        System.out.println(nextGreatestLetter(letters, 'd'));
        System.out.println(nextGreatestLetter(letters, 'j'));
        System.out.println(nextGreatestLetter(letters, 'k'));

    }
}
