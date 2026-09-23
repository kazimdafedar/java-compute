package arrays;

/**
 * File 04 — Q80: Product of Array Except Self
 * output[i] = product of all nums except nums[i]. No division.
 * Prefix pass + suffix pass — O(n) time, O(1) extra space (output excluded).
 */
class ProductExceptSelf {

    static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];   // prefix products
        }

        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }
        return result;
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
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);
        IO.print("Input:  ");
        printArray(nums);
        IO.print("Output: ");
        printArray(result);   // [24, 12, 8, 6]

        int[] withZero = {1, 2, 0, 4};
        IO.print("With zero: ");
        printArray(productExceptSelf(withZero));   // [0, 0, 8, 0]
    }
}
