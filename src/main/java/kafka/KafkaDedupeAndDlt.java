package kafka;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File 07 — Q157 / Q167: Kafka consumer dedupe, bounded retries, dead-letter topic.
 * Effectively-once = at-least-once delivery + idempotent processing.
 * Poison messages go to DLT after N retries — never infinite-retry in the poll loop.
 */
class KafkaDedupeAndDlt {

    record Message(String id, String payload, boolean poison) {}

    static final class Consumer {
        private final Set<String> processed = ConcurrentHashMap.newKeySet();
        private final Queue<Message> retry = new ArrayDeque<>();
        private final List<Message> dlt = new ArrayList<>();
        private final int maxAttempts;

        Consumer(int maxAttempts) {
            this.maxAttempts = maxAttempts;
        }

        void consume(Message message) {
            if (!processed.add(message.id())) {
                IO.println("skip duplicate " + message.id());
                return;
            }
            tryProcess(message, 1);
        }

        private void tryProcess(Message message, int attempt) {
            try {
                process(message);
                IO.println("processed " + message.id());
            } catch (IllegalArgumentException e) {
                if (attempt >= maxAttempts) {
                    processed.remove(message.id());
                    dlt.add(message);
                    IO.println("DLT " + message.id() + " after " + attempt + " attempts");
                    return;
                }
                processed.remove(message.id());
                retry.add(message);
                IO.println("retry-" + attempt + " " + message.id());
                tryProcess(message, attempt + 1);
            }
        }

        List<Message> deadLetters() {
            return List.copyOf(dlt);
        }
    }

    static void process(Message message) {
        if (message.poison()) {
            throw new IllegalArgumentException("bad schema");
        }
    }

    void main() {
        Consumer consumer = new Consumer(3);
        consumer.consume(new Message("ord-1", "{\"ok\":true}", false));
        consumer.consume(new Message("ord-1", "{\"ok\":true}", false));   // duplicate
        consumer.consume(new Message("ord-2", "not-json", true));         // poison → DLT
        IO.println("DLT size: " + consumer.deadLetters().size());
    }
}
