/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/12/04 
 * 
 * Lab 2: Four Queens
 * Pseudocode/Algorithm: 
 *      - Start at 0,0
 *      - If all queens are placed return true
 *          - Try all rows in the current column. Do the following for every row.
 *             - If the queen can be placed safely in this row
 *             - Then mark this [row, column] as part of the solution and recursively check if placing queen here leads to a solution.
 *          - If placing the queen in [row, column] leads to a solution then return true.
 *         - If placing queen doesn’t lead to a solution then unmark this [row, column] then backtrack and try other rows.
 *
 * Code: 
 * No input necessary
 * Output: 
 * 
* A+(B*C) -> ABC*+
*Conclusion: 
* The algorithm works as expected.
 */
/**
 * The FourQueens class represents a solution to the Four Queens problem.
 * It finds a valid arrangement of four queens on a 4x4 chessboard such that no two queens threaten each other.
 */

 //import stack
import java.util.ArrayList;
import java.util.Stack;

public class FourQueens {

    private int NumofQueens = 4;
    private int[] leftDiagnol = new int[2 * NumofQueens - 1];
    private int[] rightDiagnol = new int[2 * NumofQueens - 1];
    private int[] column = new int[NumofQueens];
    private int[][] board = new int[NumofQueens][NumofQueens]; // Board 4x4
    private ArrayList<int[][]> stack = new ArrayList<int[][]>();

    /**
     * Prints the current arrangement of queens on the chessboard.
     */
    private void printBoard() {
        for (int i = 0; i < NumofQueens; i++) {
            for (int j = 0; j < NumofQueens; j++)
                System.out.printf(" %d ", board[i][j]);
            System.out.println();
        }
    }

    /**
     * Resets the board by setting all elements to 0.
     */
    private void resetBoard() {
        for (int i = 0; i < NumofQueens; i++) {
            for (int j = 0; j < NumofQueens; j++)
                board[i][j] = 0;
        }
    }

    /**
     * Recursively finds a valid solution to the Four Queens problem.
     *
     * @param col The current column being considered for queen placement.
     * @return true if a valid solution is found, false otherwise.
     */
    private boolean findSolution(int col) {
        if (col >= NumofQueens) { // Base case: All queens are placed
            printBoard();
            System.out.println("Solution Found"); 
            stack.add(deepCopyBoard());
            return true;
        }

        boolean foundSolution = false;
        for (int i = 0; i < NumofQueens; i++) {
            if (leftDiagnol[i - col + NumofQueens - 1] != 1 && rightDiagnol[i + col] != 1 && column[i] != 1) {
                board[i][col] = 1; // Place this queen in board[i][col]
                leftDiagnol[i - col + NumofQueens - 1] = rightDiagnol[i + col] = column[i] = 1; // Mark this [row][col] and diagonals as part of solution

                if (findSolution(col + 1) )
                    return true;

                // BACKTRACK
                board[i][col] = 0; // Remove queen from board[i][col]
                leftDiagnol[i - col + NumofQueens - 1] = rightDiagnol[i + col] = column[i] = 0; // Unmark diagonals
            }
        }
        return foundSolution; // No solution found, start backtracking
    }
    
    /**
     * Creates a deep copy of the current board.
     * 
     * @return a new 2D array representing the copied board.
     */
    private int[][] deepCopyBoard() {
        int[][] newBoard = new int[NumofQueens][NumofQueens];
        for (int i = 0; i < NumofQueens; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        return newBoard;
    }

    /**
     * The entry point of the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        FourQueens Q = new FourQueens();

        if (!Q.findSolution(0)) {
            System.out.println("No solution exists");
        }
        Q.resetBoard();

        
    }
}
