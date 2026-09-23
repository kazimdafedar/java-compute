package matrix;

import java.util.Arrays;

class MatrixUtils {

    static void print(int[][] matrix) {
        for (int[] row : matrix) {
            IO.println(Arrays.toString(row));
        }
    }
}
