package arrays;

/**
 * File 04 — Q353: Best Time to Buy and Sell Stock (single transaction)
 * O(n) time, O(1) space.
 */
class BestTimeToBuySellStock {

    static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int best = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            best = Math.max(best, price - minPrice);
        }
        return best;
    }

    void main() {
        IO.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));   // 5
    }
}
