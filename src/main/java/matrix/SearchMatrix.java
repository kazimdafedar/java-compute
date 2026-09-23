package matrix;

/**
 * File 04 — Q427: Search in Row-Column Sorted Matrix
 * Staircase from top-right corner — O(m+n) time, O(1) space.
 */
class SearchMatrix {

    static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    void main() {
        int[][] matrix = {
                {1, 4, 7, 11},
                {2, 5, 8, 12},
                {3, 6, 9, 16}
        };
        IO.println(searchMatrix(matrix, 5));   // true
        IO.println(searchMatrix(matrix, 13));  // false
    }
}
