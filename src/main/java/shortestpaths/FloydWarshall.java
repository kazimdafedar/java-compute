package shortestpaths;

/**
 * File 04 — Q343: Floyd-Warshall
 * All-pairs shortest paths — O(V³) time, O(V²) space.
 */
class FloydWarshall {

    static final int INF = 1_000_000_000;

    static void floydWarshall(int[][] dist, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF
                        && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    static void printMatrix(int[][] dist) {
        for (int[] row : dist) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i] == INF ? "INF" : row[i]);
                if (i < row.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            IO.println(sb.toString());
        }
    }

    void main() {
        int n = 3;
        int[][] dist = {
            {0, 5, INF},
            {INF, 0, 2},
            {INF, INF, 0}
        };
        floydWarshall(dist, n);
        printMatrix(dist);
        // [0, 5, 7]
        // [INF, 0, 2]
        // [INF, INF, 0]
    }
}
