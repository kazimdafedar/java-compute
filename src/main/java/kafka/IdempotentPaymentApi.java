package kafka;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File 07 — Q155 / Q157: Idempotent payment API + effectively-once processing.
 * Client Idempotency-Key → store (key, requestHash, result). Same key + same body
 * returns the stored result; same key + different body → conflict.
 */
class IdempotentPaymentApi {

    record PayRequest(String accountId, int amount) {}
    record PaymentResult(String paymentId, String status, int amount) {}
    record IdempotencyRecord(String requestHash, PaymentResult result) {}

    static final class IdempotencyStore {
        private final Map<String, IdempotencyRecord> records = new ConcurrentHashMap<>();

        PaymentResult pay(String key, PayRequest request) {
            String hash = hash(request);
            IdempotencyRecord prior = records.get(key);
            if (prior != null) {
                if (!prior.requestHash().equals(hash)) {
                    throw new IllegalStateException("409 Idempotency-Key reused with a different body");
                }
                return prior.result();
            }
            PaymentResult result = charge(request);
            IdempotencyRecord created = new IdempotencyRecord(hash, result);
            IdempotencyRecord raced = records.putIfAbsent(key, created);
            return raced == null ? result : raced.result();
        }
    }

    /** Consumer-side dedupe: INSERT ON CONFLICT DO NOTHING. */
    static final class ProcessedMessages {
        private final Map<String, String> processed = new ConcurrentHashMap<>();

        boolean claim(String msgId, String result) {
            return processed.putIfAbsent(msgId, result) == null;
        }
    }

    static PaymentResult charge(PayRequest request) {
        return new PaymentResult("pay-" + request.accountId(), "CHARGED", request.amount());
    }

    static String hash(PayRequest request) {
        return Integer.toHexString(Objects.hash(request.accountId(), request.amount()));
    }

    void main() {
        IdempotencyStore store = new IdempotencyStore();
        PayRequest first = new PayRequest("acc-1", 100);
        PaymentResult a = store.pay("key-1", first);
        PaymentResult b = store.pay("key-1", first);
        IO.println(a + " same retry? " + (a == b));

        try {
            store.pay("key-1", new PayRequest("acc-1", 999));
        } catch (IllegalStateException e) {
            IO.println(e.getMessage());
        }

        ProcessedMessages dedupe = new ProcessedMessages();
        IO.println("first delivery:  " + dedupe.claim("msg-9", "ok"));
        IO.println("duplicate:       " + dedupe.claim("msg-9", "ok"));
    }
}
