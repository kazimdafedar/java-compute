package arrays;

/**
 * File 04 — Q405: Kth Smallest Element in Array
 * Quickselect — O(n) average, O(n²) worst.
 */
class KthSmallestElement {

    static int kthSmallest(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;
        int target = k - 1;
        while (lo < hi) {
            int pivot = partition(nums, lo, hi);
            if (pivot == target) {
                break;
            }
            if (pivot < target) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return nums[target];
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

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        IO.println(kthSmallest(nums, 2));   // 2
    }
}
