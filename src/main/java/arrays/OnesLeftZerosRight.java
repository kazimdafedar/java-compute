package arrays;

import java.util.Arrays;

/**
 * Move all 1s left and all 0s right — two pointers.
 * O(n) time, O(1) space.
 */
class OnesLeftZerosRight {

    static void partition(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] == 1) {
                left++;
            } else if (nums[right] == 0) {
                right--;
            } else {
                swap(nums, left++, right--);
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {1, 0, 1, 1, 0, 0, 1, 1, 0};
        partition(nums);
        IO.println(Arrays.toString(nums));   // [1, 1, 1, 1, 1, 0, 0, 0, 0]
    }
}
