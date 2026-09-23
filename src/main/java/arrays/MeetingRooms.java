package arrays;

import java.util.Arrays;

/**
 * Meeting Rooms I — can one person attend all meetings?
 * Sort by start, check overlap. O(n log n). Pair with Q436 (Meeting Rooms II).
 */
class MeetingRooms {

    static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    void main() {
        IO.println(canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}}));   // false
        IO.println(canAttendMeetings(new int[][]{{7, 10}, {2, 4}}));              // true
    }
}
