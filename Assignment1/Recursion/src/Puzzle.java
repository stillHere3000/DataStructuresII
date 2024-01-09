/**
Author: Trevor Maliro
Student ID: 239498690
COSC 2007 Section O & LAB O 
Assignment 1
Game of Peg Solitaire
Any and all work in this file is my own.
*/

import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Puzzle {
    private static final int SIZE = 7;
    private char[][] board;
    private List<char[][]> solution;
    private List<char[][]> backTrack;    
    private List<Integer> backTrackCondition;

    private int numofpegs= 32;
    private char[][] AnswerKey = {
        {' ', ' ', '0', '0', '0', ' ', ' '},
        {' ', ' ', '0', '0', '0', ' ', ' '},
        {'0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', 'X', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0'},
        {' ', ' ', '0', '0', '0', ' ', ' '},
        {' ', ' ', '0', '0', '0', ' ', ' '}
    };


    // Constructor
    public Puzzle() {
        board = new char[SIZE][SIZE];
        initializeBoard();
        solution = new ArrayList<>();
        backTrack = new ArrayList<>();
        backTrackCondition = new ArrayList<>();
    }

    // Initialize the board with pegs (X) and one empty space (O)
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i < 2 || i > 4) && (j < 2 || j > 4)) {
                    board[i][j] = ' ';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
        board[3][3] = 'O'; // Empty space in the middle
    }

    // Main method to start the algorithm and print the solution
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        ///while (!puzzle.findSolution(1)){}
        puzzle.findSolution(1); // Start the algorithm
        
        for (char[][] board : puzzle.solution) {
            //puzzle.print(board);
            System.out.println();
        }
        //puzzle.print(puzzle.board);
        //System.out.println();
        //puzzle.printAll();
    }

    // Recursive backtracking algorithm
    public boolean findSolution(int move) {
        // Check if the puzzle is solved
        if (isSolved()) {
            solution.add(cloneBoard());
            return true;
        }

        // Try all possible moves
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isValidMove(i, j)  ) {
                    if(makeMove(i, j)){
                      backTrack.add(0, cloneBoard());
                    }
                    if (findSolution(move + 1)  ) {
                        solution.add(0, cloneBoard()); // Add the current board to the solution
                        return true;
                    }
                    board = backTrack.get(0);
                    
                    undoMove(i, j); // Backtrack
                    backTrack.remove(0); //numofpegs++;
                    backTrackCondition.remove(0); //numofpegs++;
                    
                }
                //solution.add(0, board);
                print(board);
                System.out.println("numofpegs: " + numofpegs);
            }
        }
        return false;
    }

    // Check if the puzzle is solved
    private boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != AnswerKey[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    //return board[i][j] == 'X' && board[i][j]  != ' ';
    // Check if a move is valid
    private boolean isValidMove(int i, int j) {
        
        // Check if the current position has a peg and if it can jump over an adjacent peg into an empty space
        try{
            if (board[i][j] != 'X') {
                return false;
            }else{
                if(board[i][j] == 'X' && board[i][j+1] == 'X' && board[i][j+2] == 'O' ){ //1
                    return true;
                }
                else if(board[i][j] == 'X' && board[i][j-1] == 'X' && board[i][j-2] == 'O'){ //2 
                    return true;
                }
                else if(board[i][j] == 'X' && board[i+1][j] == 'X' && board[i+2][j] == 'O'){//3
                    return true;
                }
                else if(board[i][j] == 'X' && board[i-1][j] == 'X' && board[i-2][j] == 'O'){ //4
                    return true;
                }                      
                else{
                    return false;
                }
            }
        }
        catch(Exception e){
            return false;
        }        
    }

    private boolean checkRange(int i, int j){
        return (i >= 2 || i <= 4) && (j >= 2 || j <= 4);
    }


    //board[i][j] = 'O';
    // Make a move
    private boolean makeMove(int i, int j) {
        
        // Check if the current position has a peg and if it can jump over an adjacent peg into an empty space
        try{
            if(board[i][j] == 'X' && board[i][j+1] == 'X' && board[i][j+2] == 'O'){// Top Middle
                board[i][j] = 'O';
                board[i][j+1] = 'O';
                board[i][j+2] = 'X'; numofpegs--; backTrackCondition.add(0,0); return true;
            }
            else if(board[i][j] == 'X' && board[i][j-1] == 'X' && board[i][j-2] == 'O'){ //Bottom Middle
                board[i][j] = 'O';
                board[i][j-1] = 'O';
                board[i][j-2] = 'X'; numofpegs--; backTrackCondition.add(0, 1);; return true;
            }
            else if(board[i][j] == 'X' && board[i+1][j] == 'X' && board[i+2][j] == 'O'){  // right middle
                board[i][j] = 'O';
                board[i+1][j] = 'O';
                board[i+2][j] = 'X'; numofpegs--; backTrackCondition.add(0, 2); return true;
            }
            else if(board[i][j] == 'X' && board[i-1][j] == 'X' && board[i-2][j] == 'O'){ // left middle
                board[i][j] = 'O';
                board[i-1][j] = 'O';
                board[i-2][j] = 'X'; numofpegs--; backTrackCondition.add(0, 3); return true;
            }             
            else{
                return false;
            }
            
            // You'll need to check all four directions (up, down, left, right) for the possibility of a valid move 
                       
        } // end try
        catch(Exception e){
            return false;
        }

    }

    //board[i][j] = 'X';// Implement logic to undo a move
    // Undo a move (backtrack)
    private void undoMove(int i, int j) {
        try{
            if(board[i][j] == 'O' && board[i][j+1] == 'O' && board[i][j+2] == 'X' ){
                board[i][j] = 'X';
                board[i][j+1] = 'X';
                board[i][j+2] = 'O'; numofpegs++;
            }
            else if(board[i][j] == 'O' && board[i][j-1] == 'O' && board[i][j-2] == 'X' ){
                board[i][j] = 'X';
                board[i][j-1] = 'X';
                board[i][j-2] = 'O'; numofpegs++;
            }
            else if(board[i][j] == 'O' && board[i+1][j] == 'O' && board[i+2][j] == 'X' ){
                board[i][j] = 'X';
                board[i+1][j] = 'X';
                board[i+2][j] = 'O'; numofpegs++;
            }
            else if(board[i][j] == 'O' && board[i-1][j] == 'O' && board[i-2][j] == 'X' ){
                board[i][j] = 'X';
                board[i-1][j] = 'X';
                board[i-2][j] = 'O'; numofpegs++;
            }           
            
        }
        catch(Exception e){
            return;
        }
    }

    // Clone the current board
    private char[][] cloneBoard() {
        char[][] newBoard = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, SIZE);
        }
        return newBoard;
    }

    // Print the current board
    public void print(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }    
}