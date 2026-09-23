package shortestpaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * File 04 — Q344: DAG Shortest Path
 * Relax in topological order — O(V + E) time.
 */
class DagShortestPath {

    static int[] topologicalSort(int n, List<List<int[]>> graph) {
        int[] indegree = new int[n];
        for (List<int[]> edges : graph) {
            for (int[] edge : edges) {
                indegree[edge[0]]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int[] order = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[index++] = node;
            for (int[] edge : graph.get(node)) {
                if (--indegree[edge[0]] == 0) {
                    queue.add(edge[0]);
                }
            }
        }
        return order;
    }

    static int[] dagShortest(List<List<int[]>> graph, int n, int source) {
        int[] order = topologicalSort(n, graph);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int node : order) {
            if (dist[node] == Integer.MAX_VALUE) {
                continue;
            }
            for (int[] edge : graph.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];
                if (dist[node] + weight < dist[neighbor]) {
                    dist[neighbor] = dist[node] + weight;
                }
            }
        }
        return dist;
    }

    void main() {
        int n = 4;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new int[]{1, 5});
        graph.get(0).add(new int[]{2, 3});
        graph.get(1).add(new int[]{3, 2});
        graph.get(2).add(new int[]{3, 7});

        IO.println(Arrays.toString(dagShortest(graph, n, 0)));   // [0, 5, 3, 7]
    }
}
