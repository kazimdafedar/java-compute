package arrays;

/**
 * File 04 — Q83: Rotate Array
 * Rotate array to the right by k positions in-place.
 * Reverse three times — O(n) time, O(1) space.
 */
class RotateArray {

    static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        k %= nums.length;
        if (k == 0) {
            return;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    static void printArray(int[] a) {
        IO.print("[");
        for (int i = 0; i < a.length; i++) {
            IO.print(a[i]);
            if (i < a.length - 1) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }

    void main() {
        int[] nums = {1, 2, 3, 4, 5};
        IO.print("Before: ");
        printArray(nums);

        rotate(nums, 2);
        IO.print("k=2:   ");
        printArray(nums);   // [4, 5, 1, 2, 3]

        int[] nums2 = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums2, 3);
        IO.print("k=3:   ");
        printArray(nums2);   // [5, 6, 7, 1, 2, 3, 4]

        int[] nums3 = {1, 2, 3, 4, 5};
        rotate(nums3, 7);    // k > length → same as k % n = 2
        IO.print("k=7:   ");
        printArray(nums3);   // [4, 5, 1, 2, 3]
    }
}
