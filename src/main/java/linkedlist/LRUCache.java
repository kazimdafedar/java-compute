package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q92: LRU Cache
 * HashMap + doubly linked list — O(1) get/put.
 */
class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        insertAtHead(node);
        return node.value;
    }

    void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        Node node = new Node(key, value);
        insertAtHead(node);
        map.put(key, node);
        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    void main() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        IO.println(cache.get(1));    // 1
        cache.put(3, 3);             // evicts key 2
        IO.println(cache.get(2));    // -1
        cache.put(4, 4);             // evicts key 1
        IO.println(cache.get(1));    // -1
        IO.println(cache.get(3));    // 3
        IO.println(cache.get(4));    // 4
    }
}
