package stackqueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * File 04 — Q397: Stack Using Array
 * Resizable array — amortized O(1) push.
 */
class ArrayStack {

    private int[] data = new int[8];
    private int size;

    void push(int value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = value;
    }

    int pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[--size];
    }

    int peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data[size - 1];
    }

    boolean isEmpty() {
        return size == 0;
    }

    void main() {
        ArrayStack stack = new ArrayStack();
        stack.push(10);
        stack.push(20);
        IO.println(stack.peek());     // 20
        IO.println(stack.pop());      // 20
        IO.println(stack.isEmpty());  // false
    }
}
