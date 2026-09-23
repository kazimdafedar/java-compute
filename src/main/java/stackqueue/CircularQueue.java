package stackqueue;

/**
 * File 04 — Q371: Design Circular Queue
 * Ring buffer — O(1) per operation.
 */
class CircularQueue {

    private final int[] buffer;
    private int head;
    private int tail;
    private int size;

    CircularQueue(int capacity) {
        buffer = new int[capacity];
    }

    boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        buffer[tail] = value;
        tail = (tail + 1) % buffer.length;
        size++;
        return true;
    }

    boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % buffer.length;
        size--;
        return true;
    }

    int front() {
        return isEmpty() ? -1 : buffer[head];
    }

    int rear() {
        return isEmpty() ? -1 : buffer[(tail - 1 + buffer.length) % buffer.length];
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == buffer.length;
    }

    void main() {
        CircularQueue queue = new CircularQueue(3);
        IO.println(queue.enQueue(1));   // true
        IO.println(queue.enQueue(2));   // true
        IO.println(queue.enQueue(3));   // true
        IO.println(queue.enQueue(4));   // false
        IO.println(queue.rear());       // 3
        IO.println(queue.isFull());     // true
        IO.println(queue.deQueue());    // true
        IO.println(queue.enQueue(4));   // true
        IO.println(queue.rear());       // 4
    }
}
