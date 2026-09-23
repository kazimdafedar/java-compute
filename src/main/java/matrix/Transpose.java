package matrix;

/**
 * File 04 — Q426: Transpose Matrix
 */
class Transpose {

    static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result[c][r] = matrix[r][c];
            }
        }
        return result;
    }

    void main() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        MatrixUtils.print(transpose(matrix));
        // [1,4]
        // [2,5]
        // [3,6]
    }
}
