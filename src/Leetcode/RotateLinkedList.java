package Leetcode;

/**
 * @author kalpak
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */

public class RotateLinkedList {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;

        ListNode current = head;
        ListNode newTail = head;
        ListNode newHead = head;
        int length = 1;

        // Compute the length of the linked list
        for(int i = 1; current.next != null; i++) {
            current = current.next;
            length++;
        }


        for(int i = 0; i < length - k % length -1; i++) {
            newTail = newTail.next;
        }

        current.next = head;        // circle the linkedlist.
        newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode result = rotateRight(l1, 2);

        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
