package concurrency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * File 03 — Q300: HashMap vs Hashtable vs synchronizedMap vs ConcurrentHashMap.
 */
class ConcurrentHashMapGuide {

    static int lostUpdatesWithHashMap() throws InterruptedException {
        Map<String, Integer> map = new HashMap<>();
        map.put("hits", 0);
        runIncrements(map, 2, 5_000);
        return map.get("hits");
    }

    static int atomicUpdatesWithChm() throws InterruptedException {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("hits", 0);
        CountDownLatch start = new CountDownLatch(1);
        Thread t1 = Thread.ofPlatform().start(() -> incrementMerge(map, start, 5_000));
        Thread t2 = Thread.ofPlatform().start(() -> incrementMerge(map, start, 5_000));
        start.countDown();
        t1.join();
        t2.join();
        return map.get("hits");
    }

    static void incrementMerge(Map<String, Integer> map, CountDownLatch start, int times) {
        await(start);
        for (int i = 0; i < times; i++) {
            map.merge("hits", 1, Integer::sum);
        }
    }

    static void runIncrements(Map<String, Integer> map, int threads, int times) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        Thread[] workers = new Thread[threads];
        for (int t = 0; t < threads; t++) {
            workers[t] = Thread.ofPlatform().start(() -> {
                await(start);
                for (int i = 0; i < times; i++) {
                    map.put("hits", map.get("hits") + 1);
                }
            });
        }
        start.countDown();
        for (Thread worker : workers) {
            worker.join();
        }
    }

    void main() throws InterruptedException {
        IO.println("HashMap get+put (racy):     " + lostUpdatesWithHashMap() + "  (often < 10000)");
        IO.println("CHM merge (atomic):         " + atomicUpdatesWithChm() + "  (always 10000)");

        Map<String, Integer> chm = new ConcurrentHashMap<>();
        try {
            chm.put("a", null);
        } catch (NullPointerException e) {
            IO.println("CHM rejects null values");
        }
        try {
            new Hashtable<String, Integer>().put(null, 1);
        } catch (NullPointerException e) {
            IO.println("Hashtable rejects null keys");
        }

        Map<String, Integer> synced = Collections.synchronizedMap(new HashMap<>());
        synced.put("a", 1);
        synchronized (synced) {
            for (var e : synced.entrySet()) {
                IO.println("synced iterate: " + e);
            }
        }
        chm.computeIfAbsent("k", k -> 42);
        IO.println("computeIfAbsent: " + chm.get("k"));
    }

    static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
