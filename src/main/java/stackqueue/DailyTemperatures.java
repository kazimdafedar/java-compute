package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q368: Daily Temperatures
 * Monotonic decreasing index stack — O(n) time.
 */
class DailyTemperatures {

    static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prev = stack.pop();
                result[prev] = i - prev;
            }
            stack.push(i);
        }
        return result;
    }

    void main() {
        ArrayUtils.print(dailyTemperatures(
            new int[]{73, 74, 75, 71, 69, 72, 76, 73}
        ));   // [1, 1, 4, 2, 1, 1, 0, 0]
    }
}
