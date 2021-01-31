package Leetcode;

/**
 * @author kalpak
 *
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
 *
 *
 * Example 1:
 *
 *
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 *
 *
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 *
 *
 *
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * Example 4:
 *
 * Input: head = []
 * Output: []
 * Explanation: Given linked list is empty (null pointer), so return null.
 *
 *
 * Constraints:
 *
 * -10000 <= Node.val <= 10000
 * Node.random is null or pointing to a node in the linked list.
 * The number of nodes will not exceed 1000.
 *
 */

class NodeWithRandomPointer {
    int val;
    NodeWithRandomPointer next;
    NodeWithRandomPointer random;

    public NodeWithRandomPointer(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointer {
    public static NodeWithRandomPointer copyRandomList(NodeWithRandomPointer head) {
        if(head == null)
            return head;

        NodeWithRandomPointer current = head;

        // copy the nodes
        while(current != null) {
            NodeWithRandomPointer newNode = new NodeWithRandomPointer(current.val);
            newNode.next = current.next;
            current.next = newNode;

            current = current.next.next;
        }

        // copy the random pointers to the new nodes
        current = head;

        while(current != null) {
            if(current.random != null)
                current.next.random = current.random.next;
            else
                current.next.random = null;
            current = current.next.next;
        }

        // Split the lists
        NodeWithRandomPointer l1 = head;
        NodeWithRandomPointer l2 = head.next;
        NodeWithRandomPointer clonedHead = head.next;

        while(l1 != null) {
            l1.next = l1.next.next;

            if(l2.next != null)
                l2.next = l2.next.next;
            else
                l2.next = null;
            l1 = l1.next;
            l2 = l2.next;
        }

        return clonedHead;
    }

    public static void main(String[] args) {
        NodeWithRandomPointer head = new NodeWithRandomPointer(7);
        head.next = new NodeWithRandomPointer(13);
        head.next.next = new NodeWithRandomPointer(11);
        head.next.next.next = new NodeWithRandomPointer(10);
        head.next.next.next.next = new NodeWithRandomPointer(1);

        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        NodeWithRandomPointer result = copyRandomList(head);

        while(result != null) {
            System.out.print(result.val + " ");
            if(result.random != null)
                System.out.print("Random: "+result.random.val);
            System.out.println();
            result = result.next;
        }
    }
}
