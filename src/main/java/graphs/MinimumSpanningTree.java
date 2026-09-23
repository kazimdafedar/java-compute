package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * File 04 — Q339: Minimum Spanning Tree
 * Kruskal (Union-Find) + Prim (Priority Queue).
 */
class MinimumSpanningTree {

    static int kruskal(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int total = 0;
        int used = 0;
        for (int[] edge : edges) {
            if (dsu.union(edge[0], edge[1])) {
                total += edge[2];
                if (++used == n - 1) {
                    break;
                }
            }
        }
        return total;
    }

    @SuppressWarnings("unchecked")
    static int prim(List<int[]>[] graph, int n) {
        boolean[] inTree = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{0, 0});
        int total = 0;
        int count = 0;

        while (!pq.isEmpty() && count < n) {
            int[] current = pq.poll();
            if (inTree[current[0]]) {
                continue;
            }
            inTree[current[0]] = true;
            total += current[1];
            count++;
            for (int[] edge : graph[current[0]]) {
                if (!inTree[edge[0]]) {
                    pq.add(new int[]{edge[0], edge[1]});
                }
            }
        }
        return total;
    }

    void main() {
        int n = 4;
        int[][] edges = {{0, 1, 1}, {0, 2, 4}, {1, 2, 2}, {1, 3, 5}, {2, 3, 1}};
        IO.println("Kruskal: " + kruskal(n, edges));   // 4

        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        IO.println("Prim:    " + prim(graph, n));   // 4
    }
}
