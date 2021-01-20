package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author kalpak
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * Example 2:
 * Input: lists = []
 * Output: []
 *
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; this.next = null; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);
        ListNode current = result;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Put the first element from each of the lists
        for(ListNode node : lists) {
            if(node != null)
                minHeap.offer(node);
        }

        while(!minHeap.isEmpty()) {
            ListNode temp = minHeap.poll();
            current.next = temp;

            if(temp.next != null)
                minHeap.offer(temp.next);

            current = current.next;
        }

        return result.next;
    }

    public static void printListNode(ListNode result) {
        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        list1.next = node2;
        node2.next = node3;

        ListNode list2 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        list2.next = node5;
        list2.next = node6;

        ListNode list3 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        list3.next = node8;

        ListNode[] lists = new ListNode[3];
        lists[0]= list1;
        lists[1] = list2;
        lists[2] = list3;

        printListNode(mergeKLists(lists));
    }
}
