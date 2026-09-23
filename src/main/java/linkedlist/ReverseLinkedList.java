package linkedlist;

/**
 * File 04 — Q88: Reverse Linked List
 * Iterative — O(n) time, O(1) space.
 */
class ReverseLinkedList {

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    void main() {
        ListNode head = ListUtils.of(1, 2, 3, 4, 5);
        IO.println("Before: " + ListUtils.toString(head));
        IO.println("After:  " + ListUtils.toString(reverse(head)));   // 5 -> 4 -> 3 -> 2 -> 1
    }
}
