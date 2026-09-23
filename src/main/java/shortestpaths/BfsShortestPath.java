package shortestpaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * File 04 — Q340: BFS Shortest Path (Unweighted)
 * Distance + parent reconstruction — O(V + E) time.
 */
class BfsShortestPath {

    static List<Integer> shortestPath(List<List<Integer>> graph, int src, int dst, int n) {
        int[] dist = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dist, -1);
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        dist[src] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    parent[neighbor] = node;
                    queue.add(neighbor);
                }
            }
        }

        if (dist[dst] == -1) {
            return List.of();
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (int at = dst; at != -1; at = parent[at]) {
            path.addFirst(at);
        }
        return path;
    }

    void main() {
        int n = 4;
        List<List<Integer>> graph = List.of(
            List.of(1, 2),
            List.of(3),
            List.of(3),
            List.of()
        );
        IO.println(shortestPath(graph, 0, 3, n));   // [0, 1, 3] or [0, 2, 3]
    }
}
