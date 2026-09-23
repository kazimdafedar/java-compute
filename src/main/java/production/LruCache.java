package production;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * File 04 — J02: LRU Cache (LinkedHashMap access-order)
 * O(1) get/put — NOT thread-safe.
 */
final class LruCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    LruCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    void main() {
        LruCache<String, String> cache = new LruCache<>(2);
        cache.put("a", "data1");
        cache.put("b", "data2");
        IO.println(cache.get("a"));
        cache.put("c", "data3");
        IO.println(cache.get("b"));   // null — evicted
    }
}
