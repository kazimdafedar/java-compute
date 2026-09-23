package strings;

/**
 * File 04 — Q386: Is Subsequence
 * Two pointers — O(n) time, O(1) space.
 */
class IsSubsequence {

    static boolean isSubsequence(String s, String t) {
        int i = 0;
        for (int j = 0; i < s.length() && j < t.length(); j++) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
        }
        return i == s.length();
    }

    void main() {
        IO.println(isSubsequence("abc", "ahbgdc"));   // true
        IO.println(isSubsequence("axc", "ahbgdc"));   // false
    }
}
