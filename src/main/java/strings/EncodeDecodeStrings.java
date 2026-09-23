package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * File 04 — Q387: Encode and Decode Strings
 * Length-prefix framing — O(n) time and space.
 */
class EncodeDecodeStrings {

    static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    static List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = s.indexOf('#', i);
            int len = Integer.parseInt(s.substring(i, j));
            result.add(s.substring(j + 1, j + 1 + len));
            i = j + 1 + len;
        }
        return result;
    }

    void main() {
        List<String> input = List.of("hello", "world", "a#b");
        String encoded = encode(input);
        IO.println("Encoded: " + encoded);
        IO.println("Decoded: " + decode(encoded));
    }
}
