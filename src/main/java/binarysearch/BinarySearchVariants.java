package binarysearch;

/**
 * File 04 — Q110: Binary Search Variants
 * Exact match, lower bound, upper bound — O(log n) time.
 */
class BinarySearchVariants {

    static int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    static int lowerBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    static int upperBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    void main() {
        int[] nums = {1, 3, 5, 7, 9};
        IO.println(search(nums, 5));         // 2
        IO.println(search(nums, 6));         // -1
        IO.println(lowerBound(nums, 6));     // 3
        IO.println(upperBound(nums, 5));     // 3
    }
}
