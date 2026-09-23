package production;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * File 04 — J08: Top K utilities
 */
class TopK {

    static <T> List<T> topK(Collection<T> items, int k, Comparator<T> comparator) {
        if (k <= 0) {
            return List.of();
        }
        PriorityQueue<T> minHeap = new PriorityQueue<>(k, comparator);
        for (T item : items) {
            minHeap.offer(item);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return new ArrayList<>(minHeap);
    }

    @SuppressWarnings("unchecked")
    static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.merge(n, 1, Integer::sum);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        freq.forEach((num, count) -> {
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(num);
        });
        List<Integer> result = new ArrayList<>();
        for (int c = buckets.length - 1; c >= 0 && result.size() < k; c--) {
            if (buckets[c] != null) {
                result.addAll(buckets[c]);
            }
        }
        return result.subList(0, k);
    }

    void main() {
        IO.println(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));   // [1, 2]
    }
}
