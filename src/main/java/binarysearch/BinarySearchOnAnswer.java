package binarysearch;

/**
 * Binary Search on Answer — Koko Eating Bananas + Capacity To Ship Packages.
 * Search the monotone feasible range. O(n log M) time.
 */
class BinarySearchOnAnswer {

    static int minEatingSpeed(int[] piles, int hours) {
        int lo = 1;
        int hi = 0;
        for (int pile : piles) {
            hi = Math.max(hi, pile);
        }
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFinish(piles, hours, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    static boolean canFinish(int[] piles, int hours, int speed) {
        long needed = 0;
        for (int pile : piles) {
            needed += (pile + speed - 1L) / speed;
        }
        return needed <= hours;
    }

    static int shipWithinDays(int[] weights, int days) {
        int lo = 0;
        int hi = 0;
        for (int w : weights) {
            lo = Math.max(lo, w);
            hi += w;
        }
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canShip(weights, days, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    static boolean canShip(int[] weights, int days, int capacity) {
        int used = 1;
        int load = 0;
        for (int w : weights) {
            if (load + w > capacity) {
                used++;
                load = 0;
            }
            load += w;
        }
        return used <= days;
    }

    void main() {
        IO.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));          // 4
        IO.println(shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5)); // 15
    }
}
