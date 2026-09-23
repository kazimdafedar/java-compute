package arrays;

import java.util.Arrays;

/**
 * File 04 — Q436: Minimum Platforms (Meeting Rooms II)
 * Sort + sweep line — O(n log n) time, O(1) space.
 */
class MinimumPlatforms {

    static int minPlatforms(int[] arrival, int[] departure) {
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int i = 0;
        int j = 0;
        int platforms = 0;
        int best = 0;
        while (i < arrival.length) {
            if (arrival[i] <= departure[j]) {
                platforms++;
                i++;
            } else {
                platforms--;
                j++;
            }
            best = Math.max(best, platforms);
        }
        return best;
    }

    void main() {
        IO.println(minPlatforms(
                new int[]{900, 940, 950, 1100, 1500, 1800},
                new int[]{910, 1200, 1120, 1130, 1900, 2000}
        ));   // 3
    }
}
