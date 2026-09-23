package stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * File 04 — Q367: Next Greater Element II (Circular Array)
 * Monotonic stack, 2n pass — O(n) time.
 */
class NextGreaterElementII {

    static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                result[stack.pop()] = nums[idx];
            }
            if (i < n) {
                stack.push(idx);
            }
        }
        return result;
    }

    void main() {
        ArrayUtils.print(nextGreaterElements(new int[]{1, 2, 1}));   // [2, -1, 2]
    }
}
