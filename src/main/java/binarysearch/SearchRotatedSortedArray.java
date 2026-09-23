package binarysearch;

/**
 * File 04 — Q360: Search in Rotated Sorted Array
 * O(log n) time, O(1) space.
 */
class SearchRotatedSortedArray {

    static int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    void main() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        IO.println(search(nums, 0));   // 4
        IO.println(search(nums, 3)); // -1
    }
}
