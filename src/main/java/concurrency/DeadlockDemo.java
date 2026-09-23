package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * File 02 — Q58: Show a deadlock and fix it.
 * T1 locks A then B; T2 locks B then A → circular wait.
 * Fixes: global lock order, or tryLock + timeout + backoff.
 */
class DeadlockDemo {

    static void transferUnordered(Object from, Object to) {
        synchronized (from) {
            sleep(80);
            synchronized (to) {
                IO.println(Thread.currentThread().getName() + " transferred");
            }
        }
    }

    /** Both threads acquire locks in the same identity-hash order — cycle broken. */
    static void transferOrdered(Object from, Object to) {
        Object first = System.identityHashCode(from) <= System.identityHashCode(to) ? from : to;
        Object second = first == from ? to : from;
        synchronized (first) {
            sleep(20);
            synchronized (second) {
                IO.println(Thread.currentThread().getName() + " transferred (ordered)");
            }
        }
    }

    static boolean transferTryLock(ReentrantLock from, ReentrantLock to) throws InterruptedException {
        if (!from.tryLock(100, TimeUnit.MILLISECONDS)) {
            return false;
        }
        try {
            if (!to.tryLock(100, TimeUnit.MILLISECONDS)) {
                return false;
            }
            try {
                IO.println(Thread.currentThread().getName() + " transferred (tryLock)");
                return true;
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }

    void main() throws InterruptedException {
        Object accountA = new Object();
        Object accountB = new Object();

        CountDownLatch started = new CountDownLatch(2);
        Thread t1 = Thread.ofPlatform().daemon(true).name("T1-A-then-B").start(() -> {
            started.countDown();
            await(started);
            transferUnordered(accountA, accountB);
        });
        Thread t2 = Thread.ofPlatform().daemon(true).name("T2-B-then-A").start(() -> {
            started.countDown();
            await(started);
            transferUnordered(accountB, accountA);
        });

        t1.join(800);
        t2.join(800);
        if (t1.isAlive() || t2.isAlive()) {
            IO.println("DEADLOCK: T1 holds A wants B; T2 holds B wants A");
        }

        Object orderedA = new Object();
        Object orderedB = new Object();
        Thread.ofPlatform().name("ordered-1").start(() -> transferOrdered(orderedA, orderedB)).join();
        Thread.ofPlatform().name("ordered-2").start(() -> transferOrdered(orderedB, orderedA)).join();

        ReentrantLock lockA = new ReentrantLock();
        ReentrantLock lockB = new ReentrantLock();
        Thread.ofPlatform().name("trylock-1").start(() -> retryTryLock(lockA, lockB)).join();
        Thread.ofPlatform().name("trylock-2").start(() -> retryTryLock(lockB, lockA)).join();
    }

    static void retryTryLock(ReentrantLock from, ReentrantLock to) {
        try {
            while (!transferTryLock(from, to)) {
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
