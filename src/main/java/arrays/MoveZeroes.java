package arrays;

import java.util.Arrays;

/**
 * File 04 — Q409: Move Zeroes to End
 * Stable two-pointer — O(n) time, O(1) space.
 */
class MoveZeroes {

    static void moveZeroes(int[] nums) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                swap(nums, write, read);
                write++;
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        IO.println(Arrays.toString(nums));   // [1, 3, 12, 0, 0]
    }
}
