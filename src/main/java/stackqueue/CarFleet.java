package stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * File 04 — Q376: Car Fleet
 * Sort by position + monotonic arrival times — O(n log n).
 */
class CarFleet {

    static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> position[b] - position[a]);

        Deque<Double> arrivalTimes = new ArrayDeque<>();
        for (int i : indices) {
            double time = (double) (target - position[i]) / speed[i];
            if (arrivalTimes.isEmpty() || time > arrivalTimes.peek()) {
                arrivalTimes.push(time);
            }
        }
        return arrivalTimes.size();
    }

    void main() {
        IO.println(carFleet(
            12,
            new int[]{10, 8, 0, 5, 3},
            new int[]{2, 4, 1, 1, 3}
        ));   // 3
    }
}
