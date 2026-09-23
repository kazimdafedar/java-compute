package graphs;

/**
 * File 04 — Q100: Number of Islands
 * DFS flood fill — O(m * n) time.
 */
class NumberOfIslands {

    static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    sink(grid, i, j);
                }
            }
        }
        return count;
    }

    static void sink(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != '1') {
            return;
        }
        grid[row][col] = '0';
        sink(grid, row + 1, col);
        sink(grid, row - 1, col);
        sink(grid, row, col + 1);
        sink(grid, row, col - 1);
    }

    void main() {
        char[][] grid = {
            {'1', '1', '0'},
            {'0', '1', '0'},
            {'1', '0', '1'}
        };
        IO.println(numIslands(grid));   // 3
    }
}
