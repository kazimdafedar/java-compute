package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Copy List with Random Pointer — HashMap old → new, then wire next/random.
 * O(n) time, O(n) space.
 */
class CopyListWithRandomPointer {

    static class RandomNode {
        int val;
        RandomNode next;
        RandomNode random;

        RandomNode(int val) {
            this.val = val;
        }
    }

    static RandomNode copy(RandomNode head) {
        Map<RandomNode, RandomNode> copies = new HashMap<>();
        RandomNode cur = head;
        while (cur != null) {
            copies.put(cur, new RandomNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            RandomNode clone = copies.get(cur);
            clone.next = copies.get(cur.next);
            clone.random = copies.get(cur.random);
            cur = cur.next;
        }
        return copies.get(head);
    }

    void main() {
        RandomNode a = new RandomNode(7);
        RandomNode b = new RandomNode(13);
        RandomNode c = new RandomNode(11);
        a.next = b;
        b.next = c;
        b.random = a;
        c.random = b;

        RandomNode copy = copy(a);
        IO.println(copy.val + " -> " + copy.next.val + " -> " + copy.next.next.val);
        IO.println("13.random = " + copy.next.random.val);   // 7
        IO.println("same object? " + (copy == a));           // false
    }
}
