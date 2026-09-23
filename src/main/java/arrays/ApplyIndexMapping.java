package arrays;

import java.util.Arrays;

/**
 * File 04 — Q418: arr[i] = arr[arr[i]] In-Place
 * Modular encoding — O(n) time, O(1) space.
 */
class ApplyIndexMapping {

    static void applyIndexMapping(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] += (nums[nums[i]] % n) * n;
        }
        for (int i = 0; i < n; i++) {
            nums[i] /= n;
        }
    }

    void main() {
        int[] nums = {3, 2, 1, 4, 0};
        applyIndexMapping(nums);
        IO.println(Arrays.toString(nums));   // [4, 1, 2, 0, 3]
    }
}
