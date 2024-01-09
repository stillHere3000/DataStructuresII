/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/11/30 
 * 
 * Lab 1: Question A
 * Pseudocode/Algorithm: 
 *      - loops through each character in radar:
 *      - Recursively checks if the first and last characters match
 *      - If the first and last characters match, the method is called again with the remaining substring.
 *      - If the first and last characters do not match, the string is not a palindrome.
 *      - If there is only one character or no character, the string is a palindrome.
 *      - The method returns true if the string is a palindrome, false otherwise.
 *      - The main method calls the checkPalindrome method with the string to be checked.
 *      - The main method prints the result of the check.
 *      - The main method prompts the user to check more strings.
 * 
 * Code: 
 * No input necessary
 * Output: 
 * 
* A+(B*C) -> ABC*+
*Conclusion: 
* The algorithm works as expected.
 */
import java.util.Scanner;

/**
 * The PalindromeChecker class provides a method to check if a given string is a palindrome.
 */
public class PalindromeChecker {

    /**
     * Checks if a given string is a palindrome.
     *
     * @param str   the string to be checked
     * @param start the starting index of the substring to be checked
     * @param end   the ending index of the substring to be checked
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean checkPalindrome(String str, int start, int end) {
        // Base case: If there is only one character or no character
        if (start >= end) {
            return true;
        }

        // Check if the first and last characters match
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }

        // Recursive call for the remaining substring
        return checkPalindrome(str, start + 1, end - 1);
    }

    /**
     * The main method of the PalindromeChecker class.
     * It checks if the given string is a palindrome and prompts the user to check more strings.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String str = "radar";
        boolean result = checkPalindrome(str, 0, str.length() - 1);
        Scanner scanner = new Scanner(System.in);
        if (result) {
            System.out.println(str + " is a palindrome.");
        } else {
            System.out.println(str + " is not a palindrome.");
        }
        do {
            System.out.print("Try check another string tyype exit to quit: ");
            str = scanner.nextLine();
            result = checkPalindrome(str, 0, str.length() - 1);
            if (result) {
                System.out.println(str + " is a palindrome.");
            } else {
                System.out.println(str + " is not a palindrome.");
            }

            if(str.equals("exit")) {
                break;
            }
        }while(true);
    }
}
