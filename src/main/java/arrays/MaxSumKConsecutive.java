package arrays;

/**
 * File 04 — Q417: Max Sum of k Consecutive Elements
 * Fixed sliding window — O(n) time, O(1) space.
 */
class MaxSumKConsecutive {

    static int maxSumK(int[] nums, int k) {
        int window = 0;
        for (int i = 0; i < k; i++) {
            window += nums[i];
        }
        int best = window;
        for (int i = k; i < nums.length; i++) {
            window += nums[i] - nums[i - k];
            best = Math.max(best, window);
        }
        return best;
    }

    void main() {
        IO.println(maxSumK(new int[]{2, 1, 5, 1, 3, 2}, 3));   // 9
    }
}
