package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Palindrome Partitioning — split so every part is a palindrome.
 * Backtracking + expand-check. O(n · 2ⁿ) time.
 */
class PalindromePartitioning {

    static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    static void backtrack(String s, int start, List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (!isPalindrome(s, start, end)) {
                continue;
            }
            current.add(s.substring(start, end + 1));
            backtrack(s, end + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    static boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo++) != s.charAt(hi--)) {
                return false;
            }
        }
        return true;
    }

    void main() {
        IO.println(partition("aab"));   // [[a, a, b], [aa, b]]
        IO.println(partition("a"));     // [[a]]
    }
}
