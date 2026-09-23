package dp;

/**
 * File 04 — Q432: Longest Bitonic Subsequence
 * O(n²) DP — increasing + decreasing passes.
 */
class LongestBitonicSubsequence {

    static int longestBitonic(int[] nums) {
        int n = nums.length;
        int[] inc = new int[n];
        int[] dec = new int[n];

        for (int i = 0; i < n; i++) {
            inc[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    inc[i] = Math.max(inc[i], inc[j] + 1);
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            dec[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    dec[i] = Math.max(dec[i], dec[j] + 1);
                }
            }
        }

        int best = 0;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, inc[i] + dec[i] - 1);
        }
        return best;
    }

    void main() {
        IO.println(longestBitonic(new int[]{1, 11, 2, 10, 4, 5, 2, 1}));   // 6
    }
}
