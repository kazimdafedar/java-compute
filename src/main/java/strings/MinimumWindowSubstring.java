package strings;

/**
 * File 04 — Q379: Minimum Window Substring
 * Sliding window + frequency — O(n) time, O(σ) space.
 */
class MinimumWindowSubstring {

    static String minWindow(String s, String t) {
        if (t.isEmpty()) {
            return "";
        }

        int[] need = new int[128];
        int[] have = new int[128];
        int required = 0;

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (need[c]++ == 0) {
                required++;
            }
        }

        int formed = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestStart = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            have[c]++;
            if (need[c] > 0 && have[c] == need[c]) {
                formed++;
            }

            while (formed == required) {
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestStart = left;
                }
                char lc = s.charAt(left++);
                if (need[lc] > 0 && have[lc]-- == need[lc]) {
                    formed--;
                }
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    void main() {
        IO.println(minWindow("ADOBECODEBANC", "ABC"));   // BANC
        IO.println(minWindow("a", "a"));                 // a
        IO.println(minWindow("a", "aa"));                // ""
    }
}
