package Leetcode;

/**
 * @author kalpak
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * Follow up:
 *
 * Could you solve the problem in O(1) extra memory space?
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * Example 3:
 *
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 * Example 4:
 *
 * Input: head = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range sz.
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */

public class ReverseLinkedListInKgroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head;
        int count = 0;

        while(current != null && count != k) {
            current = current.next;
            count++;
        }

        if (count == k) {
            ListNode prev = reverseKGroup(current, k);  // Store the result of the recursive call as the previous node to be connected.
            current = head;                             // recentre the current node.
            while(count > 0) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;

                count --;
            }
            head = prev;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode result = reverseKGroup(l1, 2);

        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
