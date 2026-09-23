package binarysearch;

/**
 * File 04 — Q404: Interpolation Search
 * O(log log n) average on uniform data.
 */
class InterpolationSearch {

    static int interpolationSearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi && target >= nums[lo] && target <= nums[hi]) {
            if (lo == hi) {
                return nums[lo] == target ? lo : -1;
            }
            int pos = lo + (int) ((long) (target - nums[lo]) * (hi - lo) / (nums[hi] - nums[lo]));
            if (nums[pos] == target) {
                return pos;
            }
            if (nums[pos] < target) {
                lo = pos + 1;
            } else {
                hi = pos - 1;
            }
        }
        return -1;
    }

    void main() {
        int[] nums = {10, 20, 30, 40, 50, 60, 70};
        IO.println(interpolationSearch(nums, 60));   // 5
        IO.println(interpolationSearch(nums, 25));   // -1
    }
}
