package arrays;

import java.util.Arrays;

/**
 * File 04 — Q393: Remove Duplicates from Sorted Array
 * Slow/fast write pointer — O(n) time, O(1) space.
 */
class RemoveDuplicates {

    static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int write = 1;
        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[read - 1]) {
                nums[write++] = nums[read];
            }
        }
        return write;
    }

    void main() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int count = removeDuplicates(nums);
        IO.println(count);                                      // 5
        IO.println(Arrays.toString(Arrays.copyOf(nums, count))); // [0, 1, 2, 3, 4]
    }
}
