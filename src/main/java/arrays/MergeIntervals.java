package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File 04 — Q79: Merge Intervals
 * Merge all overlapping intervals.
 * Sort by start + linear merge — O(n log n) time, O(n) output space.
 */
class MergeIntervals {

    static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] cur : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < cur[0]) {
                merged.add(new int[]{cur[0], cur[1]});
            } else {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], cur[1]);
            }
        }
        return merged.toArray(new int[0][]);
    }

    /** Follow-up: insert one interval into an already merged list. */
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

    static void printIntervals(int[][] intervals) {
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
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merged = merge(intervals);
        IO.print("Merged: ");
        printIntervals(merged);   // [[1,6],[8,10],[15,18]]

        int[][] overlap = {{1, 4}, {4, 5}};
        IO.print("Touching/overlap: ");
        printIntervals(merge(overlap));   // [[1,5]]

        int[][] existing = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] afterInsert = insert(existing, new int[]{4, 8});
        IO.print("After insert [4,8]: ");
        printIntervals(afterInsert);   // [[1,2],[3,10],[12,16]]
    }
}
