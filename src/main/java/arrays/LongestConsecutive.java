package arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * File 04 — Q415: Longest Consecutive Subsequence
 * HashSet streak — O(n) time, O(n) space.
 */
class LongestConsecutive {

    static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        int best = 0;
        for (int x : set) {
            if (set.contains(x - 1)) {
                continue;
            }
            int len = 1;
            while (set.contains(x + len)) {
                len++;
            }
            best = Math.max(best, len);
        }
        return best;
    }

    void main() {
        IO.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));   // 4
    }
}
