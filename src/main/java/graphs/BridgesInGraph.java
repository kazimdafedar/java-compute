package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File 04 — Q401: Bridges in Graph
 * Tarjan low-link DFS — O(V + E) time.
 */
class BridgesInGraph {

    static List<int[]> findBridges(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] disc = new int[n];
        int[] low = new int[n];
        Arrays.fill(disc, -1);
        List<int[]> bridges = new ArrayList<>();
        int[] timer = {0};

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, -1, graph, disc, low, timer, bridges);
            }
        }
        return bridges;
    }

    static void dfs(int node, int parent, List<List<Integer>> graph,
                    int[] disc, int[] low, int[] timer, List<int[]> bridges) {
        disc[node] = low[node] = timer[0]++;
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) {
                continue;
            }
            if (disc[neighbor] == -1) {
                dfs(neighbor, node, graph, disc, low, timer, bridges);
                low[node] = Math.min(low[node], low[neighbor]);
                if (low[neighbor] > disc[node]) {
                    bridges.add(new int[]{Math.min(node, neighbor), Math.max(node, neighbor)});
                }
            } else {
                low[node] = Math.min(low[node], disc[neighbor]);
            }
        }
    }

    static String formatBridges(List<int[]> bridges) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < bridges.size(); i++) {
            int[] bridge = bridges.get(i);
            sb.append("[").append(bridge[0]).append(",").append(bridge[1]).append("]");
            if (i < bridges.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    void main() {
        List<int[]> bridges = findBridges(4, new int[][]{{0, 1}, {1, 2}, {2, 0}, {1, 3}});
        IO.println(formatBridges(bridges));   // [[1,3]]
    }
}
