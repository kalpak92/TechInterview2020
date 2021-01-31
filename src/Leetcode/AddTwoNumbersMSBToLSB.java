package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *
 */

public class AddTwoNumbersMSBToLSB {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = head;
        int carry = 0;

        Deque<ListNode> s1 = new ArrayDeque<>();
        Deque<ListNode> s2 = new ArrayDeque<>();

        while(l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }

        while(l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }

        while(!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int sum = carry;
            int digit1 = 0;
            int digit2 = 0;

            if(!s1.isEmpty())
                digit1 = s1.poll().val;

            if(!s2.isEmpty())
                digit2 = s2.poll().val;

            sum += digit1 + digit2;

            carry = sum/10;
            sum = sum%10;

            ListNode res = new ListNode(sum);   // Add it in reverse order
            res.next = current;
            current = res;
        }
        return current;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);

        while(result != null) {
            System.out.print(result.val + "");
            result = result.next;
        }
        System.out.println();
    }
}
