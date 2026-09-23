package arrays;

import java.util.Arrays;

/**
 * File 04 — Q359: Array Rotation when k May Exceed n
 * Always normalize with k %= n before rotating.
 */
class RotateArrayNormalized {

    static void rotateRight(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        k %= n;
        if (k == 0) {
            return;
        }
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    static void reverse(int[] nums, int lo, int hi) {
        while (lo < hi) {
            swap(nums, lo++, hi--);
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {1, 2, 3, 4, 5};
        rotateRight(nums, 7);   // same as k=2 when n=5
        IO.println(Arrays.toString(nums));   // [4, 5, 1, 2, 3]
    }
}
