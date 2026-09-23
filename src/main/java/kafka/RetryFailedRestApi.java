package kafka;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * File 07 — Q156: Retry a failed REST call — timeout, backoff + jitter, cap.
 * Retry only idempotent GETs (or POSTs guarded by an Idempotency-Key).
 */
class RetryFailedRestApi {

    static <T> T retry(int maxAttempts, long baseDelayMs, Supplier<T> call) {
        Exception last = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return call.get();
            } catch (RuntimeException e) {
                last = e;
                if (attempt == maxAttempts) {
                    break;
                }
                long jitter = ThreadLocalRandom.current().nextLong(0, baseDelayMs);
                sleep(baseDelayMs * attempt + jitter);
            }
        }
        throw new IllegalStateException("exhausted retries", last);
    }

    static String flakyGet(int[] remainingFailures) {
        if (remainingFailures[0] > 0) {
            remainingFailures[0]--;
            throw new RuntimeException("502 Bad Gateway");
        }
        return "{\"status\":\"ok\"}";
    }

    void main() {
        int[] failures = {2};
        String body = retry(4, 20, () -> flakyGet(failures));
        IO.println(body);

        try {
            retry(2, 10, () -> {
                throw new RuntimeException("503");
            });
        } catch (IllegalStateException e) {
            IO.println("gave up: " + e.getCause().getMessage());
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
