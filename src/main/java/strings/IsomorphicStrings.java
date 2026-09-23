package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q378: Isomorphic Strings
 * Bidirectional char map — O(n) time, O(σ) space.
 */
class IsomorphicStrings {

    static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (sToT.containsKey(a) && sToT.get(a) != b) {
                return false;
            }
            if (tToS.containsKey(b) && tToS.get(b) != a) {
                return false;
            }
            sToT.put(a, b);
            tToS.put(b, a);
        }
        return true;
    }

    void main() {
        IO.println(isIsomorphic("egg", "add"));     // true
        IO.println(isIsomorphic("foo", "bar"));     // false
        IO.println(isIsomorphic("paper", "title")); // true
    }
}
