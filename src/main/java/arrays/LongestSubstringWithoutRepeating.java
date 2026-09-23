package arrays;

/**
 * File 04 — Q78: Longest Substring Without Repeating Characters
 * Sliding window — O(n) time, O(k) space (k = charset size).
 * No Arrays.* — manual init of last-seen index table.
 */
class LongestSubstringWithoutRepeating {

    static int lengthOfLongest(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }

        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last[c] >= start) {
                start = last[c] + 1;   // shrink window past previous duplicate
            }
            last[c] = i;
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }

    /** Follow-up: return the actual longest substring (first occurrence if tie). */
    static String longestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }

        int start = 0;
        int maxLen = 0;
        int bestStart = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last[c] >= start) {
                start = last[c] + 1;
            }
            last[c] = i;
            int len = i - start + 1;
            if (len > maxLen) {
                maxLen = len;
                bestStart = start;
            }
        }
        return s.substring(bestStart, bestStart + maxLen);
    }

    void main() {
        String s = "abcabcbb";
        IO.println("Length: " + lengthOfLongest(s));           // 3
        IO.println("Substring: \"" + longestSubstring(s) + "\"");  // "abc"

        IO.println("Length: " + lengthOfLongest("bbbbb"));     // 1
        IO.println("Length: " + lengthOfLongest("pwwkew"));   // 3 → "wke"
        IO.println("Substring: \"" + longestSubstring("pwwkew") + "\"");
    }
}
