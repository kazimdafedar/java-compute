package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q437: Maximum Distance Between Two Occurrences
 * Track first index per value — O(n) time, O(n) space.
 */
class MaxDistanceSameElement {

    static int maxDistance(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        int best = 0;
        for (int i = 0; i < nums.length; i++) {
            first.putIfAbsent(nums[i], i);
            best = Math.max(best, i - first.get(nums[i]));
        }
        return best;
    }

    void main() {
        IO.println(maxDistance(new int[]{1, 2, 2, 3, 1, 4, 2}));   // 5
    }
}
