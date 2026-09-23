package linkedlist;

/**
 * File 04 — Q89: Detect Cycle (Floyd)
 * O(n) time, O(1) space.
 */
class DetectCycle {

    static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /** Follow-up: return node where cycle begins, or null. */
    static ListNode detectCycleStart(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    void main() {
        ListNode noCycle = ListUtils.of(1, 2, 3);
        IO.println("No cycle: " + hasCycle(noCycle));   // false

        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = b;   // cycle back to 2
        IO.println("Has cycle: " + hasCycle(a));        // true
        IO.println("Cycle starts at: " + detectCycleStart(a).val);   // 2
    }
}
