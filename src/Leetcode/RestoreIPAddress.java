package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.
 *
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255,
 * separated by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses
 * and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 *
 *
 * Example 1:
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 *
 *
 * Example 2:
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 *
 *
 * Example 3:
 * Input: s = "1111"
 * Output: ["1.1.1.1"]
 *
 * Example 4:
 * Input: s = "010010"
 * Output: ["0.10.0.10","0.100.1.0"]
 *
 * Example 5:
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * Constraints:
 *
 * 0 <= s.length <= 3000
 * s consists of digits only.
 *
 */


public class RestoreIPAddress {
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        backtrack(result, current, s, 0, 0);
        return result;
    }

    public static void backtrack(List<String> result, StringBuilder current, String s, int index, int level) {
        if(index > s.length() || level > 4)
            return;

        if(index == s.length() && level == 4) { // End of String
            result.add(current.toString());
            return;
        }

        for(int len = 1; len <= 3; len++) {
            if(index + len > s.length())    // out of the string
                break;

            int value;
            value = Integer.parseInt(s.substring(index, index + len));

            if(len == 1 || len == 2 && value >= 10 && value <=99 || len == 3 && value >= 100 && value <= 255) {
                // Add the number since criteria has been met
                current.append(value);
                if(level < 3)
                    current.append(".");

                backtrack(result, current, s, index + len, level+1);

                // Undo the changes and return bac.
                if(level < 3)
                    current.deleteCharAt(current.length() - 1);

                current.delete(current.length() - len, current.length());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }
}
