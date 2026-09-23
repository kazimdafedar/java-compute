package production;

/**
 * File 04 — J04: Token Bucket Rate Limiter
 * Thread-safe for single JVM via synchronized.
 */
final class TokenBucketRateLimiter {

    private final double capacity;
    private final double refillPerMs;
    private double tokens;
    private long lastRefillNanos;

    TokenBucketRateLimiter(double capacity, double refillPerSecond) {
        this.capacity = capacity;
        this.refillPerMs = refillPerSecond / 1_000.0;
        this.tokens = capacity;
        this.lastRefillNanos = System.nanoTime();
    }

    synchronized boolean tryAcquire() {
        refill();
        if (tokens >= 1.0) {
            tokens -= 1.0;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double elapsedMs = (now - lastRefillNanos) / 1_000_000.0;
        tokens = Math.min(capacity, tokens + elapsedMs * refillPerMs);
        lastRefillNanos = now;
    }

    void main() {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(10, 5);
        int allowed = 0;
        for (int i = 0; i < 12; i++) {
            if (limiter.tryAcquire()) {
                allowed++;
            }
        }
        IO.println("Allowed: " + allowed);   // 10
    }
}
