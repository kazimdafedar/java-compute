package linkedlist;

/**
 * File 04 — Q394: Middle of Linked List
 * Slow / fast pointers — O(n) time, O(1) space.
 */
class MiddleOfLinkedList {

    static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    void main() {
        IO.println(middleNode(ListUtils.of(1, 2, 3, 4, 5)).val);   // 3
        IO.println(middleNode(ListUtils.of(1, 2, 3, 4)).val);     // 3 (second middle)
    }
}
