package strings;

/**
 * File 04 — Q377: Valid Anagram
 * O(n) time, O(1) space (26-letter alphabet).
 */
class ValidAnagram {

    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    void main() {
        IO.println(isAnagram("anagram", "nagaram"));   // true
        IO.println(isAnagram("rat", "car"));           // false
    }
}
