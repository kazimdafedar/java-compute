package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q355: Subarray Sum Equals K (+ longest subarray with sum K)
 * Prefix sum + hashmap — O(n) time, O(n) space.
 */
class SubarraySumEqualsK {

    static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);
        int sum = 0;
        int result = 0;
        for (int x : nums) {
            sum += x;
            result += count.getOrDefault(sum - k, 0);
            count.merge(sum, 1, Integer::sum);
        }
        return result;
    }

    static int longestSubarraySumK(int[] nums, int k) {
        Map<Integer, Integer> first = new HashMap<>();
        first.put(0, -1);
        int prefix = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            Integer j = first.get(prefix - k);
            if (j != null) {
                maxLen = Math.max(maxLen, i - j);
            }
            first.putIfAbsent(prefix, i);
        }
        return maxLen;
    }

    void main() {
        IO.println(subarraySum(new int[]{1, 1, 1}, 2));              // 2
        IO.println(longestSubarraySumK(new int[]{1, 2, 3, -2, 2}, 3)); // 3
    }
}
