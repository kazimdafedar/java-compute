package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * File 04 — Q101: Course Schedule (Topological Sort / Kahn)
 * O(V + E) time.
 */
class CourseSchedule {

    static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int seen = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            seen++;
            for (int next : graph.get(course)) {
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return seen == numCourses;
    }

    void main() {
        IO.println(canFinish(2, new int[][]{{1, 0}}));           // true
        IO.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));   // false
    }
}
