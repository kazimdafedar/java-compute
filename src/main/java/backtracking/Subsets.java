package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * File 04 — Q347: Subsets (Power Set)
 */
class Subsets {

    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    void main() {
        IO.println(subsets(new int[]{1, 2, 3}));   // 8 subsets
    }
}
