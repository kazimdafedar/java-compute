package arrays;

/**
 * File 04 — Q82: Find the Duplicate Number
 * n+1 integers in [1, n] with exactly one duplicate — find it.
 * Floyd's cycle detection (treat index as next pointer: i → nums[i]).
 * O(n) time, O(1) space — no mutation, no extra array.
 */
class FindDuplicate {

    static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * Alternative when mutation allowed: mark visited by negating nums[abs(x)].
     * O(n) time, O(1) space — not suitable if array must stay unchanged.
     */
    static int findDuplicateMark(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]);
            if (nums[idx] < 0) {
                return idx;
            }
            nums[idx] = -nums[idx];
        }
        return -1;
    }

    void main() {
        int[] nums = {1, 3, 4, 2, 2};
        IO.println("Duplicate: " + findDuplicate(nums));   // 2

        int[] nums2 = {3, 1, 3, 4, 2};
        IO.println("Duplicate: " + findDuplicate(nums2));  // 3

        int[] nums3 = {1, 1};
        IO.println("Duplicate: " + findDuplicate(nums3));  // 1

        int[] markTest = {1, 3, 4, 2, 2};
        IO.println("Mark (mutates): " + findDuplicateMark(markTest));   // 2
    }
}
