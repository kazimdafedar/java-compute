package linkedlist;

/**
 * File 04 — Q396: Intersection of Two Linked Lists
 * Switch-at-end two pointers — O(m + n) time, O(1) space.
 */
class IntersectionOfTwoLinkedLists {

    static ListNode getIntersectionNode(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return null;
        }
        ListNode p = a;
        ListNode q = b;
        while (p != q) {
            p = p == null ? b : p.next;
            q = q == null ? a : q.next;
        }
        return p;
    }

    void main() {
        ListNode[] lists = ListUtils.intersectingLists(
            new int[]{4, 1},
            new int[]{5, 6, 1},
            new int[]{8, 4, 5}
        );
        ListNode intersection = getIntersectionNode(lists[0], lists[1]);
        IO.println(intersection != null ? intersection.val : "null");   // 8

        ListNode x = ListUtils.of(2, 6, 4);
        ListNode y = ListUtils.of(1, 5);
        IO.println(getIntersectionNode(x, y) == null ? "null" : "found");   // null
    }
}
