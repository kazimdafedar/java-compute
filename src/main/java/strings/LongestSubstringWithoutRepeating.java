package strings;

/**
 * File 04 — Q78 (strings): Longest Substring Without Repeating Characters
 * Sliding window — O(n) time, O(k) space.
 */
class LongestSubstringWithoutRepeating {

    static int lengthOfLongest(String s) {
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last[c] >= start) {
                start = last[c] + 1;
            }
            last[c] = i;
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }

    void main() {
        IO.println(lengthOfLongest("abcabcbb"));   // 3
        IO.println(lengthOfLongest("bbbbb"));      // 1
        IO.println(lengthOfLongest("pwwkew"));     // 3
    }
}
