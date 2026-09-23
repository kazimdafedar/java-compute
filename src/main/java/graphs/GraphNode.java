package graphs;

import java.util.ArrayList;
import java.util.List;

/** Node for clone-graph problems. */
class GraphNode {
    int val;
    List<GraphNode> neighbors = new ArrayList<>();

    GraphNode(int val) {
        this.val = val;
    }
}
