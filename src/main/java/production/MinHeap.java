package production;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * File 04 — J13: Min-Heap with heapify
 */
final class MinHeap {

    private final List<Integer> heap = new ArrayList<>();

    void offer(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    int poll() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    int peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap.get(0);
    }

    static MinHeap heapify(int[] arr) {
        MinHeap minHeap = new MinHeap();
        minHeap.heap.addAll(Arrays.stream(arr).boxed().toList());
        for (int i = minHeap.heap.size() / 2 - 1; i >= 0; i--) {
            minHeap.heapifyDown(i);
        }
        return minHeap;
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) >= heap.get(parent)) {
                break;
            }
            swap(i, parent);
            i = parent;
        }
    }

    private void heapifyDown(int i) {
        int n = heap.size();
        while (true) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < n && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest == i) {
                break;
            }
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    void main() {
        MinHeap heap = MinHeap.heapify(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        IO.println(heap.poll());   // 1
        IO.println(heap.poll());   // 2
    }
}
