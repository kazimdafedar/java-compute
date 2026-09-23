package dp;

/**
 * Decode Ways — 'A'–'Z' map to 1–26.
 * O(n) time, O(1) space rolling DP.
 */
class DecodeWays {

    static int numDecodings(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        int prev2 = 1;
        int prev1 = 1;
        for (int i = 1; i < s.length(); i++) {
            int cur = 0;
            if (s.charAt(i) != '0') {
                cur += prev1;
            }
            int two = Integer.parseInt(s.substring(i - 1, i + 1));
            if (two >= 10 && two <= 26) {
                cur += prev2;
            }
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    void main() {
        IO.println(numDecodings("12"));    // 2  ("AB", "L")
        IO.println(numDecodings("226"));   // 3
        IO.println(numDecodings("06"));    // 0
    }
}
