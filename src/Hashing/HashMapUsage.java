package Hashing;

import java.util.HashMap;
import java.util.Map;

public class HashMapUsage {

    public static void main(String[] args) {
        // 1. initialize a hash map
        Map<Integer, Integer> hashmap = new HashMap<>();

        // 2. insert a new (key, value) pair
        hashmap.putIfAbsent(0, 0);
        hashmap.putIfAbsent(2, 3);

        // 3. insert a new (key, value) pair or update the value of existed key
        hashmap.put(1, 1);
        hashmap.put(1, 2);

        // 4. get the value of specific key
        System.out.println("The value of key 1 is: " + hashmap.get(1));

        // 5. delete a key
        hashmap.remove(2);

        // 6. check if a key is in the hash map
        if (!hashmap.containsKey(2)) {
            System.out.println("Key 2 is not in the hash map.");
        }

        // 7. get the size of the hash map
        System.out.println("The size of hash map is: " + hashmap.size());

        // 8. iterate the hash map
        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            System.out.print("(" + entry.getKey() + "," + entry.getValue() + ") ");
        }
        System.out.println("are in the hash map.");

        // 9. clear the hash map
        hashmap.clear();

        // 10. check if the hash map is empty
        if (hashmap.isEmpty()) {
            System.out.println("hash map is empty now!");
        }
    }
}
