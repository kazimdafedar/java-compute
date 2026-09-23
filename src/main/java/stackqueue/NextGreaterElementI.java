package stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q366: Next Greater Element I
 * Monotonic decreasing stack on nums2 — O(n + m).
 */
class NextGreaterElementI {

    static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nge = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int value : nums2) {
            while (!stack.isEmpty() && stack.peek() < value) {
                nge.put(stack.pop(), value);
            }
            stack.push(value);
        }
        while (!stack.isEmpty()) {
            nge.putIfAbsent(stack.pop(), -1);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nge.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    void main() {
        ArrayUtils.print(nextGreaterElement(
            new int[]{4, 1, 2},
            new int[]{1, 3, 4, 2}
        ));   // [-1, 3, -1]
    }
}
