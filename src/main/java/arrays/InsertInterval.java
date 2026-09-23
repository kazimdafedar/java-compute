package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Interval — merge one interval into an already-sorted non-overlapping list.
 * O(n) time, O(n) space. Follow-up to Q79.
 */
class InsertInterval {

    static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            merged.add(intervals[i++]);
        }
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        merged.add(newInterval);
        while (i < n) {
            merged.add(intervals[i++]);
        }
        return merged.toArray(new int[0][]);
    }

    static void print(int[][] intervals) {
        IO.print("[");
        for (int i = 0; i < intervals.length; i++) {
            IO.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }

    void main() {
        int[][] intervals = {{1, 3}, {6, 9}};
        print(insert(intervals, new int[]{2, 5}));   // [[1,5],[6,9]]

        int[][] existing = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        print(insert(existing, new int[]{4, 8}));    // [[1,2],[3,10],[12,16]]
    }
}
