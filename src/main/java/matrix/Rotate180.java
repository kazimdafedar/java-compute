package matrix;

/**
 * File 04 — Q363: Rotate Matrix 180° (in-place)
 */
class Rotate180 {

    static void rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < cols; j++) {
                swap(matrix, i, j, rows - 1 - i, cols - 1 - j);
            }
        }
        if (rows % 2 == 1) {
            int mid = rows / 2;
            for (int j = 0; j < cols / 2; j++) {
                swap(matrix, mid, j, mid, cols - 1 - j);
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
        // [9,8,7]
        // [6,5,4]
        // [3,2,1]
    }
}
