package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * File 04 — Q403: Quicksort
 * Lomuto partition — O(n log n) average.
 */
class Quicksort {

    static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    static void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = partition(nums, lo, hi);
        quickSort(nums, lo, pivot - 1);
        quickSort(nums, pivot + 1, hi);
    }

    static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, hi);
        return i;
    }

    static void quickSortRandomized(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int randomIndex = lo + new Random().nextInt(hi - lo + 1);
        swap(nums, randomIndex, hi);
        int pivot = partition(nums, lo, hi);
        quickSortRandomized(nums, lo, pivot - 1);
        quickSortRandomized(nums, pivot + 1, hi);
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {3, 6, 8, 10, 1, 2, 1};
        quickSort(nums);
        IO.println(Arrays.toString(nums));   // [1, 1, 2, 3, 6, 8, 10]
    }
}
