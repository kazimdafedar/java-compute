package strings;

/**
 * File 04 — Q392: First Non-Repeating Character
 * Frequency count + second pass — O(n) time, O(1) space.
 */
class FirstNonRepeatingCharacter {

    static char firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }
        return '\0';
    }

    void main() {
        IO.println(firstUniqChar("leetcode"));   // l
        IO.println(firstUniqChar("aabb") == '\0' ? "none" : "found");
    }
}
