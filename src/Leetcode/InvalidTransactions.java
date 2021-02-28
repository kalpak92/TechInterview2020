package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * A transaction is possibly invalid if:
 *
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.
 *
 * Return a list of transactions that are possibly invalid. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 * Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
 * Example 2:
 *
 * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * Output: ["alice,50,1200,mtv"]
 * Example 3:
 *
 * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * Output: ["bob,50,1200,mtv"]
 *
 *
 * Constraints:
 *
 * transactions.length <= 1000
 * Each transactions[i] takes the form "{name},{time},{amount},{city}"
 * Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
 * Each {time} consist of digits, and represent an integer between 0 and 1000.
 * Each {amount} consist of digits, and represent an integer between 0 and 2000.
 */

public class InvalidTransactions {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> result = new ArrayList<>();
        Map<String, List<Transaction>> map = new HashMap<>();
        Set<String> invalidTransaction = new HashSet<>();

        for(String transaction : transactions) {
            String[] tokens = transaction.split(",");

            Transaction obj =
                    new Transaction(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);

            // Check constraint 1
            if(obj.getAmount() > 1000)
                invalidTransaction.add(obj.getTransactionInfo());

            List<Transaction> otherTransactions = map.get(obj.getName());

            if (otherTransactions == null) {
                otherTransactions = new ArrayList<Transaction>();
                otherTransactions.add(obj);
                map.put(obj.getName(), otherTransactions);
            } else {
                for(Transaction t : otherTransactions) {
                    if(!t.getCity().equals(obj.getCity()) && Math.abs(obj.getTime() - t.getTime()) <= 60) {
                        invalidTransaction.add(obj.getTransactionInfo());
                        invalidTransaction.add(t.getTransactionInfo());
                    }
                }
                otherTransactions.add(obj);
            }
        }

        result = new ArrayList<String>(invalidTransaction);
        return result;
    }

    class Transaction {
        private String name;
        private int amount;
        private int time;
        private String city;

        public Transaction(String name, int time, int amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }

        public String getTransactionInfo() {
            return this.name+","+this.time+","+this.amount+","+this.city;
        }

        public int getAmount() {
            return this.amount;
        }

        public String getName() {
            return this.name;
        }

        public String getCity() {
            return this.city;
        }

        public int getTime() {
            return this.time;
        }
    }
}
