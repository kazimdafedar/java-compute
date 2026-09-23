package strings;

/**
 * File 04 — Q380: Longest Repeating Character Replacement
 * Sliding window — O(n) time, O(1) space.
 */
class LongestRepeatingCharacterReplacement {

    static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(right) - 'A']);
            while (right - left + 1 - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    void main() {
        IO.println(characterReplacement("AABABBA", 1));   // 4
        IO.println(characterReplacement("ABAB", 2));     // 4
    }
}
