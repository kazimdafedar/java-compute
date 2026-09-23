package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * File 04 — Q106: Longest Increasing Subsequence
 * Patience sorting + binary search — O(n log n) time.
 */
class LongestIncreasingSubsequence {

    static int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for (int value : nums) {
            int index = Collections.binarySearch(tails, value);
            if (index < 0) {
                index = -(index + 1);
            }
            if (index == tails.size()) {
                tails.add(value);
            } else {
                tails.set(index, value);
            }
        }
        return tails.size();
    }

    void main() {
        IO.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));   // 4
    }
}
