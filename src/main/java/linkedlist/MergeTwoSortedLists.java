package linkedlist;

/**
 * File 04 — Q90: Merge Two Sorted Lists
 * O(n + m) time, O(1) extra space.
 */
class MergeTwoSortedLists {

    static ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;
        return dummy.next;
    }

    void main() {
        ListNode a = ListUtils.of(1, 2, 4);
        ListNode b = ListUtils.of(1, 3, 4);
        IO.println(ListUtils.toString(merge(a, b)));   // 1 -> 1 -> 2 -> 3 -> 4 -> 4
    }
}
