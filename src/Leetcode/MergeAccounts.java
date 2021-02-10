package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts.
 * Two accounts definitely belong to the same person if there is some email that is common to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 *
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 * and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Note:
 *
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 *
 */

public class MergeAccounts {
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();   // email : index of person

        for(int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);

            // Get the emails
            for(int j = 1; j < account.size(); j++) {
                String email = account.get(j);

                List<Integer> temp = map.get(email);
                if(temp == null) {
                    temp = new ArrayList<>();
                    map.put(email, temp);
                }
                temp.add(i);
            }
        }

        Set<Integer> visited = new HashSet<>();

        for(int i = 0; i < accounts.size(); i++) {
            Set<String> emails = new TreeSet<>();
            if(!visited.contains(i)) {
                dfsRecursive(accounts, map, emails, visited, i);
                //post-processing after dfs
                // emails have all the emails corresponding to person accounts(i).(0)
                if(!emails.isEmpty()) {
                    List<String> temp = new ArrayList<>(emails);
                    temp.add(0, accounts.get(i).get(0));
                    result.add(temp);
                }
            }
        }
        return result;
    }

    private static void dfsRecursive(List<List<String>> accounts, Map<String, List<Integer>> map, Set<String> emails,
                              Set<Integer> visited, int start) {
        visited.add(start);

        // Iterate over the emails of the current account.
        // for each of the email, dfs on the person with same email id using information from the map
        for(int i = 1; i < accounts.get(start).size(); i++) {
            String email = accounts.get(start).get(i);
            emails.add(email);

            // dfs
            for(int index : map.get(email)) {
                if(!visited.contains(index))
                    dfsRecursive(accounts, map, emails, visited, index);
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList(new String[]{"John","johnsmith@mail.com","john_newyork@mail.com"})));
        accounts.add(new ArrayList<>(Arrays.asList(new String[]{"John","johnsmith@mail.com","john00@mail.com"})));
        accounts.add(new ArrayList<>(Arrays.asList(new String[]{"Mary","mary@mail.com"})));
        accounts.add(new ArrayList<>(Arrays.asList(new String[]{"John","johnnybravo@mail.com"})));

        System.out.println(accountsMerge(accounts));
    }
}
