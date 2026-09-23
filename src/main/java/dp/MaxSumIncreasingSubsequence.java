package dp;

/**
 * File 04 — Q428: Maximum Sum Increasing Subsequence
 * O(n²) DP.
 */
class MaxSumIncreasingSubsequence {

    static int maxSumIS(int[] nums) {
        int[] dp = new int[nums.length];
        int best = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }

    void main() {
        IO.println(maxSumIS(new int[]{1, 101, 2, 3, 100, 4, 5}));   // 106
    }
}
