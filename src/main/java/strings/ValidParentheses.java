package strings;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q86: Valid Parentheses
 * Stack — O(n) time, O(n) space.
 */
class ValidParentheses {

    static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if (c == ')' && open != '(') return false;
                if (c == ']' && open != '[') return false;
                if (c == '}' && open != '{') return false;
            }
        }
        return stack.isEmpty();
    }

    void main() {
        IO.println(isValid("()[]{}"));    // true
        IO.println(isValid("(]"));        // false
        IO.println(isValid("{[]}"));      // true
    }
}
