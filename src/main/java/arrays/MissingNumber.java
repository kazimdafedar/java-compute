package arrays;

/**
 * File 04 — Q81: Missing Number
 * Array has n distinct numbers in [0, n]; one is missing.
 * XOR — O(n) time, O(1) space. Sum formula is an alternative.
 */
class MissingNumber {

    /** XOR all indices 0..n with all values — duplicates cancel, missing remains. */
    static int missingNumber(int[] nums) {
        int xor = nums.length;   // includes n in range [0, n]
        for (int i = 0; i < nums.length; i++) {
            xor ^= i ^ nums[i];
        }
        return xor;
    }

    /** Alternative: expected sum n*(n+1)/2 minus actual sum. */
    static int missingNumberSum(int[] nums) {
        int n = nums.length;
        int expected = n * (n + 1) / 2;
        int actual = 0;
        for (int num : nums) {
            actual += num;
        }
        return expected - actual;
    }

    void main() {
        int[] nums = {3, 0, 1};
        IO.println("XOR:  " + missingNumber(nums));       // 2
        IO.println("Sum:  " + missingNumberSum(nums));     // 2

        int[] nums2 = {0, 1};
        IO.println("Missing 2: " + missingNumber(nums2));   // 2

        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        IO.println("Missing 8: " + missingNumber(nums3));   // 8
    }
}
