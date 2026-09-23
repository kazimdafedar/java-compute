package matrix;

/**
 * File 04 — Q362: Rotate Matrix 90° Counter-Clockwise (in-place, square)
 */
class Rotate90CounterClockwise {

    static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n / 2; i++) {
                swap(matrix, i, j, n - 1 - i, j);
            }
        }
    }

    static void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }

    void main() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(matrix);
        MatrixUtils.print(matrix);
        // [3,6,9]
        // [2,5,8]
        // [1,4,7]
    }
}
