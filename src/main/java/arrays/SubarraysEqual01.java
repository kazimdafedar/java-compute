package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q424: Subarrays with Equal Number of 0s and 1s
 * Treat 0 as -1, prefix balance — O(n) time, O(n) space.
 */
class SubarraysEqual01 {

    static int countEqual01(int[] nums) {
        Map<Integer, Integer> balanceCount = new HashMap<>();
        balanceCount.put(0, 1);
        int balance = 0;
        int count = 0;
        for (int x : nums) {
            balance += (x == 1 ? 1 : -1);
            count += balanceCount.getOrDefault(balance, 0);
            balanceCount.merge(balance, 1, Integer::sum);
        }
        return count;
    }

    static int longestEqual01(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        first.put(0, -1);
        int balance = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            balance += (nums[i] == 1 ? 1 : -1);
            Integer j = first.get(balance);
            if (j != null) {
                maxLen = Math.max(maxLen, i - j);
            } else {
                first.put(balance, i);
            }
        }
        return maxLen;
    }

    void main() {
        int[] nums = {0, 1, 0};
        IO.println("count:   " + countEqual01(nums));    // 3
        IO.println("longest: " + longestEqual01(nums));  // 2
    }
}
