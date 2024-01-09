/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/11/30 
 * 
 * Lab 1: Question B
 * Pseudocode/Algorithm: 
 *      - loops through each character in the infix expression and performs the following steps:
 *      - If the character is an operand, it is appended to the postfix expression.
 *      - If the character is an opening parenthesis, it is pushed to the stack.
 *      - checks for balanced parenthesis, if NOT which is then popped and discarded.
 *      - If the character is an operator, 
 *          +the operators are popped and appended from the stack that have higher or equal precedence than the current operator, 
 *          +and then the current operator is pushed to the stack.

After the loop, the remaining operators are popped and appended from the stack to the postfix expression.
 * 
 * Code: 
 * No input necessary
 * Output: 
 * 
* A+(B*C) -> ABC*+
*Conclusion: 
* The algorithm works as expected.
 */

import java.util.Stack;

/**
 * The InfixToPostfix class provides methods to convert an infix expression to a postfix expression.
 */
public class InfixToPostfix {

    /**
     * Returns the precedence of a given operator.
     * Higher returned value means higher precedence.
     *
     * @param ch the operator character
     * @return the precedence of the operator
     */
    private static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    /**
     * Converts the given infix expression to a postfix expression.
     *
     * @param expression the infix expression
     * @return the postfix expression
     */
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            // add operand it to output
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            
            else if (c == '(') { // add '(', push it to the stack
                stack.push(c);
            }
            
            else if (c == ')') { // pop left bracket for every right bracket encountered
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Invalid Expression"; // Unbalanced expression
                } else {
                    stack.pop();
                }
            } else { // An operator is encountered
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (stack.peek() == '(') {
                        return "Invalid Expression";
                    }
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop all the remaining operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String exp = "A+(B*C)";
        System.out.println(infixToPostfix(exp));
    }
}
