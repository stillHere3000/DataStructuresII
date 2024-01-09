/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/11/30 
 * 
 * Assignment 1: Game of Peg Solitaire
 * Pseudocode/Algorithm: 
 *      -the Puzzle class represents a puzzle board with pegs and an empty space.
 *      -  Intialize the board with pegs (X) and one empty space (O)
 *      -  Main method to start the algorithm and print the solution
 *      -  Recursive backtracking algorithm
 *          +  Check if moive is legal
 *          +  makemove according to game rules
 *      -  Check if the puzzle is solved on every backtrack. if not undo move.
 * 
 * Code: 
 * No input necessary
 * Output: 
 * 
                        O X O 
                        O X O     
                    O O O O O O O 
                    O O O X O O X 
                    X O O O O X X 
                        O O O     
                        O O O  
*Conclusion: 
Despite numerous attempts Unable to solve the puzzle. 
    
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
 
 /**
  * The Puzzle class represents a puzzle board with pegs and an empty space.
  * It provides methods to initialize the board, find a solution using recursive backtracking,
  * check if the puzzle is solved, and make valid moves on the board.
  */
 public class Puzzle {
     private static final int SIZE = 7;
     private char[][] board;
     private List<char[][]> solution;
     private int numofpegs = 32;
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
         Puzzle puzzle = new Puzzle(); int i= 1; boolean found = false;
         puzzle.findSolution(1);        puzzle.findSolution(-1);        /*puzzle.findSolution(2);        puzzle.findSolution(-2);
         puzzle.findSolution(3);        puzzle.findSolution(-3);        puzzle.findSolution(4);        puzzle.findSolution(-4);
         puzzle.findSolution(5);        puzzle.findSolution(-5);        puzzle.findSolution(6);        puzzle.findSolution(-6);
         puzzle.findSolution(7);        puzzle.findSolution(-7);        puzzle.findSolution(8);        puzzle.findSolution(-8);
         puzzle.findSolution(9);        puzzle.findSolution(-9);        puzzle.findSolution(10);        puzzle.findSolution(-10);
         puzzle.findSolution(11);        puzzle.findSolution(-11);        puzzle.findSolution(12);        puzzle.findSolution(-12);
         puzzle.findSolution(13);        puzzle.findSolution(-13);        puzzle.findSolution(14);        puzzle.findSolution(-14);
         puzzle.findSolution(15);        puzzle.findSolution(-15);        puzzle.findSolution(16);        puzzle.findSolution(-16);
         puzzle.findSolution(17);        puzzle.findSolution(-17);        puzzle.findSolution(18);        puzzle.findSolution(-18);
         puzzle.findSolution(19);        puzzle.findSolution(-19);        puzzle.findSolution(20);        puzzle.findSolution(-20);
         puzzle.findSolution(21);        puzzle.findSolution(-21);        puzzle.findSolution(22);        puzzle.findSolution(-22);
         puzzle.findSolution(23);        puzzle.findSolution(-23);        puzzle.findSolution(24);        puzzle.findSolution(-24);
         puzzle.findSolution(25);        puzzle.findSolution(-25);        puzzle.findSolution(26);        puzzle.findSolution(-26);
         puzzle.findSolution(27);        puzzle.findSolution(-27);        puzzle.findSolution(28);        puzzle.findSolution(-28);
         puzzle.findSolution(29);        puzzle.findSolution(-29);        puzzle.findSolution(30);        puzzle.findSolution(-30);
         puzzle.findSolution(31);        puzzle.findSolution(-31);        puzzle.findSolution(32);        puzzle.findSolution(-32);
         puzzle.findSolution(33);        puzzle.findSolution(-33);        puzzle.findSolution(34);        puzzle.findSolution(-34);
         puzzle.findSolution(35);        puzzle.findSolution(-35);        puzzle.findSolution(36);        puzzle.findSolution(-36);
         
         while (!found) {
             found= puzzle.findSolution(i);            found = puzzle.findSolution(-i);
 
         }*/
         
         
         for (char[][] board : puzzle.solution) {
             System.out.println("Solution found");
             puzzle.print(board);
             System.out.println();
         }
         //puzzle.print(puzzle.board);
         //System.out.println();
         //puzzle.printAll();
     }
 
     // Recursive backtracking algorithm
     public boolean findSolution(int move) {
         // Check if the puzzle is solved
         if (isSolved() /*|| numofpegs < 5*/) {
             solution.add(cloneBoard());
             return true;
         }
 
         // Try all possible moves
         for (int i = 0; i < SIZE; i++) {
             for (int j = 0; j < SIZE; j++) {
                 if (isValidMove(i, j ) ) {
                     makeMove(i, j); 
                     if (findSolution(move + 1) /*|| numofpegs < 5*/) {
                         solution.add(0, cloneBoard()); // Add the current board to the solution
                         return true;
                     }
                     undoMove(i, j); // Backtrack
                 }
                 //solution.add(0, board);
                 print(board);
                 System.out.println("numofpegs: "+numofpegs);
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
             if (board[i][j] == ' ' ) {
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
                 /*else if(board[i][j] == 'X' && board[i+1][j+1] == 'X' && board[i+2][j+2] == 'O'){ //5
                     return true;
                 }
                 else if(board[i][j] == 'X' && board[i-1][j+1] == 'X' && board[i-2][j+2] == 'O'){//6
                     return true;
                 }
                 else if(board[i][j] == 'X' && board[i-1][j-1] == 'X' && board[i-2][j-2] == 'O'){//7
                     return true;
                 }
                 else if(board[i][j] == 'X' && board[i+1][j-1] == 'X' && board[i+2][j-2] == 'O'){//8
                     return true;
                 } */               
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
     private void makeMove(int i, int j) {
         
         // Check if the current position has a peg and if it can jump over an adjacent peg into an empty space
         try{
             if(board[i][j] == 'X' && board[i][j+1] == 'X' && board[i][j+2] == 'O'){// Top Middle
                 board[i][j] = 'O';
                 board[i][j+1] = 'O';
                 board[i][j+2] = 'X'; numofpegs--;
             }
             else if(board[i][j] == 'X' && board[i+1][j] == 'X' && board[i+2][j] == 'O'){ //Bottom Middle
                 board[i][j] = 'O';
                 board[i+1][j] = 'O';
                 board[i+2][j] = 'X'; numofpegs--;
             }
             else if(board[i][j] == 'X' && board[i][j-1] == 'X' && board[i][j-2] == 'O'){  // right middle
                 board[i][j] = 'O';
                 board[i][j-1] = 'O';
                 board[i][j-2] = 'X'; numofpegs--;
             }
             else if(board[i][j] == 'X' && board[i-1][j] == 'X' && board[i-2][j] == 'O'){ // left middle
                 board[i][j] = 'O';
                 board[i-1][j] = 'O';
                 board[i-2][j] = 'X'; numofpegs--;
             }
             /*else if(board[i][j] == 'X' && board[i+1][j+1] == 'X' && board[i+2][j+2] == 'O'){ //5 // top right
                 board[i][j] = 'O';
                 board[i+1][j+1] = 'O';
                 board[i+2][j+2] = 'X'; numofpegs--;
             }
             else if(board[i][j] == 'X' && board[i-1][j+1] == 'X' && board[i-2][j+2] == 'O'){//6 // top left
                 board[i][j] = 'O';
                 board[i-1][j+1] = 'O';
                 board[i-2][j+2] = 'X'; numofpegs--;
             }
             else if(board[i][j] == 'X' && board[i-1][j-1] == 'X' && board[i-2][j-2] == 'O'){//7 // bottom left
                 board[i][j] = 'O';
                 board[i-1][j-1] = 'O';
                 board[i-2][j-2] = 'X'; numofpegs--;
             }
             else if(board[i][j] == 'X' && board[i+1][j-1] == 'X' && board[i+2][j-2] == 'O'){//8 // bottom right
                 board[i][j] = 'O';
                 board[i+1][j-1] = 'O';
                 board[i+2][j-2] = 'X'; numofpegs--;
             }*/
             
             // You'll need to check all four directions (up, down, left, right) for the possibility of a valid move 
                        
         } // end try
         catch(Exception e){
             return;
         }
 
     }
 
     //board[i][j] = 'X';// Implement logic to undo a move
     // Undo a move (backtrack)
     private void undoMove(int i, int j) {
         try{
             if(board[i][j] == 'O' && board[i][j+1] == 'O' && board[i][j+2] == 'X'){
                 board[i][j] = 'X';
                 board[i][j+1] = 'X';
                 board[i][j+2] = 'O'; numofpegs++;
             }
             else if(board[i][j] == 'O' && board[i][j-1] == 'O' && board[i][j-2] == 'X'){
                 board[i][j] = 'X';
                 board[i][j-1] = 'X';
                 board[i][j-2] = 'O'; numofpegs++;
             }
             else if(board[i][j] == 'O' && board[i+1][j] == 'O' && board[i+2][j] == 'X'){
                 board[i][j] = 'X';
                 board[i+1][j] = 'X';
                 board[i+2][j] = 'O'; numofpegs++;
             }
             else if(board[i][j] == 'O' && board[i-1][j] == 'O' && board[i-2][j] == 'X'){
                 board[i][j] = 'X';
                 board[i-1][j] = 'X';
                 board[i-2][j] = 'O'; numofpegs++;
             }
             /*else if(board[i][j] == 'O' && board[i+1][j+1] == 'O' && board[i+2][j+2] == 'X'){ //5
                 board[i][j] = 'X';
                 board[i+1][j+1] = 'X';
                 board[i+2][j+2] = 'O'; numofpegs++;
             }
             else if(board[i][j] == 'O' && board[i-1][j+1] == 'O' && board[i-2][j+2] == 'X'){//6
                 board[i][j] = 'X';
                 board[i-1][j+1] = 'X';
                 board[i-2][j+2] = 'O'; numofpegs++;
             }
             else if(board[i][j] == 'O' && board[i-1][j-1] == 'O' && board[i-2][j-2] == 'X'){//7
                 board[i][j] = 'X';
                 board[i-1][j-1] = 'X';
                 board[i-2][j-2] = 'O'; numofpegs++;
             }
             else if(board[i][j] == 'O' && board[i+1][j-1] == 'O' && board[i+2][j-2] == 'X'){//8
                 board[i][j] = 'X';
                 board[i+1][j-1] = 'X';
                 board[i+2][j-2] = 'O'; numofpegs++;
             }*/            
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
 