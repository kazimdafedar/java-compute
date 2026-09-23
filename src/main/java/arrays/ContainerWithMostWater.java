package arrays;

/**
 * Container With Most Water — two pointers from both ends.
 * O(n) time, O(1) space. Often paired with Q354 (Trapping Rain Water).
 */
class ContainerWithMostWater {

    static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int best = 0;
        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            best = Math.max(best, width * h);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }

    void main() {
        IO.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));   // 49
        IO.println(maxArea(new int[]{1, 1}));                         // 1
    }
}
