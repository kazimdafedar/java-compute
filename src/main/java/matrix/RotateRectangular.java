package matrix;

/**
 * File 04 — Q364: Rotate Rectangular Matrix 90° Clockwise
 * Returns new matrix (rows != cols).
 */
class RotateRectangular {

    static int[][] rotate90Clockwise(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotated = new int[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = matrix[r][c];
            }
        }
        return rotated;
    }

    void main() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        MatrixUtils.print(rotate90Clockwise(matrix));
        // [4,1]
        // [5,2]
        // [6,3]
    }
}
