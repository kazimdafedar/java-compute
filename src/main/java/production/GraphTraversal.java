package production;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File 04 — J12: Graph traversal (BFS + DFS cycle detection)
 */
final class GraphTraversal {

    private final Map<Integer, List<Integer>> adj = new HashMap<>();

    void addEdge(int u, int v, boolean undirected) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        if (undirected) {
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
    }

    Map<Integer, Integer> bfs(int start) {
        Map<Integer, Integer> dist = new HashMap<>();
        Deque<Integer> queue = new ArrayDeque<>();
        dist.put(start, 0);
        queue.add(start);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.getOrDefault(u, List.of())) {
                if (!dist.containsKey(v)) {
                    dist.put(v, dist.get(u) + 1);
                    queue.add(v);
                }
            }
        }
        return dist;
    }

    enum Color { WHITE, GRAY, BLACK }

    boolean hasCycleDirected() {
        Map<Integer, Color> color = new HashMap<>();
        for (int u : adj.keySet()) {
            if (color.getOrDefault(u, Color.WHITE) == Color.WHITE && dfsCycle(u, color)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsCycle(int u, Map<Integer, Color> color) {
        color.put(u, Color.GRAY);
        for (int v : adj.getOrDefault(u, List.of())) {
            Color c = color.getOrDefault(v, Color.WHITE);
            if (c == Color.GRAY) {
                return true;
            }
            if (c == Color.WHITE && dfsCycle(v, color)) {
                return true;
            }
        }
        color.put(u, Color.BLACK);
        return false;
    }

    void main() {
        GraphTraversal graph = new GraphTraversal();
        graph.addEdge(0, 1, false);
        graph.addEdge(1, 2, false);
        graph.addEdge(2, 0, false);
        IO.println("has cycle: " + graph.hasCycleDirected());   // true

        GraphTraversal acyclic = new GraphTraversal();
        acyclic.addEdge(0, 1, false);
        acyclic.addEdge(1, 2, false);
        IO.println(acyclic.bfs(0));   // {0=0, 1=1, 2=2}
    }
}
