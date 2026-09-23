package arrays;

import java.util.Arrays;

/**
 * File 04 — Q420: Even Before Odd Rearrangement
 * Two-pointer partition — O(n) time, O(1) space.
 */
class EvenBeforeOdd {

    static void evenBeforeOdd(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            if (nums[lo] % 2 == 0) {
                lo++;
            } else if (nums[hi] % 2 == 1) {
                hi--;
            } else {
                swap(nums, lo++, hi--);
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {3, 1, 2, 4};
        evenBeforeOdd(nums);
        IO.println(Arrays.toString(nums));   // [2, 4, 3, 1] (any valid ordering)
    }
}
