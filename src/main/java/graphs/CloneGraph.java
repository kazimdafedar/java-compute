package graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q332: Clone Graph
 * DFS + visited map — O(V + E) time.
 */
class CloneGraph {

    static GraphNode cloneGraph(GraphNode node) {
        Map<GraphNode, GraphNode> seen = new HashMap<>();
        return clone(node, seen);
    }

    static GraphNode clone(GraphNode node, Map<GraphNode, GraphNode> seen) {
        if (node == null) {
            return null;
        }
        if (seen.containsKey(node)) {
            return seen.get(node);
        }
        GraphNode copy = new GraphNode(node.val);
        seen.put(node, copy);
        for (GraphNode neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, seen));
        }
        return copy;
    }

    void main() {
        GraphNode one = new GraphNode(1);
        GraphNode two = new GraphNode(2);
        GraphNode three = new GraphNode(3);
        GraphNode four = new GraphNode(4);
        one.neighbors.add(two);
        one.neighbors.add(four);
        two.neighbors.add(one);
        two.neighbors.add(three);
        three.neighbors.add(two);
        three.neighbors.add(four);
        four.neighbors.add(one);
        four.neighbors.add(three);

        GraphNode cloned = cloneGraph(one);
        IO.println(cloned.val);                    // 1
        IO.println(cloned.neighbors.size());       // 2
        IO.println(cloned != one);                 // true — deep copy
        IO.println(cloned.neighbors.get(0) != two); // true
    }
}
