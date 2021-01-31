package Leetcode;

/**
 * @author kalpak
 *
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 */


public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        if(head == null)
            return head;

        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;

        while(current != null) {
            // Store the next pointer and then change the next pointer to previous
            next = current.next;
            current.next = prev;

            // prev now moves to the current node
            prev = current;

            // current goes to the next node which has been cached
            current = next;
        }

        // In the end, now current is null, but prev has the last node.
        // Move head to prev.
        head = prev;
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode result = reverseList(l1);

        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
