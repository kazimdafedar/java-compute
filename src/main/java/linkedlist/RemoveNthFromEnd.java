package linkedlist;

/**
 * File 04 — Q91: Remove Nth Node From End
 * Two pointers with gap n — O(n) time, O(1) space.
 */
class RemoveNthFromEnd {

    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    void main() {
        ListNode head = ListUtils.of(1, 2, 3, 4, 5);
        IO.println(ListUtils.toString(removeNthFromEnd(head, 2)));   // 1 -> 2 -> 3 -> 5
    }
}
