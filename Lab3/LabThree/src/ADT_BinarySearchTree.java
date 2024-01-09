/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/12/04 
 * 
 * Lab 3: ADT Binary Search Tree
 * Pseudocode/Algorithm: 
 *      -  A class singleNode to represent each node of the binary tree.
 *     -  A class ADT_BinarySearchTree to represent the binary tree.
 *          - A method insertLevelOrder to construct the binary tree from the array.
 *          - A method inorderTraversal to perform the inorder traversal of the binary tree.
 * Code: 
 * No input necessary
 * Output: 
 *
 * The algorithm works as expected.
 */

/**
 * This class represents an ADT Binary Search Tree.
 */
public class ADT_BinarySearchTree {

    private SingleNode root;
    
    /**
     * Constructs an empty binary search tree.
     */
    public ADT_BinarySearchTree() {
        root = null;        
    }

    /**
     * The main method of the program.
     * 
     * @param args the command-line arguments
     * @throws Exception if an error occurs
     */
    public static void main(String[] args) throws Exception {
        try {

            ADT_BinarySearchTree tree = new ADT_BinarySearchTree();
            int arr[] = { 1, 2, 3, 4, 5, 6, 7 ,8 ,9 ,10, 11, 12, 13, 14, 15};
            tree.root = tree.insertLevelOrder(arr, tree.root, 0);
            System.out.println("Inorder Traversal: ");
            tree.inorderTraversal(tree.root);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Inserts elements from an array into the binary tree in level order.
     * 
     * @param arr  the array of elements to be inserted
     * @param root the root of the binary tree
     * @param i    the current index in the array
     * @return the root of the modified binary tree
     */
    SingleNode insertLevelOrder(int[] arr, SingleNode root, int i) {
        
        if (i < arr.length) { // NOT BASE CASE
            SingleNode temp = new SingleNode(arr[i]);
            root = temp;

            // insert left child
            root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
        }
        return root;// Base case for recursion 
    }

    /**
     * Performs an inorder traversal of the binary tree and prints the elements.
     * 
     * @param root the root of the binary tree
     */
    void inorderTraversal(SingleNode root) {
        
        if (root != null) { // CHECK bst NOT EMPTY
            inorderTraversal(root.left);
            System.out.print(root.item + " ");
            inorderTraversal(root.right);
        }
    }
}
