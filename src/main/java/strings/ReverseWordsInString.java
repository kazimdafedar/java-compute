package strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * File 04 — Q381: Reverse Words in a String
 * Trim + split + reverse + join — O(n) time.
 */
class ReverseWordsInString {

    static String reverseWords(String s) {
        String[] parts = s.trim().split("\\s+");
        List<String> list = Arrays.asList(parts);
        Collections.reverse(list);
        return String.join(" ", list);
    }

    void main() {
        IO.println(reverseWords("  the sky   is blue  "));   // blue is sky the
        IO.println(reverseWords("hello world"));             // world hello
    }
}
