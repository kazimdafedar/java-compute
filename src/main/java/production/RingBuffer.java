package production;

/**
 * File 04 — J05: Bounded Ring Buffer Queue
 * O(1) offer/poll — NOT thread-safe.
 */
final class RingBuffer<T> {

    private final Object[] buffer;
    private int head;
    private int tail;
    private int size;

    RingBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0");
        }
        this.buffer = new Object[capacity];
    }

    boolean offer(T item) {
        if (size == buffer.length) {
            return false;
        }
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    T poll() {
        if (size == 0) {
            return null;
        }
        T item = (T) buffer[head];
        buffer[head] = null;
        head = (head + 1) % buffer.length;
        size--;
        return item;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == buffer.length;
    }

    void main() {
        RingBuffer<Integer> buffer = new RingBuffer<>(3);
        IO.println(buffer.offer(1));
        IO.println(buffer.offer(2));
        IO.println(buffer.offer(3));
        IO.println(buffer.offer(4));   // false — full
        IO.println(buffer.poll());     // 1
    }
}
