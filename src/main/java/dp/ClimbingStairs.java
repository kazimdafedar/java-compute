package dp;

/**
 * File 04 — Q104: Climbing Stairs / Fibonacci
 * O(n) time, O(1) space.
 */
class ClimbingStairs {

    static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int prev2 = 1;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int current = prev2 + prev1;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    void main() {
        IO.println(climbStairs(5));   // 8
        IO.println(climbStairs(3));   // 3
    }
}
