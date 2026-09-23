package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q94: Sliding Window Maximum
 * Monotonic deque — O(n) time.
 */
class SlidingWindowMaximum {

    static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    void main() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        ArrayUtils.print(maxSlidingWindow(nums, 3));   // [3, 3, 5, 5, 6, 7]
    }
}
