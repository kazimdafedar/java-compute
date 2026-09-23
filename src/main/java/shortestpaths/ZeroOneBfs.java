package shortestpaths;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * File 04 — Q341: 0-1 BFS
 * Deque for edges weighted 0 or 1 — O(V + E) time.
 */
class ZeroOneBfs {

    @SuppressWarnings("unchecked")
    static int[] zeroOneBfs(List<int[]>[] graph, int source, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(source);

        while (!deque.isEmpty()) {
            int node = deque.pollFirst();
            for (int[] edge : graph[node]) {
                int neighbor = edge[0];
                int weight = edge[1];
                int nextDist = dist[node] + weight;
                if (nextDist < dist[neighbor]) {
                    dist[neighbor] = nextDist;
                    if (weight == 0) {
                        deque.addFirst(neighbor);
                    } else {
                        deque.addLast(neighbor);
                    }
                }
            }
        }
        return dist;
    }

    void main() {
        int n = 3;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new int[]{1, 0});
        graph[1].add(new int[]{2, 1});
        graph[0].add(new int[]{2, 1});

        IO.println(Arrays.toString(zeroOneBfs(graph, 0, n)));   // [0, 0, 1]
    }
}
