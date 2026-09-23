package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * File 04 — Q93: Min Stack
 * Store {value, minSoFar} pairs — O(1) push/pop/top/getMin.
 */
class MinStack {

    private final Deque<long[]> stack = new ArrayDeque<>();

    void push(int value) {
        long minSoFar = stack.isEmpty() ? value : Math.min(value, stack.peek()[1]);
        stack.push(new long[]{value, minSoFar});
    }

    void pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        stack.pop();
    }

    int top() {
        return (int) stack.peek()[0];
    }

    int getMin() {
        return (int) stack.peek()[1];
    }

    void main() {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        IO.println(stack.getMin());   // -3
        stack.pop();
        IO.println(stack.top());      // 0
        IO.println(stack.getMin());   // -2
    }
}
