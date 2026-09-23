package binarysearch;

/**
 * File 04 — Q435: Median of Two Sorted Arrays
 * Binary search on partition — O(log(min(m,n))) time.
 */
class MedianOfTwoSortedArrays {

    static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }
        int m = a.length;
        int n = b.length;
        int half = (m + n + 1) / 2;
        int lo = 0;
        int hi = m;

        while (lo <= hi) {
            int i = lo + (hi - lo) / 2;
            int j = half - i;
            int aLeft = i > 0 ? a[i - 1] : Integer.MIN_VALUE;
            int aRight = i < m ? a[i] : Integer.MAX_VALUE;
            int bLeft = j > 0 ? b[j - 1] : Integer.MIN_VALUE;
            int bRight = j < n ? b[j] : Integer.MAX_VALUE;

            if (aLeft <= bRight && bLeft <= aRight) {
                if ((m + n) % 2 == 1) {
                    return Math.max(aLeft, bLeft);
                }
                return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2.0;
            }
            if (aLeft > bRight) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("impossible");
    }

    void main() {
        IO.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));       // 2.0
        IO.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));   // 2.5
    }
}
