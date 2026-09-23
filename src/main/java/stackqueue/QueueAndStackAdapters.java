package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * File 04 — Q95: Queue Using Two Stacks / Stack Using Two Queues
 */
class QueueAndStackAdapters {

    /** Queue from two stacks — amortized O(1) per operation. */
    static class MyQueue {
        private final Deque<Integer> in = new ArrayDeque<>();
        private final Deque<Integer> out = new ArrayDeque<>();

        void push(int value) {
            in.push(value);
        }

        int peek() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.peek();
        }

        int pop() {
            peek();
            return out.pop();
        }

        boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    /** Stack from one queue — push O(n), pop O(1). */
    static class MyStack {
        private final Queue<Integer> queue = new LinkedList<>();

        void push(int value) {
            queue.add(value);
            for (int i = queue.size(); i > 1; i--) {
                queue.add(queue.remove());
            }
        }

        int pop() {
            return queue.remove();
        }

        int top() {
            return queue.peek();
        }

        boolean empty() {
            return queue.isEmpty();
        }
    }

    void main() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        IO.println(queue.peek());   // 1
        IO.println(queue.pop());    // 1
        IO.println(queue.empty());  // false

        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        IO.println(stack.top());    // 2
        IO.println(stack.pop());    // 2
    }
}
