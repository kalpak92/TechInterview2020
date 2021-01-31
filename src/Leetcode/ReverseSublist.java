package Leetcode;

/**
 * @author kalpak
 *
 * Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 *
 */

public class ReverseSublist {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null)
            return head;

        // dummy node to track the previous node
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode current = head;
        ListNode prev = dummy;

        // Set the current pointer to the place from where it needs to be reversed
        for(int i = 1; i < m; i++) {
            prev = current;
            current = current.next;
        }

        // Reverse from position m to position n.
        // We need to store the current and prev nodes to connect next after reversing
        ListNode currentCopy = current;
        ListNode prevCopy = prev;
        ListNode next = null;

        for(int i = m; i <= n; i++) {
            next = currentCopy.next;
            currentCopy.next = prevCopy;
            prevCopy = currentCopy;
            currentCopy = next;
        }

        // Connect the remaining part to the reversed linked list.
        prev.next = prevCopy;
        current.next = currentCopy;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode result = reverseBetween(l1, 2, 5);

        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
