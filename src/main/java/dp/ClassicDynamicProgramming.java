package dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * File 04 — Q109: House Robber / Word Break / Unique Paths
 */
class ClassicDynamicProgramming {

    static int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;
        for (int value : nums) {
            int take = prev2 + value;
            prev2 = prev1;
            prev1 = Math.max(prev1, take);
        }
        return prev1;
    }

    static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    static int uniquePaths(int rows, int cols) {
        int[] dp = new int[cols];
        for (int i = 0; i < cols; i++) {
            dp[i] = 1;
        }
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                dp[c] += dp[c - 1];
            }
        }
        return dp[cols - 1];
    }

    void main() {
        IO.println(rob(new int[]{2, 7, 9, 3, 1}));   // 18
        IO.println(wordBreak("leetcode", List.of("leet", "code")));   // true
        IO.println(uniquePaths(3, 7));   // 28
    }
}
