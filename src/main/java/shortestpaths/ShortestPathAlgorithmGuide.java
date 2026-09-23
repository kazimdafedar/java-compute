package shortestpaths;

/**
 * File 04 — Q346: Which Shortest-Path Algorithm?
 * Interview cheat sheet — when to use each algorithm.
 */
class ShortestPathAlgorithmGuide {

    static String recommend(String scenario) {
        return switch (scenario.toLowerCase()) {
            case "unweighted" -> "BFS — O(V+E) — Q340";
            case "weights01" -> "0-1 BFS (deque) — O(V+E) — Q341";
            case "nonnegative" -> "Dijkstra — O(E log V) — Q102";
            case "negative" -> "Bellman-Ford — O(V*E) — Q342";
            case "dag" -> "Topological relaxation — O(V+E) — Q344";
            case "allpairs" -> "Floyd-Warshall — O(V³) — Q343";
            case "hoplimit" -> "Bounded Bellman-Ford — O(E*K) — Q345";
            default -> "Clarify: weighted? negative edges? all-pairs? DAG? hop limit?";
        };
    }

    void main() {
        IO.println("=== Shortest Path Algorithm Cheat Sheet ===");
        IO.println("| Scenario | Algorithm |");
        IO.println("| Unweighted | BFS (Q340) |");
        IO.println("| Weights {0,1} | 0-1 BFS (Q341) |");
        IO.println("| Non-negative | Dijkstra (Q102) |");
        IO.println("| Negative edges | Bellman-Ford (Q342) |");
        IO.println("| DAG | Topo relax (Q344) |");
        IO.println("| All pairs, small V | Floyd-Warshall (Q343) |");
        IO.println("| At most K stops | Bounded BF (Q345) |");
        IO.println();
        IO.println("unweighted -> " + recommend("unweighted"));
        IO.println("negative   -> " + recommend("negative"));
        IO.println("hoplimit   -> " + recommend("hoplimit"));
    }
}
