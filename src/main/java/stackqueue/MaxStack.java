package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q398: Max Stack
 * Same pattern as MinStack — O(1) push/pop/top/getMax.
 */
class MaxStack {

    private final Deque<long[]> stack = new ArrayDeque<>();

    void push(int value) {
        long maxSoFar = stack.isEmpty() ? value : Math.max(value, stack.peek()[1]);
        stack.push(new long[]{value, maxSoFar});
    }

    void pop() {
        stack.pop();
    }

    int top() {
        return (int) stack.peek()[0];
    }

    int getMax() {
        return (int) stack.peek()[1];
    }

    void main() {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        IO.println(stack.getMax());   // 5
        stack.pop();
        IO.println(stack.getMax());   // 5
    }
}
