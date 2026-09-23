package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File 04 — Q76: Three Sum
 * Find all unique triplets that sum to zero.
 * Sort + two pointers — O(n²) time, O(1) extra space (excluding output).
 */
class ThreeSum {

    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;   // skip duplicate anchor
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    void main() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> triplets = threeSum(nums);

        IO.println("Triplets summing to 0:");
        for (List<Integer> t : triplets) {
            IO.println("  " + t);   // [-1, -1, 2], [-1, 0, 1]
        }

        IO.println("Count: " + triplets.size());   // 2

        int[] none = {1, 2, 3};
        IO.println("No solution: " + threeSum(none));   // []
    }
}
