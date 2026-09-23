package binarysearch;

/**
 * File 04 — Q410: First and Last Occurrence
 * Lower/upper bound — O(log n) time.
 */
class SearchRange {

    static int[] searchRange(int[] nums, int target) {
        int lo = lowerBound(nums, target);
        if (lo == nums.length || nums[lo] != target) {
            return new int[]{-1, -1};
        }
        int hi = upperBound(nums, target) - 1;
        return new int[]{lo, hi};
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
        int[] nums = {5, 7, 7, 8, 8, 10};
        IO.println(java.util.Arrays.toString(searchRange(nums, 8)));   // [3, 4]
        IO.println(java.util.Arrays.toString(searchRange(nums, 6)));   // [-1, -1]
    }
}
