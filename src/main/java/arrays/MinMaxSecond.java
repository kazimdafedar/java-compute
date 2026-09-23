package arrays;

import java.util.Arrays;

/**
 * File 04 — Q407: Max/Min and Second Largest/Smallest in Array
 * Single pass — O(n) time, O(1) space.
 */
class MinMaxSecond {

    static int[] minMax(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return new int[]{min, max};
    }

    static int[] secondMinMax(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int x : nums) {
            if (x > max1) {
                max2 = max1;
                max1 = x;
            } else if (x > max2 && x != max1) {
                max2 = x;
            }
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2 && x != min1) {
                min2 = x;
            }
        }
        return new int[]{min2, max2};
    }

    void main() {
        int[] nums = {12, 35, 1, 10, 34, 1};
        IO.println("min/max:       " + Arrays.toString(minMax(nums)));
        IO.println("2nd min/max:   " + Arrays.toString(secondMinMax(nums)));
    }
}
