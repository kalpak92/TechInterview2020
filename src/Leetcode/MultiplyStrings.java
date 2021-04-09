package Leetcode;

/**
 * @author kalpak
 *
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 *
 * Constraints:
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 *
 */

public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        // Compute products from each pair of digits from num1 and num2
        int n1 = num1.length();
        int n2 = num2.length();

        int[] products = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';

                products[i + j + 1] += d1 * d2;
            }
        }

        // carry each element over.
        int carry = 0;

        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }

        // Output the result
        StringBuilder result = new StringBuilder();
        for(int i : products)
            result.append(i);

        while (result.length() != 0 && result.charAt(0) == '0')
            result.deleteCharAt(0);

        return result.length() == 0 ? "0" : result.toString();

    }

    public static void main(String[] args) {
        System.out.println(multiply("123", "456"));
    }
}
