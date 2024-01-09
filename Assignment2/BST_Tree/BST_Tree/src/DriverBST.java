/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/11/30 
 * 
 * Assignment 2: Question B
 * Pseudocode/Algorithm: 
 *      -
 * Code: 
 * No input necessary
 * Output: 
 * 
* A+(B*C) -> ABC*+
*Conclusion: 
* The algorithm works almost as expected.
 */
import java.util.Scanner;

public class DriverBST {
	public static boolean alreadyInserted = false;
	public static boolean alreadyDeleted = false;

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(15);
		// 15, 5, 16, 3, 12, 20, 10, 13, 18, 23, 6, 7
		// bst.insert(15);
		 
		// bs
		// //.........
		// //.........
		// //.........
		// // Call preorder, inorder, and postorder traversals
		
		// // Call preorder, inorder, and postorder traversals
		Scanner input= new Scanner(System.in);
		 

		do{
			System.out.println("BST Menu" );
			System.out.println(" Press I or(i) to insert the values 15, 5, 16, 3, 12, 20, 10, 13, 18, 23, 6, 7  " );
			System.out.println(" Press D or(d) to delete the values  13, 16, 5" );
			System.out.println(" Press S or(s) to show the traversal values of the tree" );
			System.out.println(" Press Q or(q) to quit the program" );
			String choice = input.nextLine();

			switch(choice){
				case "I": case "i":
				if(!alreadyInserted){
					bst.insert(5);		 bst.insert(16);		 bst.insert(3);
		 			bst.insert(12);		 bst.insert(20);		 bst.insert(10);
		 			bst.insert(13);		 bst.insert(18);		 bst.insert(23);
		 			bst.insert(6);		 bst.insert(7);
					alreadyInserted= true;	
				}				
				break;

				case "D": case "d":
					if(!alreadyDeleted){
						System.out.println("Deleting 13");
						bst.delete(13);
						showTraversals(bst);
						
						// // Call preorder, inorder, and postorder traversals
						System.out.println("Deleting 16");
						bst.delete(16);
						showTraversals(bst);
						// // Call preorder, inorder, and postorder traversals
						System.out.println("Deleting 5");
						bst.delete(5);
						showTraversals(bst);
					}
				break;

				case "Q": case "q":
					break;

				case "S": case "s": showTraversals(bst);
					break;

				default:
					System.out.println("Please Choose a valid menu option");
				break;
			}
			if(choice != "Q" || choice != "q")
				break;


		}while (true);
		
	} // end main

	public static void showTraversals(BinarySearchTree<Integer> bst){
		System.out.println();
		bst.setPreorder();
		System.out.println();
		bst.setInorder();
		System.out.println();
		bst.setPostorder();
		System.out.println();
	}
}