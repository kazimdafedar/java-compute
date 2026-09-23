package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q425: Distinct Elements in Every Window of Size k
 * Sliding window + freq map — O(n) time, O(k) space.
 */
class DistinctInWindow {

    static int[] distinctInWindow(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            freq.merge(nums[i], 1, Integer::sum);
            if (i >= k) {
                int outgoing = nums[i - k];
                freq.merge(outgoing, -1, Integer::sum);
                if (freq.get(outgoing) == 0) {
                    freq.remove(outgoing);
                }
            }
            if (i >= k - 1) {
                result[i - k + 1] = freq.size();
            }
        }
        return result;
    }

    void main() {
        IO.println(Arrays.toString(distinctInWindow(new int[]{1, 2, 1, 3, 4, 2, 3}, 4)));
        // [3, 4, 4, 3]
    }
}
