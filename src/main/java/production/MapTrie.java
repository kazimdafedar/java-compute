package production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * File 04 — J11: Map-based Trie (prefix tree)
 */
final class MapTrie {

    private final Map<Character, MapTrie> children = new HashMap<>();
    private boolean isWord;

    void insert(String word) {
        Objects.requireNonNull(word, "word");
        MapTrie node = this;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new MapTrie());
        }
        node.isWord = true;
    }

    boolean search(String word) {
        MapTrie node = findNode(word);
        return node != null && node.isWord;
    }

    boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    List<String> suggest(String prefix, int limit) {
        MapTrie node = findNode(prefix);
        if (node == null) {
            return List.of();
        }
        List<String> results = new ArrayList<>();
        node.dfs(new StringBuilder(prefix), results, limit);
        return results;
    }

    private MapTrie findNode(String text) {
        MapTrie node = this;
        for (char c : text.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    private void dfs(StringBuilder path, List<String> out, int limit) {
        if (out.size() >= limit) {
            return;
        }
        if (isWord) {
            out.add(path.toString());
        }
        for (var entry : children.entrySet()) {
            path.append(entry.getKey());
            entry.getValue().dfs(path, out, limit);
            path.deleteCharAt(path.length() - 1);
        }
    }

    void main() {
        MapTrie trie = new MapTrie();
        trie.insert("java");
        trie.insert("javascript");
        trie.insert("jar");
        IO.println(trie.suggest("ja", 2));   // [java, javascript]
    }
}
