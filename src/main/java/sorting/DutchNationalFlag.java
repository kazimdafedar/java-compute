package sorting;

import java.util.Arrays;

/**
 * File 04 — Q411: Sort 0s, 1s, 2s (Dutch National Flag)
 * O(n) time, O(1) space.
 */
class DutchNationalFlag {

    static void sortColors(int[] nums) {
        int lo = 0;
        int mid = 0;
        int hi = nums.length - 1;
        while (mid <= hi) {
            if (nums[mid] == 0) {
                swap(nums, lo++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, hi--);
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        IO.println(Arrays.toString(nums));   // [0, 0, 1, 1, 2, 2]
    }
}
