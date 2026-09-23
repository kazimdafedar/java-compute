package strings;

/**
 * File 04 — Q385: Longest Common Prefix
 * Vertical scan — O(n * L) time, O(1) extra space.
 */
class LongestCommonPrefix {

    static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    void main() {
        IO.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));   // fl
        IO.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));      // ""
    }
}
