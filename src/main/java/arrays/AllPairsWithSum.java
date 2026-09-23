package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File 04 — Q412: All Pairs with Given Sum
 * Two pointers on sorted array — O(n log n) time.
 */
class AllPairsWithSum {

    static List<int[]> allPairs(int[] nums, int target) {
        Arrays.sort(nums);
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo];
                if (sum == target) {
                    result.add(new int[]{nums[i], nums[lo]});
                    while (lo < hi && nums[lo] == nums[lo + 1]) {
                        lo++;
                    }
                    lo++;
                } else if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return result;
    }

    void main() {
        IO.println(allPairs(new int[]{1, 5, 7, -1, 5}, 6));   // [[1,5], [7,-1]]
    }
}
