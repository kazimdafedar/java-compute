package linkedlist;

import java.util.NoSuchElementException;

/**
 * File 04 — Q395: Stack Using Linked List
 * Push/pop at head — O(1) per operation.
 */
class LinkedListStack {

    private ListNode top;

    void push(int value) {
        ListNode node = new ListNode(value);
        node.next = top;
        top = node;
    }

    int pop() {
        if (top == null) {
            throw new NoSuchElementException("stack empty");
        }
        int value = top.val;
        top = top.next;
        return value;
    }

    int peek() {
        if (top == null) {
            throw new NoSuchElementException("stack empty");
        }
        return top.val;
    }

    boolean isEmpty() {
        return top == null;
    }

    void main() {
        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        IO.println(stack.peek());   // 2
        IO.println(stack.pop());    // 2
        IO.println(stack.peek());   // 1
        IO.println(stack.isEmpty()); // false
    }
}
