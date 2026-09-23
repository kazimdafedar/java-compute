package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * File 04 — Q348: Permutations
 */
class Permutations {

    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    static void permute(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            current.add(nums[i]);
            permute(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    void main() {
        IO.println(permute(new int[]{1, 2, 3}));   // 6 permutations
    }
}
