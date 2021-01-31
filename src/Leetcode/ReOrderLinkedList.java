package Leetcode;

/**
 * @author kalpak
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 *
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 */

public class ReOrderLinkedList {
    public static void reorderList(ListNode head) {
        if (head == null || head .next == null)
            return;

        // Let's find the middle of the linked list
        ListNode prev = null;
        ListNode head1 = head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null ) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Cut the linked lists
        prev.next = null;
        ListNode head2 = slow;

        // Reverse the second half of the linked list
        head2 = reverseList(head2);

        merge(head1, head2);
    }

    private static void merge(ListNode l1, ListNode l2) {
        while (l1 != null) {
            ListNode temp1 = l1.next;
            ListNode temp2 = l2.next;

            l1.next = l2;

            if (temp1 == null)      // For 1->2 and 5->4->3 scenario
                break;
            l2.next = temp1;

            l1 = temp1;
            l2 = temp2;
        }
    }

    private static ListNode reverseList(ListNode head) {
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

        reorderList(l1);

        while(l1 != null) {
            System.out.print(l1.val + " ");
            l1 = l1.next;
        }
    }
}
