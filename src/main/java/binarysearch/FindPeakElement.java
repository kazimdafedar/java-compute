package binarysearch;

/**
 * Find Peak Element — binary search on slope.
 * A peak is strictly greater than neighbors. O(log n) time.
 */
class FindPeakElement {

    static int findPeakElement(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    void main() {
        IO.println(findPeakElement(new int[]{1, 2, 3, 1}));       // 2
        IO.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4})); // 1 or 5
    }
}
