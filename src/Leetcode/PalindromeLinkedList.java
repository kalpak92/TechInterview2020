package Leetcode;

/**
 * @author kalpak
 *
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *
 */

public class PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }


        if (fast != null) { // odd nodes.
            slow = slow.next;
        }

        slow = reverseList(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val)
                return false;

            fast = fast.next;
            slow = slow.next;
        }

        return true;
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
        l1.next.next.next = new ListNode(2);
        l1.next.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(l1));
    }
}
