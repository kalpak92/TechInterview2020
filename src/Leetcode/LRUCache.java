package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *
 * - void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * Follow up:
 * Could you do get and put in O(1) time complexity?
 *
 *
 * Example 1:
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 3000
 * 0 <= value <= 104
 * At most 3 * 104 calls will be made to get and put.
 */

public class LRUCache {
    Map<Integer, DataNode> map;
    DataNode head;
    DataNode tail;

    int itemsInCache;
    int maxCapacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();

        head = new DataNode();
        tail = new DataNode();

        head.next = tail;
        tail.prev = head;

        maxCapacity = capacity;
        itemsInCache = 0;

    }

    public int get(int key) {
        DataNode result = map.get(key);

        if(result != null) {
            moveToHead(result);
            return result.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DataNode node = map.get(key);

        if(node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            DataNode data = new DataNode(key, value);

            map.put(key, data);

            itemsInCache++;
            addToFront(data);

            if(itemsInCache > maxCapacity) {
                removeFromCache();
                itemsInCache--;
            }
        }
    }

    private void moveToHead(DataNode node) {
        removeData(node);
        addToFront(node);
    }

    private void removeFromCache() {
        DataNode tailNode  = tail.prev;
        removeData(tailNode);

        map.remove(tailNode.key);
    }

    private void removeData(DataNode node) {
        DataNode prevNode = node.prev;
        DataNode nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void addToFront(DataNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }


    private class DataNode {
        int key;
        int value;

        DataNode prev;
        DataNode next;

        public DataNode() {
            prev = next = null;
        }

        public DataNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
