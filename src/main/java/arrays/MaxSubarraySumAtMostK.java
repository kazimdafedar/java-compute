package arrays;

/**
 * File 04 — Q430: Max Subarray Sum with Length at Most k
 * Enumerate windows up to k — O(n * k) time.
 */
class MaxSubarraySumAtMostK {

    static int maxSumAtMostK(int[] nums, int k) {
        int best = Integer.MIN_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length && end - start < k; end++) {
                sum += nums[end];
                best = Math.max(best, sum);
            }
        }
        return best;
    }

    void main() {
        IO.println(maxSumAtMostK(new int[]{2, -1, 2, 3}, 2));   // 5
    }
}
