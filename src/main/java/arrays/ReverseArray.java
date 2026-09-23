package arrays;

import java.util.Arrays;

/**
 * File 04 — Q408: Reverse Array In-Place
 * Two pointers — O(n) time, O(1) space.
 */
class ReverseArray {

    static void reverse(int[] nums) {
        for (int left = 0, right = nums.length - 1; left < right; left++, right--) {
            swap(nums, left, right);
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {1, 2, 3, 4, 5};
        reverse(nums);
        IO.println(Arrays.toString(nums));   // [5, 4, 3, 2, 1]
    }
}
