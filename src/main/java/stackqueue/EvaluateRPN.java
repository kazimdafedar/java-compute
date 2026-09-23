package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q365: Evaluate Reverse Polish Notation
 * Stack evaluation — O(n) time.
 */
class EvaluateRPN {

    static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+", "-", "*", "/" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(switch (token) {
                        case "+" -> a + b;
                        case "-" -> a - b;
                        case "*" -> a * b;
                        default -> a / b;
                    });
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    void main() {
        IO.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));   // 9
        IO.println(evalRPN(new String[]{"4", "13", "5", "/", "+"})); // 6
    }
}
