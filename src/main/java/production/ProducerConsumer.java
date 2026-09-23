package production;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * File 04 — J06: Producer/Consumer with BlockingQueue
 */
final class ProducerConsumer<T> implements AutoCloseable {

    private final BlockingQueue<T> queue;
    private final ExecutorService consumers;
    private volatile boolean running = true;

    ProducerConsumer(int capacity, int consumerThreads, Consumer<T> handler) {
        this.queue = new ArrayBlockingQueue<>(capacity);
        this.consumers = Executors.newFixedThreadPool(consumerThreads);
        for (int i = 0; i < consumerThreads; i++) {
            consumers.submit(() -> consumeLoop(handler));
        }
    }

    void produce(T item) throws InterruptedException {
        if (!running) {
            throw new IllegalStateException("shut down");
        }
        queue.put(item);
    }

    private void consumeLoop(Consumer<T> handler) {
        try {
            while (running || !queue.isEmpty()) {
                T item = queue.poll(100, TimeUnit.MILLISECONDS);
                if (item != null) {
                    handler.accept(item);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        running = false;
        consumers.shutdown();
        try {
            consumers.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            consumers.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    void main() throws InterruptedException {
        try (ProducerConsumer<String> pc = new ProducerConsumer<>(10, 2, IO::println)) {
            for (int i = 0; i < 5; i++) {
                pc.produce("task-" + i);
            }
            Thread.sleep(500);
        }
    }
}
