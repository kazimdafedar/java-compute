package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Task Scheduler — cool-down n between same tasks.
 * Max-heap of remaining counts. O(time · 26 log 26).
 */
class TaskScheduler {

    static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int count : freq) {
            if (count > 0) {
                heap.offer(count);
            }
        }

        int time = 0;
        while (!heap.isEmpty()) {
            List<Integer> cooldown = new ArrayList<>();
            int slots = n + 1;
            while (slots > 0 && !heap.isEmpty()) {
                int remaining = heap.poll() - 1;
                if (remaining > 0) {
                    cooldown.add(remaining);
                }
                time++;
                slots--;
            }
            heap.addAll(cooldown);
            if (!heap.isEmpty()) {
                time += slots;
            }
        }
        return time;
    }

    void main() {
        IO.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));   // 8
        IO.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));   // 6
    }
}
