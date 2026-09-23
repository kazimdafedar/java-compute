package arrays;

/**
 * Jump Game I / II — greedy reach vs min jumps.
 * I: O(n) can-reach. II: O(n) BFS-style farthest window.
 */
class JumpGame {

    static boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                return false;
            }
            reach = Math.max(reach, i + nums[i]);
        }
        return true;
    }

    static int jump(int[] nums) {
        int jumps = 0;
        int end = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }

    void main() {
        IO.println(canJump(new int[]{2, 3, 1, 1, 4}));   // true
        IO.println(canJump(new int[]{3, 2, 1, 0, 4}));   // false
        IO.println(jump(new int[]{2, 3, 1, 1, 4}));      // 2
    }
}
