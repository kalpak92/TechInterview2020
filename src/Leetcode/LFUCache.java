package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * - int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * - void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
 * When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item.
 *
 * For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for each key in the cache.
 * The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
 * The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[3,4], cnt(4)=2, cnt(3)=3
 *
 *
 * Constraints:
 *
 * 0 <= capacity, key, value <= 104
 * At most 105 calls will be made to get and put.
 *
 *
 * Follow up: Could you do both operations in O(1) time complexity?
 */

public class LFUCache {
    int maxCapacity;
    int itemsInCache;
    int minFreq;

    Map<Integer, DataNode> keyMap;
    Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        maxCapacity = capacity;
        itemsInCache = minFreq = 0;

        keyMap = new HashMap<>();
        freqMap = new HashMap<>();

        freqMap.put(0,new DoublyLinkedList(0));
    }

    public int get(int key) {
        if(!keyMap.containsKey(key))
            return -1;

        DataNode node = keyMap.get(key);
        return  _update(node);
    }

    private int _update(DataNode node) {
        int freq = node.freq;
        freqMap.get(freq).remove(node);

        node.freq ++;
        freqMap.computeIfAbsent(node.freq, k->new DoublyLinkedList(node.freq)).addNode(node);

        while (freqMap.get(minFreq).isEmpty())
            minFreq++;

        return node.value;
    }

    public void put(int key, int value) {
        if(maxCapacity == 0)
            return;

        if (keyMap.containsKey(key)) {
            DataNode node = keyMap.get(key);
            node.value = value;
            _update(node);
            return ;
        }

        if(itemsInCache >= maxCapacity) {
            DataNode old = freqMap.get(minFreq).popNode();
            keyMap.remove(old.key);
            itemsInCache--;
        }

        DataNode node = new DataNode(key, value);
        freqMap.get(0).addNode(node);
        keyMap.put(key,node);
        minFreq = 0;
        itemsInCache++;
    }

    class DoublyLinkedList {
        int freq;
        DataNode head;
        DataNode tail;

        public DoublyLinkedList (int freq){
            this.freq =  freq;

            head = new DataNode(0, 0);
            tail = new DataNode(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        public void addNode(DataNode node) {
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        public void remove(DataNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        public boolean isEmpty() {
            return head.next==tail;
        }

        public DataNode popNode() {
            if(isEmpty())
                return null;

            DataNode node = tail.prev;
            remove(node);
            return node;
        }
    }

    class DataNode {
        int key;
        int value;
        int freq;

        DataNode prev;
        DataNode next;

        DataNode(int key,int value){
            this.key = key;
            this.value = value;
            this.freq = 0;

            prev = next = null;
        }
    }
}
