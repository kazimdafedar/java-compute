package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * File 04 — Q102: Dijkstra Shortest Path
 * Non-negative weights — O(E log V) time.
 */
class Dijkstra {

    @SuppressWarnings("unchecked")
    static int[] dijkstra(List<int[]>[] graph, int source, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int distance = current[1];
            if (distance > dist[node]) {
                continue;
            }
            for (int[] edge : graph[node]) {
                int neighbor = edge[0];
                int weight = edge[1];
                int nextDist = distance + weight;
                if (nextDist < dist[neighbor]) {
                    dist[neighbor] = nextDist;
                    pq.add(new int[]{neighbor, nextDist});
                }
            }
        }
        return dist;
    }

    void main() {
        int n = 4;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new int[]{1, 4});
        graph[0].add(new int[]{2, 1});
        graph[2].add(new int[]{1, 2});
        graph[1].add(new int[]{3, 1});

        int[] dist = dijkstra(graph, 0, n);
        IO.println(Arrays.toString(dist));   // [0, 3, 1, 4]
    }
}
