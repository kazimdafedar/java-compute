package arrays;

/**
 * File 04 — Q434: Majority Element (> n/2)
 * Boyer-Moore voting — O(n) time, O(1) space.
 */
class MajorityElement {

    static int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int x : nums) {
            if (count == 0) {
                candidate = x;
                count = 1;
            } else if (x == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    void main() {
        IO.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));   // 2
    }
}
