package com.example.array;

public class MaxSumSubArray {

  static void main() {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(maxSub(nums));
  }

//  private static int maxSub(int[] nums) {
//    int best = nums[0], cur = nums[0];
//    for (int i = 1; i < nums.length; i++) {
//      cur = Math.max(nums[i], cur + nums[i]);
//      best = Math.max(best, cur);
//    }
//    return best;
//  }

  static int maxSub(int[] nums) {

    int cur = 0, max = Integer.MIN_VALUE;

    for (int num : nums) {
      cur += num;
      if (cur <= num) {
        cur = num;
      }
      if (max <= cur) {
        max = cur;
      }
    }

    return max;
  }


}
