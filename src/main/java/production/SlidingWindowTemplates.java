package production;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — J15: Sliding window templates
 */
class SlidingWindowTemplates {

    static int maxSumFixed(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("invalid k");
        }
        int window = 0;
        for (int i = 0; i < k; i++) {
            window += nums[i];
        }
        int best = window;
        for (int i = k; i < nums.length; i++) {
            window += nums[i] - nums[i - k];
            best = Math.max(best, window);
        }
        return best;
    }

    static int longestUniqueSubstring(String text) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int start = 0;
        int best = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= start) {
                start = lastSeen.get(c) + 1;
            }
            lastSeen.put(c, i);
            best = Math.max(best, i - start + 1);
        }
        return best;
    }

    static int minSubarrayLen(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) {
                best = Math.min(best, end - start + 1);
                sum -= nums[start++];
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }

    void main() {
        IO.println(maxSumFixed(new int[]{2, 1, 5, 1, 3, 2}, 3));   // 9
        IO.println(longestUniqueSubstring("abcabcbb"));             // 3
        IO.println(minSubarrayLen(7, new int[]{2, 3, 1, 2, 4, 3})); // 2
    }
}
