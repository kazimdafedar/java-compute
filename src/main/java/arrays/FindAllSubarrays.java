package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File 04 — Q422: Find All Subarrays
 * Enumerate O(n²) — count = n(n+1)/2.
 */
class FindAllSubarrays {

    static List<int[]> allSubarrays(int[] nums) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                result.add(Arrays.copyOfRange(nums, i, j + 1));
            }
        }
        return result;
    }

    static int countSubarrays(int n) {
        return n * (n + 1) / 2;
    }

    void main() {
        IO.println("count: " + countSubarrays(3));   // 6
        IO.println(allSubarrays(new int[]{1, 2, 3}));
    }
}
