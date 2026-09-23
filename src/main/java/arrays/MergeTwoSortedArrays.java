package arrays;

import java.util.Arrays;

/**
 * File 04 — Q391: Merge Two Sorted Arrays
 * Two pointers from end — O(m+n) time, O(1) space.
 */
class MergeTwoSortedArrays {

    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int write = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[write--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j >= 0) {
            nums1[write--] = nums2[j--];
        }
    }

    void main() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[]{2, 5, 6}, 3);
        IO.println(Arrays.toString(nums1));   // [1, 2, 2, 3, 5, 6]
    }
}
