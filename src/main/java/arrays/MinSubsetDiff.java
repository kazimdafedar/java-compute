package arrays;

/**
 * File 04 — Q421: Minimize Max-Min Difference of Two Subset Sums
 * 0/1 knapsack DP — O(n * sum) time.
 */
class MinSubsetDiff {

    static int minSubsetDiff(int[] nums) {
        int total = 0;
        for (int x : nums) {
            total += x;
        }
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int x : nums) {
            for (int sum = target; sum >= x; sum--) {
                dp[sum] |= dp[sum - x];
            }
        }
        for (int sum = target; sum >= 0; sum--) {
            if (dp[sum]) {
                return total - 2 * sum;
            }
        }
        return 0;
    }

    void main() {
        IO.println(minSubsetDiff(new int[]{1, 6, 11, 5}));   // 1
    }
}
