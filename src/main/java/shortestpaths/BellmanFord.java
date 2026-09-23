package shortestpaths;

import java.util.Arrays;

/**
 * File 04 — Q342: Bellman-Ford
 * Negative edges + negative cycle detection — O(V * E) time.
 */
class BellmanFord {

    static int[] bellmanFord(int n, int[][] edges, int source) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (dist[u] != Long.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (dist[u] != Long.MAX_VALUE && dist[u] + w < dist[v]) {
                throw new IllegalStateException("negative cycle");
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = dist[i] == Long.MAX_VALUE ? Integer.MAX_VALUE : (int) dist[i];
        }
        return result;
    }

    void main() {
        int[][] edges = {{0, 1, 5}, {1, 2, -3}, {2, 0, -2}};
        IO.println(Arrays.toString(bellmanFord(3, edges, 0)));   // [0, 5, 2]
    }
}
