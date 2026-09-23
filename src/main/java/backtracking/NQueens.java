package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File 04 — Q352: N-Queens
 */
class NQueens {

    static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        backtrack(board, 0, result);
        return result;
    }

    static void backtrack(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            result.add(buildBoard(board));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (!isSafe(board, row, col)) {
                continue;
            }
            board[row][col] = 'Q';
            backtrack(board, row + 1, result);
            board[row][col] = '.';
        }
    }

    static boolean isSafe(char[][] board, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
            int left = col - (row - r);
            if (left >= 0 && board[r][left] == 'Q') {
                return false;
            }
            int right = col + (row - r);
            if (right < board.length && board[r][right] == 'Q') {
                return false;
            }
        }
        return true;
    }

    static List<String> buildBoard(char[][] board) {
        List<String> rows = new ArrayList<>();
        for (char[] row : board) {
            rows.add(new String(row));
        }
        return rows;
    }

    void main() {
        IO.println(solveNQueens(4));   // 2 solutions
    }
}
