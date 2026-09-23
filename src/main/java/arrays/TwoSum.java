package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q75: Two Sum
 * Return indices of two numbers that add up to target.
 * Optimal: HashMap complement — O(n) time, O(n) space.
 */
class TwoSum {

    /** One-pass hash map: store value → index, look for complement. */
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[]{seen.get(need), i};
            }
            seen.put(nums[i], i);
        }
        return new int[0];  // no solution
    }

    /**
     * Follow-up: sorted input — two pointers, O(n) time, O(1) extra space.
     * Returns values (not indices) since sorting reorders the array.
     */
    static int[] twoSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{nums[left], nums[right]};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    /** Brute force — O(n²), no HashMap (only loops). */
    static int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    void main() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] indices = twoSum(nums, target);
        IO.println("Indices: [" + indices[0] + ", " + indices[1] + "]");  // [0, 1]
        IO.println("Values: " + nums[indices[0]] + " + " + nums[indices[1]] + " = " + target);

        int[] sorted = {2, 3, 4, 7, 11, 15};
        int[] pair = twoSumSorted(sorted, 9);
        IO.println("Two pointers (sorted): " + pair[0] + ", " + pair[1]);  // 2, 7

        int[] dup = {3, 3};
        int[] dupResult = twoSum(dup, 6);
        IO.println("Duplicates: [" + dupResult[0] + ", " + dupResult[1] + "]");  // [0, 1]
    }
}
