package strings;

/**
 * File 04 — Q87: Longest Palindromic Substring
 * Expand around center — O(n²) time, O(1) space.
 */
class LongestPalindromicSubstring {

    static String longestPalindrome(String s) {
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int odd = expand(s, i, i);
            int even = expand(s, i, i + 1);
            int len = Math.max(odd, even);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    void main() {
        IO.println(longestPalindrome("babad"));   // bab or aba
        IO.println(longestPalindrome("cbbd"));    // bb
    }
}
