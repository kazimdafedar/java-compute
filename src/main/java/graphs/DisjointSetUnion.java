package graphs;

/**
 * File 04 — Q103: Union-Find (DSU)
 * Path compression + union by rank — ~O(α(n)) per operation.
 */
class DisjointSetUnion {

    private final int[] parent;
    private final int[] rank;

    DisjointSetUnion(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        if (rank[rootA] < rank[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }
        parent[rootB] = rootA;
        if (rank[rootA] == rank[rootB]) {
            rank[rootA]++;
        }
        return true;
    }

    void main() {
        DisjointSetUnion dsu = new DisjointSetUnion(5);
        dsu.union(0, 1);
        dsu.union(1, 2);
        IO.println(dsu.find(0) == dsu.find(2));   // true
        dsu.union(3, 4);
        IO.println(dsu.find(0) == dsu.find(4));   // false
    }
}
