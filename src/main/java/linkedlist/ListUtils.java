package linkedlist;

/**
 * Helpers to build and print lists for practice mains.
 */
class ListUtils {

    static ListNode of(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : values) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    static String toString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        return sb.toString();
    }

    /** Create intersection: a = [aHead...] + shared, b = [bHead...] + shared. */
    static ListNode[] intersectingLists(int[] aHead, int[] bHead, int[] shared) {
        ListNode sharedTail = of(shared);
        ListNode a = of(aHead);
        ListNode b = of(bHead);
        ListNode aTail = a;
        while (aTail.next != null) {
            aTail = aTail.next;
        }
        ListNode bTail = b;
        while (bTail.next != null) {
            bTail = bTail.next;
        }
        aTail.next = sharedTail;
        bTail.next = sharedTail;
        return new ListNode[]{a, b};
    }
}
