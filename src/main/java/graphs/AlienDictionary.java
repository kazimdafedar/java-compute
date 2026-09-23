package graphs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Alien Dictionary — topological order of unique characters.
 * Compare adjacent words, then Kahn's algorithm. O(C) for all characters.
 */
class AlienDictionary {

    static String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i];
            String b = words[i + 1];
            if (a.length() > b.length() && a.startsWith(b)) {
                return "";
            }
            int len = Math.min(a.length(), b.length());
            for (int j = 0; j < len; j++) {
                if (a.charAt(j) != b.charAt(j)) {
                    if (graph.get(a.charAt(j)).add(b.charAt(j))) {
                        indegree.merge(b.charAt(j), 1, Integer::sum);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new ArrayDeque<>();
        for (var e : indegree.entrySet()) {
            if (e.getValue() == 0) {
                queue.add(e.getKey());
            }
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            order.append(c);
            for (char next : graph.get(c)) {
                if (indegree.merge(next, -1, Integer::sum) == 0) {
                    queue.add(next);
                }
            }
        }
        return order.length() == indegree.size() ? order.toString() : "";
    }

    void main() {
        IO.println(alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));   // wertf
        IO.println(alienOrder(new String[]{"z", "x"}));                           // zx
        IO.println(alienOrder(new String[]{"z", "x", "z"}));                      // "" (cycle)
    }
}
