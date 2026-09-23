package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * File 04 — Q390: All Palindrome Substrings
 * Expand around center — O(n²) time, O(n²) output space.
 */
class AllPalindromeSubstrings {

    static List<String> allPalindromes(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            expand(s, i, i, result);
            expand(s, i, i + 1, result);
        }
        return result;
    }

    static void expand(String s, int left, int right, List<String> result) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result.add(s.substring(left, right + 1));
            left--;
            right++;
        }
    }

    void main() {
        IO.println(allPalindromes("abc"));   // [a, b, c]
        IO.println(allPalindromes("aaa"));   // [a, a, a, aa, aa, aaa]
    }
}
