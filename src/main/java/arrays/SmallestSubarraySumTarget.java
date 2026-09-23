package arrays;

/**
 * File 04 — Q429: Smallest Subarray with Sum > Target
 * Variable sliding window — O(n) time, O(1) space.
 */
class SmallestSubarraySumTarget {

    static int minSubarrayLen(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum > target) {
                best = Math.min(best, end - start + 1);
                sum -= nums[start++];
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }

    void main() {
        IO.println(minSubarrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));   // 2
    }
}
