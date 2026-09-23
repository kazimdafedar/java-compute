package linkedlist;

/**
 * Add Two Numbers — digits stored in reverse order.
 * O(max(m, n)) time, O(1) extra space besides the result list.
 */
class AddTwoNumbers {

    static ListNode add(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummy.next;
    }

    void main() {
        ListNode a = ListUtils.of(2, 4, 3);   // 342
        ListNode b = ListUtils.of(5, 6, 4);   // 465
        IO.println(ListUtils.toString(add(a, b)));   // 7 -> 0 -> 8  (807)
    }
}
