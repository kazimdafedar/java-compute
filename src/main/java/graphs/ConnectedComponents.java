package graphs;

/**
 * File 04 — Q336: Number of Connected Components
 * Union-Find — O(V + E * α) time.
 */
class ConnectedComponents {

    static int countComponents(int n, int[][] edges) {
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int components = n;
        for (int[] edge : edges) {
            if (dsu.union(edge[0], edge[1])) {
                components--;
            }
        }
        return components;
    }

    void main() {
        IO.println(countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));   // 2
        IO.println(countComponents(5, new int[][]{{0, 1}, {2, 3}, {3, 4}}));   // 2
    }
}
