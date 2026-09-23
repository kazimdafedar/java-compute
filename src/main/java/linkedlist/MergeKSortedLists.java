package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge K Sorted Lists — min-heap of list heads.
 * O(N log k) time, O(k) space. Follow-up to Q90.
 */
class MergeKSortedLists {

    static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode head : lists) {
            if (head != null) {
                heap.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return dummy.next;
    }

    void main() {
        ListNode[] lists = {
                ListUtils.of(1, 4, 5),
                ListUtils.of(1, 3, 4),
                ListUtils.of(2, 6)
        };
        IO.println(ListUtils.toString(mergeKLists(lists)));   // 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    }
}
