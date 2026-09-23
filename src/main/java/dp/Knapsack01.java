package dp;

/**
 * File 04 — Q107: 0/1 Knapsack
 * 1D rolling array, capacity descending — O(n * cap) time.
 */
class Knapsack01 {

    static int knapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int c = capacity; c >= weights[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - weights[i]] + values[i]);
            }
        }
        return dp[capacity];
    }

    void main() {
        IO.println(knapsack(
            new int[]{1, 2, 3},
            new int[]{6, 10, 12},
            5
        ));   // 22
    }
}
