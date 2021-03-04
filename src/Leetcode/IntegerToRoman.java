package Leetcode;

/**
 * @author kalpak
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together.
 * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 *
 * The same principle applies to the number nine, which is written as IX.
 *
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 *
 * Given an integer, convert it to a roman numeral.
 *
 * Example 1:
 * Input: num = 3
 * Output: "III"
 *
 * Example 2:
 * Input: num = 4
 * Output: "IV"
 *
 * Example 3:
 * Input: num = 9
 * Output: "IX"
 *
 * Example 4:
 * Input: num = 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 *
 * Example 5:
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 *
 * Constraints:
 *
 * 1 <= num <= 3999
 */

public class IntegerToRoman {
    private static Numeral[] numerals = {
            new Numeral("M", 1000),
            new Numeral("CM", 900),
            new Numeral("D", 500),
            new Numeral("CD", 400),
            new Numeral("C", 100),
            new Numeral("XC", 90),
            new Numeral("L", 50),
            new Numeral("XL", 40),
            new Numeral("X", 10),
            new Numeral("IX", 9),
            new Numeral("V", 5),
            new Numeral("IV", 4),
            new Numeral("I", 1)
    };

    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();

        for(Numeral numeral : numerals) {
            int numberOfSymbols = num / numeral.value;
            if(numberOfSymbols != 0) {
                while(numberOfSymbols-- > 0)
                    result.append(numeral.symbol);
            }

            num %= numeral.value;
        }
        return result.toString();
    }

    static class Numeral {
        public String symbol;
        public int value;

        public Numeral(String symbol, int value) {
            this.symbol = symbol;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3465));
    }
}
