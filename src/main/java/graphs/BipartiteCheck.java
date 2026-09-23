package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * File 04 — Q335: Bipartite Check
 * BFS 2-coloring — O(V + E) time.
 */
class BipartiteCheck {

    static boolean isBipartite(List<List<Integer>> graph, int n) {
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int start = 0; start < n; start++) {
            if (color[start] != -1) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            color[start] = 0;

            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : graph.get(node)) {
                    if (color[neighbor] == -1) {
                        color[neighbor] = color[node] ^ 1;
                        queue.add(neighbor);
                    } else if (color[neighbor] == color[node]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void main() {
        List<List<Integer>> evenCycle = List.of(
            List.of(1, 3),
            List.of(0, 2),
            List.of(1, 3),
            List.of(0, 2)
        );
        IO.println(isBipartite(evenCycle, 4));   // true

        List<List<Integer>> oddCycle = List.of(
            List.of(1),
            List.of(0, 2),
            List.of(1)
        );
        IO.println(isBipartite(oddCycle, 3));   // false
    }
}
