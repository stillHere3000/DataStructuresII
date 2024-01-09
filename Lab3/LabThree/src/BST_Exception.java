/*
 * Author: Trevor Maliro
 * Student ID: 239498690
 * COSC2006LABI23F Lab 6 Abstract Data Type LinkedList
 * Any and all work in this file is my own.
 * 
 */


/**
 * This class contains the ListException. 
 * It extends the Runtime Exception and gives an error message if a logic error has occurred.
 * and handling errors such as outofbound indexes, null pointers .
 * @author 
 * @version 
 */
public class BST_Exception extends RuntimeException {
    public BST_Exception(String message) {
        super(message);
    }
}