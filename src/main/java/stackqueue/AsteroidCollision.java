package stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q374: Asteroid Collision
 * Stack simulation — O(n) time.
 */
class AsteroidCollision {

    static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int x : asteroids) {
            boolean alive = true;
            while (alive && x < 0 && !stack.isEmpty() && stack.peek() > 0) {
                if (stack.peek() < -x) {
                    stack.pop();
                    continue;
                }
                if (stack.peek() == -x) {
                    stack.pop();
                }
                alive = false;
            }
            if (alive) {
                stack.push(x);
            }
        }
        int[] result = new int[stack.size()];
        int i = 0;
        for (int value : stack) {
            result[i++] = value;
        }
        return result;
    }

    void main() {
        ArrayUtils.print(asteroidCollision(new int[]{5, 10, -5}));   // [5, 10]
        ArrayUtils.print(asteroidCollision(new int[]{8, -8}));       // []
        ArrayUtils.print(asteroidCollision(new int[]{-2, -1, 1, 2})); // [-2, -1, 1, 2]
    }
}
