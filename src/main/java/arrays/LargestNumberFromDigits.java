package arrays;

import java.util.Arrays;

/**
 * File 04 — Q419: Largest/Smallest Number from Array Digits
 * Custom comparator sort — O(n log n) time.
 */
class LargestNumberFromDigits {

    static String largestNumber(int[] nums) {
        String[] parts = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            parts[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(parts, (x, y) -> (y + x).compareTo(x + y));
        if (parts[0].equals("0")) {
            return "0";
        }
        return String.join("", parts);
    }

    static String smallestNumber(int[] nums) {
        String[] parts = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            parts[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(parts, (x, y) -> (x + y).compareTo(y + x));
        if (parts[0].equals("0")) {
            return "0";
        }
        return String.join("", parts);
    }

    void main() {
        int[] nums = {10, 2};
        IO.println(largestNumber(nums));    // 210
        IO.println(smallestNumber(nums));   // 102
    }
}
