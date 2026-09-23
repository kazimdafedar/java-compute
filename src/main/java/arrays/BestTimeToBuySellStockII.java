package arrays;

/**
 * File 04 — Q431: Best Time to Buy and Sell Stock II (Multiple Transactions)
 * Greedy — capture every uphill — O(n) time, O(1) space.
 */
class BestTimeToBuySellStockII {

    static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    void main() {
        IO.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));   // 7
    }
}
