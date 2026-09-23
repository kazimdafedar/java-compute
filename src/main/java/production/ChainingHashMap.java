package production;

/**
 * File 04 — J01: HashMap implementation (chaining sketch)
 * Mirrors JDK structure — NOT thread-safe.
 */
final class ChainingHashMap<K, V> {

    private static final int DEFAULT_CAP = 16;
    private static final float LOAD = 0.75f;

    @SuppressWarnings("unchecked")
    private Node<K, V>[] table = (Node<K, V>[]) new Node[DEFAULT_CAP];
    private int size;
    private int threshold = (int) (DEFAULT_CAP * LOAD);

    static final class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static int spread(int hash) {
        return hash ^ (hash >>> 16);
    }

    V get(K key) {
        if (key == null) {
            return getNullKey();
        }
        int idx = (table.length - 1) & spread(key.hashCode());
        for (Node<K, V> node = table[idx]; node != null; node = node.next) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    V put(K key, V value) {
        if (key == null) {
            return putNullKey(value);
        }
        if (++size > threshold) {
            resize();
        }
        int idx = (table.length - 1) & spread(key.hashCode());
        for (Node<K, V> node = table[idx]; node != null; node = node.next) {
            if (key.equals(node.key)) {
                V old = node.value;
                node.value = value;
                return old;
            }
        }
        table[idx] = new Node<>(spread(key.hashCode()), key, value, table[idx]);
        return null;
    }

    private V getNullKey() {
        for (Node<K, V> node = table[0]; node != null; node = node.next) {
            if (node.key == null) {
                return node.value;
            }
        }
        return null;
    }

    private V putNullKey(V value) {
        for (Node<K, V> node = table[0]; node != null; node = node.next) {
            if (node.key == null) {
                V old = node.value;
                node.value = value;
                return old;
            }
        }
        if (++size > threshold) {
            resize();
        }
        table[0] = new Node<>(0, null, value, table[0]);
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] old = table;
        table = (Node<K, V>[]) new Node[old.length * 2];
        threshold = (int) (table.length * LOAD);
        size = 0;
        for (Node<K, V> head : old) {
            for (Node<K, V> node = head; node != null; node = node.next) {
                int idx = (table.length - 1) & node.hash;
                table[idx] = new Node<>(node.hash, node.key, node.value, table[idx]);
                size++;
            }
        }
    }

    void main() {
        ChainingHashMap<String, String> map = new ChainingHashMap<>();
        map.put("user:42", "payload");
        IO.println(map.get("user:42"));
    }
}
