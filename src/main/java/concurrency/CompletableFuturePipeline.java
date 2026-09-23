package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * File 02 — Q44: Runnable vs Callable; Future vs CompletableFuture.
 * Compose a payment pipeline: authorize → ledger → notify.
 */
class CompletableFuturePipeline {

    record Auth(String paymentId) {}
    record Ledger(String paymentId, String entryId) {}

    static Auth authorize(String paymentId) {
        sleep(50);
        return new Auth(paymentId);
    }

    static Ledger post(Auth auth) {
        sleep(50);
        return new Ledger(auth.paymentId(), "LED-" + auth.paymentId());
    }

    static String notifyUser(Ledger ledger) {
        return "notified " + ledger.entryId();
    }

    static CompletableFuture<String> paymentPipeline(String paymentId, ExecutorService ioPool) {
        return CompletableFuture.supplyAsync(() -> authorize(paymentId), ioPool)
                .thenCompose(auth -> CompletableFuture.supplyAsync(() -> post(auth), ioPool))
                .thenApply(CompletableFuturePipeline::notifyUser)
                .exceptionally(ex -> {
                    IO.println("pipeline failed: " + ex.getMessage());
                    return "failed";
                });
    }

    void main() {
        try (ExecutorService ioPool = Executors.newFixedThreadPool(4)) {
            String result = paymentPipeline("pay-42", ioPool).join();
            IO.println(result);   // notified LED-pay-42

            CompletableFuture<Integer> left = CompletableFuture.supplyAsync(() -> 2, ioPool);
            CompletableFuture<Integer> right = CompletableFuture.supplyAsync(() -> 3, ioPool);
            int sum = left.thenCombine(right, Integer::sum).join();
            IO.println("thenCombine: " + sum);   // 5
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
