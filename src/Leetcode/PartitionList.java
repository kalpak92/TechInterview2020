package Leetcode;

/**
 * @author kalpak
 *
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example 1:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 *
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 *
 */


public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        ListNode smallerThanXHead = new ListNode(0);
        ListNode smallerThanXdummy = smallerThanXHead;

        ListNode greaterThanXHead = new ListNode(0);
        ListNode greaterThanXdummy = greaterThanXHead;

        while(head != null) {
            if(head.val < x) {
                smallerThanXdummy.next = head;
                smallerThanXdummy = smallerThanXdummy.next;
            } else {
                greaterThanXdummy.next = head;
                greaterThanXdummy = greaterThanXdummy.next;
            }
            head = head.next;
        }
        greaterThanXdummy.next = null;                      // Terminate the list
        smallerThanXdummy.next = greaterThanXHead.next;     // Connect the smaller list to the greater list

        return smallerThanXHead.next;
    }

    public static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        partition(head, 3);
        printList(head);
    }
}
