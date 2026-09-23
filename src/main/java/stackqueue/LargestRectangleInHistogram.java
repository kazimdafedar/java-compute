package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q369: Largest Rectangle in Histogram
 * Monotonic increasing stack — O(n) time.
 */
class LargestRectangleInHistogram {

    static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int best = 0;

        for (int i = 0; i <= heights.length; i++) {
            int current = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > current) {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;
                best = Math.max(best, height * width);
            }
            stack.push(i);
        }
        return best;
    }

    void main() {
        IO.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));   // 10
    }
}
