package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * File 04 — Q333: Course Schedule II
 * Return topological order — O(V + E) time.
 */
class CourseScheduleII {

    static int[] findOrder(int numCourses, int[][] prerequisites) {
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

        int[] order = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[index++] = course;
            for (int next : graph.get(course)) {
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return index == numCourses ? order : new int[0];
    }

    void main() {
        int[] order = findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        IO.println(java.util.Arrays.toString(order));   // [0, 1, 2, 3] (valid order)
    }
}
