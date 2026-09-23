package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * File 04 — Q334: Detect Cycle (Directed + Undirected)
 * DFS colors / Union-Find — O(V + E) time.
 */
class DetectCycle {

    private int[] color;

    static boolean hasCycleDirected(List<List<Integer>> graph, int n) {
        DetectCycle detector = new DetectCycle();
        detector.color = new int[n];
        for (int i = 0; i < n; i++) {
            if (detector.color[i] == 0 && detector.dfsDirected(graph, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsDirected(List<List<Integer>> graph, int node) {
        color[node] = 1;
        for (int neighbor : graph.get(node)) {
            if (color[neighbor] == 1) {
                return true;
            }
            if (color[neighbor] == 0 && dfsDirected(graph, neighbor)) {
                return true;
            }
        }
        color[node] = 2;
        return false;
    }

    static boolean hasCycleUndirected(int n, int[][] edges) {
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return true;
            }
        }
        return false;
    }

    void main() {
        int n = 3;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(0);
        IO.println("Directed cycle: " + hasCycleDirected(graph, n));   // true

        IO.println("Undirected cycle: " + hasCycleUndirected(3, new int[][]{{0, 1}, {1, 2}}));   // false
        IO.println("Undirected cycle: " + hasCycleUndirected(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}));   // true
    }
}
