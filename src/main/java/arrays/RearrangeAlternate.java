package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File 04 — Q414: Rearrange Positive/Negative Alternate
 * Stable merge of partitions — O(n) time, O(n) space.
 */
class RearrangeAlternate {

    static int[] rearrangeAlternate(int[] nums) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        for (int x : nums) {
            if (x >= 0) {
                positive.add(x);
            } else {
                negative.add(x);
            }
        }
        int[] result = new int[nums.length];
        int p = 0;
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            result[i] = (i % 2 == 0) ? positive.get(p++) : negative.get(n++);
        }
        return result;
    }

    void main() {
        IO.println(Arrays.toString(rearrangeAlternate(new int[]{1, 2, 3, -4, -1, 4})));
        // [1, -4, 2, -1, 3, 4]
    }
}
