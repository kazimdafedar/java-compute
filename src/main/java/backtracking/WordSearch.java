package backtracking;

/**
 * File 04 — Q351: Word Search
 */
class WordSearch {

    static boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean dfs(char[][] board, String word, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return false;
        }
        if (board[r][c] != word.charAt(index)) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '#';
        boolean found = dfs(board, word, r + 1, c, index + 1)
                || dfs(board, word, r - 1, c, index + 1)
                || dfs(board, word, r, c + 1, index + 1)
                || dfs(board, word, r, c - 1, index + 1);
        board[r][c] = temp;
        return found;
    }

    void main() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        IO.println(exist(board, "ABCCED"));   // true
        IO.println(exist(board, "SEE"));      // true
        IO.println(exist(board, "ABCB"));     // false
    }
}
