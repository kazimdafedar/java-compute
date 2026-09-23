package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * File 04 — Q337: Rotting Oranges
 * Multi-source BFS — O(m * n) time.
 */
class RottingOranges {

    static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int minutes = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty() && fresh > 0) {
            minutes++;
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cell = queue.poll();
                for (int[] dir : directions) {
                    int x = cell[0] + dir[0];
                    int y = cell[1] + dir[1];
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] != 1) {
                        continue;
                    }
                    grid[x][y] = 2;
                    fresh--;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }

    void main() {
        int[][] grid = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        IO.println(orangesRotting(grid));   // 4
    }
}
