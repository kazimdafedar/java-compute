package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Four Sum — sort + two nested loops + two pointers.
 * O(n³) time. Extension of Q76 Three Sum.
 */
class FourSum {

    static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int lo = j + 1;
                int hi = n - 1;
                while (lo < hi) {
                    long sum = (long) nums[i] + nums[j] + nums[lo] + nums[hi];
                    if (sum == target) {
                        result.add(List.of(nums[i], nums[j], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (sum < target) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return result;
    }

    void main() {
        IO.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        // [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
    }
}
