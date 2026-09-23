package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q423: Count Subarrays with Given XOR
 * Prefix XOR + hashmap — O(n) time, O(n) space.
 */
class CountSubarraysXor {

    static int countSubarraysXor(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        int prefix = 0;
        int count = 0;
        for (int x : nums) {
            prefix ^= x;
            count += freq.getOrDefault(prefix ^ k, 0);
            freq.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    void main() {
        IO.println(countSubarraysXor(new int[]{4, 2, 2, 6, 4}, 6));   // 4
    }
}
