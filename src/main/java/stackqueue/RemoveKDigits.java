package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q373: Remove K Digits
 * Monotonic increasing stack — O(n) time.
 */
class RemoveKDigits {

    static String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while (k-- > 0) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    void main() {
        IO.println(removeKdigits("1432219", 3));   // 1219
        IO.println(removeKdigits("10200", 1));     // 200
        IO.println(removeKdigits("10", 2));        // 0
    }
}
