package dp;

import java.util.Arrays;

/**
 * File 04 — Q105: Coin Change
 * Unbounded knapsack — O(amount * coins) time.
 */
class CoinChange {

    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    void main() {
        IO.println(coinChange(new int[]{1, 2, 5}, 11));   // 3
        IO.println(coinChange(new int[]{2}, 3));          // -1
    }
}
