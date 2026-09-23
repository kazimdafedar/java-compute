package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * File 04 — Q84: Top K Frequent Elements
 * Bucket sort by frequency — O(n) time, O(n) space.
 * Min-heap alternative — O(n log k) when k is small.
 */
class TopKFrequent {

    /** Bucket sort: index = frequency count. */
    @SuppressWarnings("unchecked")
    static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int count = e.getValue();
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(e.getKey());
        }

        List<Integer> result = new ArrayList<>();
        for (int count = buckets.length - 1; count >= 0 && result.size() < k; count--) {
            if (buckets[count] != null) {
                result.addAll(buckets[count]);
            }
        }
        return result.subList(0, k);
    }

    /** Min-heap of size k — O(n log k), less memory when k is small. */
    static List<Integer> topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> freq.get(a) - freq.get(b));

        for (int num : freq.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return new ArrayList<>(minHeap);
    }

    static void printList(List<Integer> list) {
        IO.print("[");
        for (int i = 0; i < list.size(); i++) {
            IO.print(list.get(i));
            if (i < list.size() - 1) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }

    void main() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        IO.print("Bucket: ");
        printList(topKFrequent(nums, k));   // [1, 2] (order may vary)

        IO.print("Heap:   ");
        printList(topKFrequentHeap(nums, k));

        int[] nums2 = {4, 4, 4, 5, 5, 6, 6, 6, 6};
        IO.print("k=2: ");
        printList(topKFrequent(nums2, 2));   // [6, 4] or [6, 5]
    }
}
