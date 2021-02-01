package Leetcode;

/**
 * @author kalpak
 *
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 *
 *
 * Constraints:
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * The length of the linked list is between [0, 10^4].
 */

public class OddEvenLinkedList {
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode oddCurrent = head;
        ListNode evenCurrent = head.next;

        while(evenCurrent != null && evenCurrent.next != null) {
            oddCurrent.next = evenCurrent.next;
            oddCurrent = oddCurrent.next;

            evenCurrent.next = oddCurrent.next;
            evenCurrent = evenCurrent.next;
        }

        oddCurrent.next = evenHead;
        return oddHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next = new ListNode(6);

        ListNode result = oddEvenList(l1);
        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
    }
}
