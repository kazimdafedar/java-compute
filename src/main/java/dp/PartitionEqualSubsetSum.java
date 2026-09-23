package dp;

/**
 * File 04 — Q433: Partition Equal Subset Sum
 * 0/1 knapsack boolean — O(n * sum) time.
 */
class PartitionEqualSubsetSum {

    static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int value : nums) {
            sum += value;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int value : nums) {
            for (int s = target; s >= value; s--) {
                dp[s] |= dp[s - value];
            }
        }
        return dp[target];
    }

    void main() {
        IO.println(canPartition(new int[]{1, 5, 11, 5}));   // true
        IO.println(canPartition(new int[]{1, 2, 3, 5}));    // false
    }
}
