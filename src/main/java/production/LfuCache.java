package production;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * File 04 — J03: LFU Cache
 * O(1) get/put — NOT thread-safe.
 */
final class LfuCache<K, V> {

    private final int capacity;
    private int minFreq = 0;
    private final Map<K, Node> nodes = new HashMap<>();
    private final Map<Integer, LinkedHashSet<K>> freqBuckets = new HashMap<>();

    private final class Node {
        final K key;
        V value;
        int freq;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    LfuCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0");
        }
        this.capacity = capacity;
    }

    V get(K key) {
        Node node = nodes.get(key);
        if (node == null) {
            return null;
        }
        bumpFreq(node);
        return node.value;
    }

    void put(K key, V value) {
        if (capacity == 0) {
            return;
        }
        Node node = nodes.get(key);
        if (node != null) {
            node.value = value;
            bumpFreq(node);
            return;
        }
        if (nodes.size() >= capacity) {
            evict();
        }
        node = new Node(key, value);
        nodes.put(key, node);
        freqBuckets.computeIfAbsent(1, f -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }

    private void bumpFreq(Node node) {
        LinkedHashSet<K> bucket = freqBuckets.get(node.freq);
        bucket.remove(node.key);
        if (bucket.isEmpty() && node.freq == minFreq) {
            minFreq++;
        }
        node.freq++;
        freqBuckets.computeIfAbsent(node.freq, f -> new LinkedHashSet<>()).add(node.key);
    }

    private void evict() {
        K evictKey = freqBuckets.get(minFreq).iterator().next();
        freqBuckets.get(minFreq).remove(evictKey);
        nodes.remove(evictKey);
    }

    void main() {
        LfuCache<Integer, String> cache = new LfuCache<>(2);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.get(1);
        cache.put(3, "three");
        IO.println(cache.get(2));   // null — evicted
        IO.println(cache.get(1));   // one
    }
}
