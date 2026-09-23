package arrays;

/**
 * File 04 — Q416: Maximum Product Subarray
 * Track min and max — O(n) time, O(1) space.
 */
class MaximumProductSubarray {

    static int maxProduct(int[] nums) {
        int best = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            best = Math.max(best, max);
        }
        return best;
    }

    void main() {
        IO.println(maxProduct(new int[]{2, 3, -2, 4}));   // 6
    }
}
