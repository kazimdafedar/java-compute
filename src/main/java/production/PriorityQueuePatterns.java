package production;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * File 04 — J09: PriorityQueue patterns
 */
class PriorityQueuePatterns {

    record Task(String id, int priority, Instant deadline) {}

    record Entry(String id, int priority, int gen) {}

    static PriorityQueue<Task> taskQueue() {
        return new PriorityQueue<>(Comparator
                .comparingInt(Task::priority)
                .thenComparing(Task::deadline));
    }

    static class LazyPriorityQueue {
        private final Map<String, Integer> entryGen = new HashMap<>();
        private final PriorityQueue<Entry> queue = new PriorityQueue<>(Comparator.comparingInt(Entry::priority));

        void offer(String id, int priority) {
            int gen = entryGen.getOrDefault(id, 0);
            queue.offer(new Entry(id, priority, gen));
        }

        void removeLazy(String id) {
            entryGen.merge(id, 1, Integer::sum);
        }

        Entry pollValid() {
            while (!queue.isEmpty()) {
                Entry entry = queue.peek();
                if (entryGen.getOrDefault(entry.id(), 0) == entry.gen()) {
                    return queue.poll();
                }
                queue.poll();
            }
            return null;
        }
    }

    void main() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(3);
        maxHeap.offer(1);
        maxHeap.offer(2);
        IO.println(maxHeap.poll());   // 3

        PriorityQueue<Task> tasks = taskQueue();
        tasks.offer(new Task("A", 3, Instant.now()));
        tasks.offer(new Task("B", 1, Instant.now()));
        tasks.offer(new Task("C", 2, Instant.now()));
        IO.println(tasks.poll().id());   // B
    }
}
