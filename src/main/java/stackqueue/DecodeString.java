package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q370: Decode String
 * Stack of (repeatCount, builtString) — O(n * maxK) time.
 */
class DecodeString {

    static String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> strings = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int repeat = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                repeat = repeat * 10 + (c - '0');
            } else if (c == '[') {
                counts.push(repeat);
                strings.push(current);
                current = new StringBuilder();
                repeat = 0;
            } else if (c == ']') {
                StringBuilder previous = strings.pop();
                int k = counts.pop();
                for (int i = 0; i < k; i++) {
                    previous.append(current);
                }
                current = previous;
            } else {
                current.append(c);
            }
        }
        return current.toString();
    }

    void main() {
        IO.println(decodeString("3[a2[c]]"));   // accaccacc
        IO.println(decodeString("2[abc]3[cd]ef"));   // abcabccdcdcdef
    }
}
