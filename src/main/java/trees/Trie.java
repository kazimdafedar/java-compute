package trees;

/**
 * File 04 — Q331: Trie (Prefix Tree)
 */
class Trie {

    private final Trie[] children = new Trie[26];
    private boolean isWord;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }

    boolean search(String word) {
        Trie node = find(word);
        return node != null && node.isWord;
    }

    boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private Trie find(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    void main() {
        Trie trie = new Trie();
        trie.insert("apple");
        IO.println(trie.search("apple"));      // true
        IO.println(trie.search("app"));        // false
        IO.println(trie.startsWith("app"));    // true
        trie.insert("app");
        IO.println(trie.search("app"));        // true
    }
}
