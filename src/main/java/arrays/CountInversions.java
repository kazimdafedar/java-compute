package arrays;

/**
 * File 04 — Q406: Count Inversions in Array
 * Merge sort divide-and-conquer — O(n log n) time, O(n) space.
 */
class CountInversions {

    static long countInversions(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSortCount(nums, temp, 0, nums.length - 1);
    }

    static long mergeSortCount(int[] nums, int[] temp, int lo, int hi) {
        if (lo >= hi) {
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        long inv = mergeSortCount(nums, temp, lo, mid)
                + mergeSortCount(nums, temp, mid + 1, hi);
        int i = lo;
        int j = mid + 1;
        int k = lo;
        while (i <= mid && j <= hi) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                inv += mid - i + 1;
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= hi) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, lo, nums, lo, hi - lo + 1);
        return inv;
    }

    void main() {
        IO.println(countInversions(new int[]{2, 4, 1, 3, 5}));   // 3
    }
}
