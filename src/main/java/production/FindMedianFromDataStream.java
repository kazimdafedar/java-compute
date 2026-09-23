package production;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find Median from Data Stream — two heaps.
 * Max-heap of lower half + min-heap of upper half. O(log n) add, O(1) median.
 */
final class MedianFinder {

    private final PriorityQueue<Integer> low = new PriorityQueue<>(Comparator.reverseOrder());
    private final PriorityQueue<Integer> high = new PriorityQueue<>();

    void addNum(int num) {
        if (low.isEmpty() || num <= low.peek()) {
            low.offer(num);
        } else {
            high.offer(num);
        }
        if (low.size() > high.size() + 1) {
            high.offer(low.poll());
        } else if (high.size() > low.size()) {
            low.offer(high.poll());
        }
    }

    double findMedian() {
        if (low.size() > high.size()) {
            return low.peek();
        }
        return (low.peek() + high.peek()) / 2.0;
    }

    void main() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        IO.println(finder.findMedian());   // 1.5
        finder.addNum(3);
        IO.println(finder.findMedian());   // 2.0
    }
}
