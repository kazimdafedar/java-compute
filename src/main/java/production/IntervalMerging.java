package production;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * File 04 — J10: Interval merging (typed record API)
 */
class IntervalMerging {

    record Interval(int start, int end) {
        Interval {
            if (start > end) {
                throw new IllegalArgumentException("start > end: " + start + " > " + end);
            }
        }

        boolean overlaps(Interval other) {
            return end >= other.start;
        }

        Interval merge(Interval other) {
            return new Interval(start, Math.max(end, other.end));
        }
    }

    static List<Interval> merge(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return List.of();
        }
        List<Interval> sorted = intervals.stream()
                .sorted(Comparator.comparingInt(Interval::start))
                .toList();
        List<Interval> result = new ArrayList<>();
        Interval current = sorted.get(0);
        for (int i = 1; i < sorted.size(); i++) {
            Interval next = sorted.get(i);
            if (current.overlaps(next)) {
                current = current.merge(next);
            } else {
                result.add(current);
                current = next;
            }
        }
        result.add(current);
        return result;
    }

    void main() {
        IO.println(merge(List.of(
                new Interval(1, 3),
                new Interval(2, 6),
                new Interval(8, 10),
                new Interval(15, 18)
        )));
    }
}
