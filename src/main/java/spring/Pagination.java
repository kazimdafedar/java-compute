package spring;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * File 05 / File 06 Q442: Spring Data Pageable vs keyset (seek) pagination.
 * OFFSET is simple but slow and unstable on deep pages.
 * Keyset uses the last row's sort key — O(page size) at any depth.
 */
class Pagination {

    record Order(long id, Instant createdAt, String customer) {}

    record PageRequest(int page, int size) {
        int offset() {
            return page * size;
        }
    }

    record Page<T>(List<T> content, int page, int size, long totalElements) {
        int totalPages() {
            return size == 0 ? 0 : (int) Math.ceil(totalElements / (double) size);
        }

        boolean hasNext() {
            return page + 1 < totalPages();
        }
    }

    record SeekCursor(Instant createdAt, long id) {}

    /** Spring-style: repo.findAll(PageRequest.of(page, size)). */
    static Page<Order> offsetPage(List<Order> all, PageRequest request) {
        List<Order> sorted = sorted(all);
        int from = Math.min(request.offset(), sorted.size());
        int to = Math.min(from + request.size(), sorted.size());
        return new Page<>(sorted.subList(from, to), request.page(), request.size(), sorted.size());
    }

    /** Keyset: WHERE (created_at, id) < (:last) ORDER BY created_at DESC, id DESC LIMIT n */
    static List<Order> keysetPage(List<Order> all, SeekCursor after, int size) {
        return sorted(all).stream()
                .filter(order -> after == null || before(order, after))
                .limit(size)
                .toList();
    }

    static boolean before(Order order, SeekCursor cursor) {
        int byTime = order.createdAt().compareTo(cursor.createdAt());
        if (byTime != 0) {
            return byTime < 0;
        }
        return order.id() < cursor.id();
    }

    static List<Order> sorted(List<Order> all) {
        return all.stream()
                .sorted(Comparator.comparing(Order::createdAt).reversed()
                        .thenComparing(Comparator.comparingLong(Order::id).reversed()))
                .toList();
    }

    void main() {
        Instant now = Instant.parse("2026-09-07T10:00:00Z");
        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            orders.add(new Order(i, now.plusSeconds(i), "c" + i));
        }

        Page<Order> page0 = offsetPage(orders, new PageRequest(0, 3));
        IO.println("offset page 0: " + page0.content().stream().map(Order::id).toList()
                + " hasNext=" + page0.hasNext());

        Page<Order> page1 = offsetPage(orders, new PageRequest(1, 3));
        IO.println("offset page 1: " + page1.content().stream().map(Order::id).toList());

        List<Order> first = keysetPage(orders, null, 3);
        SeekCursor cursor = new SeekCursor(first.getLast().createdAt(), first.getLast().id());
        List<Order> next = keysetPage(orders, cursor, 3);
        IO.println("keyset first:  " + first.stream().map(Order::id).toList());
        IO.println("keyset next:   " + next.stream().map(Order::id).toList());
    }
}
