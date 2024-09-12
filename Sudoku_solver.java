class Sudoku_solver {

    public boolean isSafe(char[][] board, int row, int col, int number) {
        // Check row and column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char)(number + '0')) {
                return false;
            }
            if (board[row][i] == (char)(number + '0')) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char)(number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean helper(char[][] board, int row, int col) {
        // Base case: If we have filled all rows, the board is solved
        if (row == board.length) {
            return true;
        }

        // Move to the next cell
        int nrow = 0;
        int ncol = 0;
        if (col != board.length - 1) {
            nrow = row;
            ncol = col + 1;
        } else {
            nrow = row + 1;
            ncol = 0;
        }

        // If the current cell is not empty, move to the next one
        if (board[row][col] != '.') {
            return helper(board, nrow, ncol);
        } else {
            // Try placing numbers 1-9 in the empty cell
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0'); // Place the number

                    // Recursively try to solve the rest of the board
                    if (helper(board, nrow, ncol)) {
                        return true;
                    }

                    // If placing i didn't work, reset the cell and backtrack
                    board[row][col] = '.';
                }
            }
        }

        // Return false if no valid placement is found
        return false;
    }

    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Sample Sudoku board ('.' represents an empty cell)
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        Sudoku_solver solver = new Sudoku_solver();
        solver.solveSudoku(board);

        // Print the solved Sudoku board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
