package arrays;

/**
 * File 04 — Q77: Maximum Subarray (Kadane's algorithm)
 * Find the contiguous subarray with the largest sum.
 * Time O(n), space O(1). No collections — loops + Math.max only.
 */
class MaximumSubarray {

    static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("empty array");
        }

        int best = nums[0];
        int cur = nums[0];

        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

    /** Follow-up: return [start, end] indices of a max-sum subarray. */
    static int[] maxSubArrayIndices(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("empty array");
        }

        int best = nums[0];
        int cur = nums[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > cur + nums[i]) {
                cur = nums[i];
                tempStart = i;
            } else {
                cur = cur + nums[i];
            }
            if (cur > best) {
                best = cur;
                start = tempStart;
                end = i;
            }
        }
        return new int[]{start, end};
    }

    void main() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int maxSum = maxSubArray(nums);
        IO.println("Max sum: " + maxSum);  // 6

        int[] range = maxSubArrayIndices(nums);
        IO.println("Indices: [" + range[0] + ", " + range[1] + "]");  // [3, 6]
        IO.print("Subarray: [");
        for (int i = range[0]; i <= range[1]; i++) {
            IO.print(nums[i]);
            if (i < range[1]) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }
}
